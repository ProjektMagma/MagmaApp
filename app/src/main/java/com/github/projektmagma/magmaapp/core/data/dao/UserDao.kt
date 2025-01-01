package com.github.projektmagma.magmaapp.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.projektmagma.magmaapp.core.data.models.User

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM Users")
    fun getUsers(): List<User>
}