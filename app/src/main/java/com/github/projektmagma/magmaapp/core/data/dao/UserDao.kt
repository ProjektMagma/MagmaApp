package com.github.projektmagma.magmaapp.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.projektmagma.magmaapp.core.data.models.User
import com.github.projektmagma.magmaapp.core.data.models.UserWithRoleAndGroup

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT " +
            "Users.user_id, " +
            "Users.pesel, " +
            "Users.password, " +
            "Users.first_name, " +
            "Users.second_name, " +
            "UserRoles.role_id, " +
            "UserRoles.name " +
            "FROM Users " +
            "INNER JOIN UserRoles ON Users.role_id = UserRoles.role_id " +
            "INNER JOIN `Groups` ON Users.group_id = `Groups`.group_id")
    fun getUsers(): List<UserWithRoleAndGroup>
}