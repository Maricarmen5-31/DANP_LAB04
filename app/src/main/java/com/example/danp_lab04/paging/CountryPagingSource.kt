package com.example.danp_lab04.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.danp_lab04.entities.Country
import com.example.danp_lab04.model.CountryRepository
import java.io.IOException

class CountryPagingSource(
    private val countryRepository: CountryRepository
) : PagingSource<Int, Country>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val nextPageNumber = params.key ?: 1
            val countries = countryRepository.getCountries()

            return LoadResult.Page(
                data = countries ?: listOf(),
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else 1,
                nextKey = nextPageNumber + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}