package com.example.adawson.courseadvisor;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.adawson.courseadvisor.model.User;

import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        CourseDatabase db = CourseDatabase.getDatabase(application);
        userDAO = db.userDAO();
        users = userDAO.getAllUsers();
    }

    LiveData<List<User>> getUsers() {
        return users;
    }

    public void insert(User user) {
        new insertAsyncTask(userDAO).execute(user);
    }

    public void update(String majorName) {
        new updateAsyncTask(userDAO).execute(majorName);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO mAsyncTaskDao;

        insertAsyncTask(UserDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<String, Void, Void> {
        private UserDAO mAsyncTaskDao;

        updateAsyncTask(UserDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}