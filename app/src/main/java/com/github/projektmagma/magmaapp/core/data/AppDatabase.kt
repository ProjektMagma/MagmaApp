package com.github.projektmagma.magmaapp.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.projektmagma.magmaapp.core.data.dao.UserDao
import com.github.projektmagma.magmaapp.core.data.models.Classes
import com.github.projektmagma.magmaapp.core.data.models.Group
import com.github.projektmagma.magmaapp.core.data.models.Qualification
import com.github.projektmagma.magmaapp.core.data.models.User
import com.github.projektmagma.magmaapp.core.data.models.UserRoles

@Database(entities = [UserRoles::class, Group::class, User::class, Classes::class, Qualification::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}