package com.github.projektmagma.magmaapp.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.projektmagma.magmaapp.core.data.dao.UserDao
import com.github.projektmagma.magmaapp.core.data.models.ClassesEntity
import com.github.projektmagma.magmaapp.core.data.models.GroupEntity
import com.github.projektmagma.magmaapp.core.data.models.QualificationEntity
import com.github.projektmagma.magmaapp.core.data.models.UserEntity
import com.github.projektmagma.magmaapp.core.data.models.UserRolesEntity

@Database(entities = [UserRolesEntity::class, GroupEntity::class, UserEntity::class, ClassesEntity::class, QualificationEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}