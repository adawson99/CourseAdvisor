package com.example.adawson.courseadvisor;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.CourseSelectionObject;
import com.example.adawson.courseadvisor.model.Semester;

import java.util.ArrayList;
import java.util.List;

// changes so that it implements the semester_preview_fragment
public class Home extends AppCompatActivity {

    View view1, view2, view3, view4, view5, view6, view7, view8;
    Intent editSem;
    int semesterId;
    TextView creditHours;

    // for editing the fragment
    private FragmentManager fm = getSupportFragmentManager();
    private Fragment s1;
    private  Fragment s2;
    private Fragment s3;
    private Fragment s4;
    private Fragment s5;
    private Fragment s6;
    private Fragment s7;
    private Fragment s8;

    CourseDatabase db;

    String[] sem1Course = new String[8];
    String[] sem2Course = new String[8];
    String[] sem3Course = new String[8];
    String[] sem4Course = new String[8];
    String[] sem5Course = new String[8];
    String[] sem6Course = new String[8];
    String[] sem7Course = new String[8];
    String[] sem8Course = new String[8];

    private List<CourseSelectionObject> courseSelectionList = new ArrayList<>();

    Activity activity = this;

    private static final String TAG = "logging" ;


    // list of semesters
    List<Semester> semesterList = new ArrayList<>();

   // course placeholder for inserting vs querying when testing
    private CourseRepository courseRepository;

    // semester repositories
    private SemesterRepository semesterRepository;
    private CourseSelectionRepository courseSelectionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editSem = new Intent(activity, SemesterEdit.class);

        setListenersForAllFragments();

        db = CourseDatabase.getDatabase(getApplication());

        //
        courseRepository = new CourseRepository(getApplication());
        // initialize semesterRepository
        semesterRepository = new SemesterRepository(getApplication());
        // initialize courseSelectionRepository
        courseSelectionRepository = new CourseSelectionRepository(getApplication());

        semesterRepository.getAllSemesters().observe(
                this,
                new Observer<List<Semester>>() {
                    @Override
                    public void onChanged(@Nullable List<Semester> semesters) {
                        semesterList = semesters;
                        setTitles(fm);
                    }
                });

        Boolean dbEmpty = true;
        //loads the semester templates if first time running
       Cursor mcursor = db.query("SELECT * FROM semester_table", null);
        if (mcursor.moveToFirst()) {
            dbEmpty = false;
        }
        if (dbEmpty) {
            populateSemesters();
            Log.i(TAG, "populating the semesters");
        } else {
            // in progress
            Log.i(TAG, "there are semester, so load them into view");
        }

        s1 = fm.findFragmentById(R.id.semester_1_fragment);
        s2 = fm.findFragmentById(R.id.semester_2_fragment);
        s3 = fm.findFragmentById(R.id.semester_3_fragment);
        s4 = fm.findFragmentById(R.id.semester_4_fragment);
        s5 = fm.findFragmentById(R.id.semester_5_fragment);
        s6 = fm.findFragmentById(R.id.semester_6_fragment);
        s7 = fm.findFragmentById(R.id.semester_7_fragment);
        s8 = fm.findFragmentById(R.id.semester_8_fragment);

