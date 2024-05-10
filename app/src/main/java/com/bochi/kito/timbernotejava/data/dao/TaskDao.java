package com.bochi.kito.timbernotejava.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.bochi.kito.timbernotejava.data.entity.Task;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(Task... items);

    @Update
    void updateTask(Task... items);

    @Delete
    void deleteTask(Task... items);

    @Query("DELETE FROM t_task")
    void deleteAllTasks();

    @Query("SELECT * FROM t_task ORDER BY id DESC")
    Flow<List<Task>> getAllTasks();

    @Query("SELECT * FROM t_task WHERE systemTimeL LIKE :sysytemTimeL")
    Flow<Task> searchTask(String sysytemTimeL);

}
