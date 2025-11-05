package edu.charlotte.assignment11;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.charlotte.assignment11.models.Task;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    Task getTask(long id);

    @Delete
    void delete(Task task);

    @Insert
    void insertAll(Task... task);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("SELECT * FROM tasks ORDER BY priority_value ASC")
    List<Task> getTasksSortedAsc();

    @Query("SELECT * FROM tasks ORDER BY priority_value DESC")
    List<Task> getTasksSortedDesc();

}
