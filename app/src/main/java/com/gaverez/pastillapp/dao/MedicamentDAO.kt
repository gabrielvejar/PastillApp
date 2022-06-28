package com.gaverez.pastillapp.dao

import androidx.room.*
import com.gaverez.pastillapp.models.MedicamentEntity

@Dao
interface MedicamentDAO {
    @Query("SELECT * FROM medicaments WHERE user_id = :userId")
    fun findAll(userId: Long): List<MedicamentEntity>

    @Query("SELECT * FROM medicaments WHERE id = :id")
    fun findById(id: Long): MedicamentEntity?

    @Insert
    fun insert(medicament: MedicamentEntity)

    @Update
    fun update(medicament: MedicamentEntity)

    @Query("DELETE FROM medicaments WHERE id = :id")
    fun delete(id: Long)
}
