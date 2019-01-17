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

    }


