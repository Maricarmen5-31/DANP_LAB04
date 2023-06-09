package com.example.danp_lab04.paging

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.danp_lab04.entities.Country
import com.example.danp_lab04.model.AppDatabase
import com.example.danp_lab04.model.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

@HiltViewModel
class CountryViewModel
@Inject constructor(
    private val repo: CountryRepository
): ViewModel() {

    fun addCountriesFromJason(context: Context) {

        try {
            val obj = JSONObject(readJSONFromAsset(context))
            val userArray = obj.getJSONArray("countries")

            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)

                val name_en = userDetail.getString("name_en")
                val name_es = userDetail.getString("name_es")
                val continent_en = userDetail.getString("continent_en")
                val continent_es = userDetail.getString("continent_es")
                val capital_en = userDetail.getString("capital_en")
                val capital_es = userDetail.getString("capital_es")
                val dial_code = userDetail.getString("dial_code")
                val code_2 = userDetail.getString("code_2")
                val code_3 = userDetail.getString("code_3")
                val tld = userDetail.getString("tld")
                val km2 = userDetail.getInt("km2")

                viewModelScope.launch(Dispatchers.IO){
                    repo.insertCountry(Country(0, name_en, name_es, continent_en, continent_es, capital_en, capital_es, dial_code, code_2, code_3, tld, km2))
                }
            }
        }
        catch (e: JSONException) {
            Log.d("ERROR",e.toString())
        }
    }

    fun readJSONFromAsset(context: Context): String {
        val json: String?
        try {
            val inputStream = context.resources.assets.open("countries")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            return "ERROR: $ex"
        }
        return json
    }

    fun items(): Flow<PagingData<Country>> {
        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            CountryPagingSource(repo)
        }.flow.cachedIn(viewModelScope)
        return pager
    }
}