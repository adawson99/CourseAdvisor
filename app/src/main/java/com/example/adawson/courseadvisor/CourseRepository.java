package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.model.Course;

import java.util.List;

public class CourseRepository {
    private CourseDAO courseDAO;
    private LiveData<List<Course>> courses;

    public CourseRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        courseDAO = db.courseDAO();
        courses = courseDAO.getAllCourses();
    }

    LiveData<List<Course>> getCourses() {
        return courses;
    }

    public void insert(Course course) {
        new insertAsyncTask(courseDAO).execute(course);
    }

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
