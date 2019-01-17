package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.adawson.courseadvisor.CourseDatabase;
import com.example.adawson.courseadvisor.SemesterDAO;
import com.example.adawson.courseadvisor.model.Semester;

import java.util.List;

public class SemesterRepository {

    private static final String TAG = "logging";

    private SemesterDAO semesterDAO;

    private LiveData<List<Semester>> semesters;


    public SemesterRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        semesterDAO = db.semesterDAO();
        semesters = semesterDAO.getAllSemesters();
    }

    // get semester name
    String getSemesterName(int id) {
        return semesterDAO.getSemesterName(id);
    }

    LiveData<List<Semester>> getAllSemesters() {
        return semesters;
    }

    public void insert(Semester semester) {
        new insertAsyncTask(semesterDAO).execute(semester);
    }

    private static class insertAsyncTask extends AsyncTask<Semester, Void, Void> {
        private SemesterDAO mAsyncTaskDao;

        insertAsyncTask(SemesterDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Semester... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }



}

