package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.model.Course;

import java.util.List;

public class CourseRepository {
    private CourseDAO courseDAO;

    private LiveData<List<Course>> courses;

    private LiveData<String> name;

    public CourseRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        courseDAO = db.courseDAO();
        courses = courseDAO.getAllCourses();
    }

    LiveData<List<Course>> getCourses() {
        return courses;
    }

    //added to get course name by key
    String getCourseNameByKey(int key) {
        return courseDAO.getCourseNameByKey(key);
    }

    //added to get course name by id
    String getCourseNameById(String id) {
        return courseDAO.getCourseNameById(id);
    }

    //get course major by id
    String getMajorById(String id) {
        //return courseDAO.getCourseMajorById(id);
        if (id.startsWith("CSC")) {
            return "Computer Science";
        } else {
            return "English";
        }
    }

   // inserts all the courses in the list into the database
    public void insert(List<Course> courses) {
        courseDAO.insertAll(courses);
    }

    // ALSO NOT ASYNCHRONOUS?
    //count all items in course table
    int countItems() {
        return courseDAO.countItems();
    }

    public void insert(Course course) {
        new insertAsyncTask(courseDAO).execute(course);
    }

    public LiveData<List<Course>> getMajorCourses(String major) { return courseDAO.getCourseBy(major); }

    private static class insertAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDAO mAsyncTaskDao;

        insertAsyncTask(CourseDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Course... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
