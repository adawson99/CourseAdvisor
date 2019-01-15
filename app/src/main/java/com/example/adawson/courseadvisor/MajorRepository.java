package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.model.Major;

import java.util.List;

public class MajorRepository {
    private MajorDAO majorDAO;
    private LiveData<List<Major>> majors;

    public MajorRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        majorDAO = db.majorDAO();
        majors = majorDAO.getAllMajors();
    }

    LiveData<List<Major>> getMajors() {
        return majors;
    }

    public void insert(Major major) {
        new insertAsyncTask(majorDAO).execute(major);
    }

    private static class insertAsyncTask extends AsyncTask<Major, Void, Void> {
        private MajorDAO mAsyncTaskDao;

        insertAsyncTask(MajorDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Major... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}