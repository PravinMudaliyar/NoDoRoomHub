package com.example.nodoandroidroompractice.data;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nodoandroidroompractice.model.NoDo;

@Database(entities = {NoDo.class} , version = 1)
public abstract class NoRoomDataBase extends RoomDatabase {

    private static volatile NoRoomDataBase INSTANCE ;
    public abstract NoDoDao noDoDao();

    public static NoRoomDataBase getDataBase(final Context context){       //Singleton
        if(INSTANCE == null){
            synchronized (NoRoomDataBase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() , NoRoomDataBase.class , "noDo_dataBase" )
                            .addCallback(roomDataBaseCallBack)
                            .build();

                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDataBaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void , Void , Void> {
        private final NoDoDao noDoDao;

        public PopulateDbAsync(NoRoomDataBase db) {
            noDoDao = db.noDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //noDoDao.deleteAll();

            /*NoDo noDo = new NoDo("Buy something");
            noDoDao.insert(noDo);

            noDo = new NoDo("Gibberish");
            noDoDao.insert(noDo);   */

            return null;
        }
    }
}
