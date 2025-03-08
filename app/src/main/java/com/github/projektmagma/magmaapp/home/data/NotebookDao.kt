package com.github.projektmagma.magmaapp.home.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.projektmagma.magmaapp.home.data.model.NotebookEntity

@Dao
interface NotebookDao {

    @Upsert
    fun upsertNotebook(notebook: NotebookEntity)

    @Delete
    fun deleteNotebook(notebook: NotebookEntity)

    @Query("SELECT * FROM notebook")
    fun getNotebooks(): List<NotebookEntity>

    @Query("SELECT * FROM notebook WHERE id = :id")
    fun getNotebookById(id: String): NotebookEntity
}