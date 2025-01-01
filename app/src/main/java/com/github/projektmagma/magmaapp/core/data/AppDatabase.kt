package com.github.projektmagma.magmaapp.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.projektmagma.magmaapp.core.data.dao.UserDao
import com.github.projektmagma.magmaapp.core.data.models.Group
import com.github.projektmagma.magmaapp.core.data.models.User
import com.github.projektmagma.magmaapp.core.data.models.UserRoles

@Database(entities = [UserRoles::class, Group::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MagmaAppDatabase.db"
            )
                .createFromAsset("MagmaAppDatabase.db")
                .build()
                .also { INSTANCE = it }
        }
    }
}