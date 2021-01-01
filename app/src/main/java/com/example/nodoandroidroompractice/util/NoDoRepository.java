package com.example.nodoandroidroompractice.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nodoandroidroompractice.data.NoDoDao;
import com.example.nodoandroidroompractice.data.NoRoomDataBase;
import com.example.nodoandroidroompractice.model.NoDo;

import java.util.List;

import javax.xml.transform.Result;

public class NoDoRepository {

    private NoDoDao noDoDao;
    private LiveData<List<NoDo>> allNoDos;

    public NoDoRepository(Application application) { //We are passing global context here cuz we want to make sure that the life of our activity or whatever we are doing inside of our Android room is beyond activity life-cycle , view and everything.

        NoRoomDataBase db = NoRoomDataBase.getDataBase(application);
        noDoDao = db.noDoDao();
        allNoDos = noDoDao.getAllNoDos();

    }

    public LiveData<List<NoDo>> getAllNoDos(){
        return allNoDos;
    }

    public void insert(NoDo noDo){
        new insertAsyncTask(noDoDao).execute(noDo);  // So that we don't block the main thread.
    }

    private class insertAsyncTask extends AsyncTask<NoDo,Void,Void> {
        private NoDoDao asyncTaskDao;
        public insertAsyncTask(NoDoDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(NoDo... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