        // updates the list of courseSelection objects, triggered by changes in course selections
        courseSelectionRepository.getAllPairings().observe(
                this, new Observer<List<CourseSelectionObject>>() {
                    @Override
                    public void onChanged(@Nullable List<CourseSelectionObject> courseSelections) {
                        courseSelectionList = courseSelections;
                        //using the populated list of semesters, the courseIds fora specific semester can be pulled out
                        // they are stored in courseIds
                        setFragmentCourseIds(courseSelectionList, fm);
                        Log.i(TAG, "courseSelections updating");

                        // add calls for all the semesters
                        updateCredits(sem1Course, s1);
                        updateCredits(sem2Course, s2);
                        updateCredits(sem3Course, s3);
                        updateCredits(sem4Course, s4);
                        updateCredits(sem5Course, s5);
                        updateCredits(sem6Course, s6);
                        updateCredits(sem7Course, s7);
                        updateCredits(sem8Course, s8);
                    }
                });

    }

    // update the credits on the home screen for each semester
    public void updateCredits(String[] semesterCourses, Fragment fragment) {
        int creditCount = 0;

        for (int i = 0; i < semesterCourses.length; i++) {
            creditHours = fragment.getView().findViewById(R.id.creditHours);

            //int credits = courseRepository.getCreditsByCourseId(semesterCourses[i]);
            if (semesterCourses[i] != null) {
                creditCount += 4;
            }
            Log.i(TAG, "the current credit count " + creditCount);

        }
        Log.i(TAG, "final creditCount" + creditCount);
        // actually count based on the courses
        creditHours.setText(creditCount + "");
    }

    // all the courseSelection objects from database and fills into the view for each fragment
    public void setFragmentCourseIds(List<CourseSelectionObject> courseSelectionList, FragmentManager fm) {

        int i = 0;
        CourseSelectionObject cs;
        int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
        // run through all pairings and add to List for each semester of courses
        for (int j=0; j<courseSelectionList.size(); j++) {
            cs = courseSelectionList.get(j);
            switch (cs.getSemester()) {
                // adds the course to the list of courses for that semester, and  sends this course name, the course number, and the fragment to fillInCourses()
                case 1:
                    sem1Course[a] = cs.getCourse();
                    fillInCourses(cs.getCourse(),a, s1);
                    a++;
                    break;
                case 2:
                    sem2Course[b] = cs.getCourse();
                    fillInCourses(cs.getCourse(),b, s2);
                    b++;
                    break;
                case 3:
                    sem3Course[c] = cs.getCourse();
                    fillInCourses(cs.getCourse(),c, s3);
                    c++;
                    break;
                case 4:
                    sem4Course[d] = cs.getCourse();
                    fillInCourses(cs.getCourse(),d, s4);
                    d++;
                    break;
                case 5:
                    sem5Course[e] = cs.getCourse();
                    fillInCourses(cs.getCourse(),e, s5);
                    e++;
                    break;
                case 6:
                    sem6Course[f] = cs.getCourse();
                    fillInCourses(cs.getCourse(),f, s6);
                    f++;
                    break;
                case 7:
                    sem7Course[g] = cs.getCourse();
                    fillInCourses(cs.getCourse(),g, s7);
                    g++;
                    break;
                case 8:
                    sem8Course[h] = cs.getCourse();
                    fillInCourses(cs.getCourse(),h, s8);
                    h++;
                    break;
            }
            }
        }

    // writes on the buttons depending on courseselectionobejcts in database
    public void fillInCourses(String courseName, int courseNumber, Fragment fragment) {

        // this makes the entire app get upset about non asychronous database access
        //String courseName = courseRepository.getCourseNameByKey(courseId);

        TextView view;

        switch (courseNumber) {
            //course number 1
            case 0:
                view = fragment.getView().findViewById(R.id.course1);
                view.setText(courseName);
                break;
            case 1:
                view = fragment.getView().findViewById(R.id.course2);
                view.setText(courseName);
                break;
            case 2:
                view = fragment.getView().findViewById(R.id.course3);
                view.setText(courseName);
                break;
            case 3:
                view = fragment.getView().findViewById(R.id.course4);
                view.setText(courseName);
                break;
        }

    }


    /* handler for checkProgress button that
     * triggers information/feedback activity
      */
    public void checkProgress(View view) {
        // will connect to the feedback activity
     // Intent intent = new Intent(this, feedback.class);
        Intent intent = new Intent(this, FeedbackActivity.class);
      startActivity(intent);
    }


    public void populateSemesters() {
        Semester s1 = new Semester(1, "Semester 1");
        Semester s2 = new Semester(2, "Semester 2");
        Semester s3 = new Semester(3, "Semester 3");
        Semester s4 = new Semester(4, "Semester 4");
        Semester s5 = new Semester(5, "Semester 5");
        Semester s6 = new Semester(6, "Semester 6");
        Semester s7 = new Semester(7, "Semester 7");
        Semester s8 = new Semester(8, "Semester 8");
        semesterRepository.insert(s1);
        semesterRepository.insert(s2);
        semesterRepository.insert(s3);
        semesterRepository.insert(s4);
        semesterRepository.insert(s5);
        semesterRepository.insert(s6);
        semesterRepository.insert(s7);
        semesterRepository.insert(s8);
        finish();
    }

    public void setTitles(FragmentManager fm) {

        Fragment s1 = fm.findFragmentById(R.id.semester_1_fragment);
        Fragment s2 = fm.findFragmentById(R.id.semester_2_fragment);
        Fragment s3 = fm.findFragmentById(R.id.semester_3_fragment);
        Fragment s4 = fm.findFragmentById(R.id.semester_4_fragment);
        Fragment s5 = fm.findFragmentById(R.id.semester_5_fragment);
        Fragment s6 = fm.findFragmentById(R.id.semester_6_fragment);
        Fragment s7 = fm.findFragmentById(R.id.semester_7_fragment);
        Fragment s8 = fm.findFragmentById(R.id.semester_8_fragment);


        TextView semester1Title = (TextView) s1.getView().findViewById(R.id.semesterTitle);
        semester1Title.setText(semesterList.get(0).getName());

        // semester 2 title
        TextView semester2Title = (TextView) s2.getView().findViewById(R.id.semesterTitle);
        semester2Title.setText(semesterList.get(1).getName());

        // semester 3 title
        TextView semester3Title = (TextView) s3.getView().findViewById(R.id.semesterTitle);
        semester3Title.setText(semesterList.get(2).getName());

        // semester 4 title
        TextView semester4Title = (TextView) s4.getView().findViewById(R.id.semesterTitle);
        semester4Title.setText(semesterList.get(3).getName());

        // semester 5 title
        TextView semester5Title = (TextView) s5.getView().findViewById(R.id.semesterTitle);
        semester5Title.setText(semesterList.get(4).getName());

        // semester 6 title
        TextView semester6Title = (TextView) s6.getView().findViewById(R.id.semesterTitle);
        semester6Title.setText(semesterList.get(5).getName());

        // semester 7 title
        TextView semester7Title = (TextView) s7.getView().findViewById(R.id.semesterTitle);
        semester7Title.setText(semesterList.get(6).getName());

        // semester 8 title
        TextView semester8Title = (TextView) s8.getView().findViewById(R.id.semesterTitle);
        semester8Title.setText(semesterList.get(7).getName());
    }

    public void setListenersForAllFragments() {

        view1 = findViewById(R.id.fragment_container);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                semesterId = semesterList.get(0).getId();
                Log.i(TAG, "The string to pass " + semesterId);
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view2 = findViewById(R.id.fragment__2_container);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(1).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view3 = findViewById(R.id.fragment__3_container);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(2).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view4 = findViewById(R.id.fragment__4_container);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(3).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view5 = findViewById(R.id.fragment__5_container);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(4).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view6 = findViewById(R.id.fragment__6_container);
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(5).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view7 = findViewById(R.id.fragment__7_container);
        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(6).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view8 = findViewById(R.id.fragment__8_container);
        view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(7).getId();
                editSem.putExtra(Keys.SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });


    }


}

