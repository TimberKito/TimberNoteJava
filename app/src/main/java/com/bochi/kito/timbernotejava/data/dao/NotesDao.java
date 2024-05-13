package com.bochi.kito.timbernotejava.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.bochi.kito.timbernotejava.data.entity.Notes;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNote(Notes... items);

    @Update
    void updateNote(Notes... items);

    @Delete
    void deleteNote(Notes... items);

    @Query("DELETE FROM t_notes")
    void deleteAllNotes();

    @Query("SELECT * FROM t_notes ORDER BY id DESC")
    List<Notes> getAllNotes();

}
