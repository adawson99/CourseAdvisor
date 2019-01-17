package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.Dao.CourseSelectionDAO;
import com.example.adawson.courseadvisor.model.CourseSelectionObject;

import java.util.List;

public class CourseSelectionRepository {

    // course selection DAO
    private CourseSelectionDAO courseSelectionDAO;

    private LiveData<List<CourseSelectionObject>> courseSelections;

    public CourseSelectionRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        courseSelectionDAO = db.courseSelectionDAO();
        courseSelections = courseSelectionDAO.getTotalCourses();
    }


    // asynchronous replaceCourse task
    public void replaceCourse(String courseId, int key) {
        new replaceAsyncTask(courseSelectionDAO, courseId, key).execute();
    }

    // get courses for this semester
    public LiveData<List<String>> getThisSemesterCourses(int id) {
        return courseSelectionDAO.getThisSemesterCourses(id);
    }

    //get all courses before and during this semester by id
    public LiveData<List<String>> getAllCoursesBefore(int id) {
        return courseSelectionDAO.getAllCoursesBefore(id);
    }

    // get all course ids after this semester
    public LiveData<List<String>> getAllCoursesAfter(int id) {
        return courseSelectionDAO.getAllCoursesAfter(id);
    }

    //get all courseSelections
    public LiveData<List<CourseSelectionObject>> getAllPairings() {
        return courseSelectionDAO.getAllPairings();
    }

    // asynchronous insert task
    public void insert(CourseSelectionObject courseSelectionObject) {
        new insertAsyncTask(courseSelectionDAO).execute(courseSelectionObject);
    }

    private static class insertAsyncTask extends AsyncTask<CourseSelectionObject, Void, Void> {
        private CourseSelectionDAO mAsyncTaskDao;

        insertAsyncTask(CourseSelectionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseSelectionObject... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class replaceAsyncTask extends AsyncTask<Void, Void, Void> {
        private CourseSelectionDAO mAsyncTaskDao;
        private String stringVal;
        private int intVal;

        replaceAsyncTask(CourseSelectionDAO dao, String s, int i) {
            stringVal = s;
            intVal = i;
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.replaceCourse(stringVal, intVal);
            return null;
        }
    }

    public void updateCourse(String courseId, int courseLoc) {
        new updateCourseAsyncTask(courseSelectionDAO, courseId, courseLoc).execute();
    }

    private static class updateCourseAsyncTask extends AsyncTask<Void, Void, Void> {
        private CourseSelectionDAO mAsyncTaskDao;
        private String stringVal;
        private int intVal;

        updateCourseAsyncTask(CourseSelectionDAO dao, String s, int i) {
            stringVal = s;
            intVal = i;
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.updateCourse(stringVal, intVal);
            return null;
        }
    }
}


