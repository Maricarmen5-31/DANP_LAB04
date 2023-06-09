package com.example.danp_lab04.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.danp_lab04.entities.Country

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contries: List<Country>)

    @Insert
    suspend fun insertCountry(country: Country)

    @Query("SELECT * FROM country")
    suspend fun getAll(): List<Country>

    @Query("SELECT * FROM country")
    fun pagingSource(): PagingSource<Int, Country>

    @Query("DELETE FROM country")
    suspend fun clearAll()
}