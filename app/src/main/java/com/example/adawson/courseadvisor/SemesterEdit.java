package com.example.adawson.courseadvisor;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.adawson.courseadvisor.model.CourseSelectionObject;

public class SemesterEdit extends AppCompatActivity {

    private int buttonId;

    // the id of the currentSemester, that is recieve when activity is triggered
    private int currentSemester;

    // list of course IDs from the semester based on courseSelection
    private String[] courseIds = new String[8];

    // the semester repository
    private CourseRepository courseRepository;
    private SemesterRepository semesterRepository;
    private CourseSelectionRepository courseSelectionRepository;
    // for holding CourseSelections of the semester
    private List<CourseSelectionObject> courseSelectionList = new ArrayList<>();

    private static final String TAG = "logging";
    String msgKey = "msgKey";
    //Button button5 = (Button)findViewById(R.id.button5);
    //Button button6 = (Button)findViewById(R.id.button6);
    String hldmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_edit);

        // the id of the current semester from the bundle
        Intent intent = getIntent();
        currentSemester = intent.getIntExtra(Keys.SEMESTER_SELECTED, 0);
        Log.i(TAG, currentSemester + " the string passed");

        // populate the semester title in top textView based on the id (1-8)
        TextView semesterTitle = findViewById(R.id.currentSemester);
        semesterTitle.setText("Semester " + currentSemester);


        //DATABASE STUFF
        CourseDatabase db = CourseDatabase.getDatabase(getApplication());

        // initialize courseRepository
        courseRepository = new CourseRepository(getApplication());
        // initialize semesterRepository
        semesterRepository = new SemesterRepository(getApplication());
        // initialize courseSelectionRepository
        courseSelectionRepository = new CourseSelectionRepository(getApplication());

        // updates the list of courseSelection objects, triggered by changes in course selections
        courseSelectionRepository.getAllPairings().observe(
                this, new Observer<List<CourseSelectionObject>>() {
                    @Override
                    public void onChanged(@Nullable List<CourseSelectionObject> courseSelections) {
                        courseSelectionList = courseSelections;
                        //using the populated list of semesters, the courseIds for a specific semester can be pulled out
                        // they are stored in courseIds
                        setThisSemesterCourseIds(courseSelectionList);
                        Log.i(TAG, "courseSelections updating");
                    }
                });
    }


    // fills the courseId list with the appropriate courses
    public void setThisSemesterCourseIds(List<CourseSelectionObject> courseSelections) {
        int i = 0;
        CourseSelectionObject cs;
        Log.i(TAG, "the # of pairings: " + courseSelections.size());
        for (int j=0; j<courseSelections.size(); j++) {
            cs = courseSelections.get(j);
            Log.i(TAG, "the current semesters is " + currentSemester + " and the semester of this course" +
                    "is " + cs.getSemester());
            if (cs.getSemester() == currentSemester) {
                //
                courseIds[i] = cs.getCourse();
                Log.i(TAG, "this is i: " + i);
                nameButtons(cs.getCourse(), i);
                Log.i(TAG, "getting the course: " + cs.getCourse());
                i++;
            }
        }
        //for debugging, see all the courses in that semester
        printCourseNames();
    }

    // writes on the buttons depending on courseselectionobejcts in database
    public void nameButtons(String courseName, int courseNumber) {

        // this makes the entire app get upset about non asychronous database access

       //String courseName = courseRepository.getCourseNameByKey(courseId);
        Log.i(TAG, "get courseNumber... " + courseNumber);
        Button button;

        switch (courseNumber) {
            //course number 1
            case 0:
                button = findViewById(R.id.button);
                button.setText(courseName);
                break;
            case 1:
                button = findViewById(R.id.button2);
                button.setText(courseName);
                break;
            case 2:
                button = findViewById(R.id.button3);
                button.setText(courseName);
                break;
            case 3:
                button = findViewById(R.id.button4);
                button.setText(courseName);
                break;
            case 4:
                button = findViewById(R.id.button5);
                button.setText(courseName);
                break;
            case 5:
                button = findViewById(R.id.button6);
                button.setText(courseName);
                break;
        }

    }

    // prints out the names of the courses whose ids are in courseIds
    public void printCourseNames() {
        int size = courseIds.length;
        for (int i = 0; i<size; i++) {
            Log.i(TAG, courseIds[i] + "I am printing a course name, but need to get it from database!");
        }

    }

    // triggers CourseSelection activity
    public void openCourseList(View view) {
        TextView v = (TextView) findViewById(R.id.currentSemester);
        String message = v.getText().toString();
        Intent intent = new Intent(this,CourseSelection.class);

        // attempting to record which button the data should be bound to visually
        buttonId = v.getId();

        //Button button = (Button) findViewById(buttonId);
        //String currentCourse = button.getText().toString();
        //intent.putExtra(Keys.CURRENT_COURSE,currentCourse);

        intent.putExtra(msgKey,message);
        //startActivity(intent);
        startActivityForResult(intent, Keys.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("TestCrash","in onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Keys.REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("TestCrash","RESULT_OK");
            String courseId = data.getStringExtra(Keys.HLDMSG);
            String oldCourseId = data.getStringExtra(Keys.CURRENT_COURSE);
            Log.i("TestCrash","oldCourseId: "+oldCourseId);

            if (courseId==null) {
                Log.d("TestCrash","courseId is null");
            }
            Log.i("TestCrash", courseId + " is the course!");
            //Button button = (Button) findViewById(R.id.button);

            //replace the current course with the selected course
            //String oldCourseId = button.getText().toString();
            //Log.i("TestCrash","oldCourseId: "+oldCourseId);

            CourseDatabase db = CourseDatabase.getDatabase(getApplication());
            Log.d("TestCrash","Adding course to db");
            CourseSelectionObject courseSelection = new CourseSelectionObject(currentSemester,courseId, 1);
            courseSelectionRepository.insert(courseSelection);
            /*
            Cursor mcursor = db.query("SELECT * from course_selection_table",null);

            if (mcursor.moveToFirst()) {
                mcursor = db.query("SELECT * from course_selection_table WHERE semesterId = currentSemester AND courseId = oldCourseId",null);
                if (mcursor.moveToFirst()) {
                    //replace course
                    Log.d("TestCrash","Replacing old course with new in the db");
                    int courseSelectionKey = courseRepository.getCourseKeyById(oldCourseId);
                    courseSelectionRepository.replaceCourse(courseId,courseSelectionKey);
                } else {
                    //add course to repository
                    Log.d("TestCrash","Adding course to db");
                    CourseSelectionObject courseSelection = new CourseSelectionObject(currentSemester,courseId);
                    courseSelectionRepository.insert(courseSelection);
                }
            } else {
                //add course to repository
                Log.d("TestCrash","Adding course to db");
                CourseSelectionObject courseSelection = new CourseSelectionObject(currentSemester,courseId);
                courseSelectionRepository.insert(courseSelection);
            }
            */

            //button.setText(courseId);
        }
    }

}
