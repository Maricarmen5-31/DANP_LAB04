package com.example.danp_lab04.model

import android.util.Log
import com.example.danp_lab04.entities.Country

class CountryRepository(
    private val appDatabase: AppDatabase
) {

    suspend fun insertCountry(country: Country) {
        Log.d("REPOSITORY", "Estoy en el repositorio ekcnfeinbibnfkjlxn")
        return appDatabase.countryDao().insertCountry(country)
    }

    suspend fun insertCountries(countries: List<Country>) {
        return appDatabase.countryDao().insertAll(countries)
    }

    suspend fun getCountries() : List<Country> {
        return appDatabase.countryDao().getAll()
    }

    suspend fun getCountries(nextPageNumber: Int): List<Country> {

        Log.d("nextPageNumber:",nextPageNumber.toString())

        val countries = arrayListOf<Country>()
        var country: Country
        val start: Int = 100 * nextPageNumber
        val end = start + 20

        for (i in start..end) {
            //country = Country(countryId = i, );
            //countries.json.add(country)
        }
        return countries
    }
}