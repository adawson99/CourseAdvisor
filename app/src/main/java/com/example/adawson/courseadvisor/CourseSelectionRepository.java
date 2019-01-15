package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.Dao.CourseSelectionDAO;
import com.example.adawson.courseadvisor.model.CourseSelection;

import java.util.List;

public class CourseSelectionRepository {

        // course selection DAO
        private CourseSelectionDAO courseSelectionDAO;

        private LiveData<List<CourseSelection>> courseSelections;

        public CourseSelectionRepository(Application application) {
            CourseDatabase db = CourseDatabase.getDatabase(application);
            courseSelectionDAO = db.courseSelectionDAO();
            courseSelections = courseSelectionDAO.getTotalCourses();
        }

    public void insert(CourseSelection courseSelection) {
        new insertAsyncTask(courseSelectionDAO).execute(courseSelection);
    }

    private static class insertAsyncTask extends AsyncTask<CourseSelection, Void, Void> {
        private CourseSelectionDAO mAsyncTaskDao;

        insertAsyncTask(CourseSelectionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseSelection... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    }


