package com.github.projektmagma.magmaapp.home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.projektmagma.magmaapp.home.data.model.NotebookEntity

@Database(
    entities = [NotebookEntity::class],
    version = 1
)
abstract class NotebooksDatabase: RoomDatabase() {
    abstract fun notebookDao(): NotebookDao
}