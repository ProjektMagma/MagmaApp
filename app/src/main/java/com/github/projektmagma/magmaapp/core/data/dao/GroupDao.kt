package com.github.projektmagma.magmaapp.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.projektmagma.magmaapp.core.data.models.GroupWithClassAndQualificationRelation

@Dao
interface GroupDao {
    @Transaction
    @Query("SELECT Groups.group_id, Groups.group_sign, Groups.class_id, Groups.qualification_id, Classes.class_id, Classes.name, Qualifications.qualification_id, Qualifications.name FROM Groups INNER JOIN Classes ON Groups.class_id = Classes.class_id INNER JOIN Qualifications ON Groups.qualification_id = Qualifications.qualification_id")
    fun getGroups(): List<GroupWithClassAndQualificationRelation>
}