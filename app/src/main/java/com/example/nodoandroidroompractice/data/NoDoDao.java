package com.example.nodoandroidroompractice.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nodoandroidroompractice.model.NoDo;

import java.util.List;

@Dao
public interface NoDoDao {

    @Insert
    public void insert(NoDo noDo);

    @Query("DELETE FROM noDo_table")
    public void deleteAll();

    @Query("DELETE FROM noDo_table WHERE id = :id")
    public int deleteNoDo(int id);

    @Query("UPDATE noDo_table SET noDo_col = :noDoText WHERE id = :id")
    public int updateNoDoItem(int id , String noDoText);

    @Query("SELECT * FROM noDo_table ORDER BY noDo_col DESC")
    LiveData<List<NoDo>> getAllNoDos();
}
