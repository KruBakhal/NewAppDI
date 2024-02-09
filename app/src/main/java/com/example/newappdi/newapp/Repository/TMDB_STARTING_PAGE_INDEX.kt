package com.example.newappdi.newapp.Repository

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Repository.API_Interface
import com.example.newappdi.NewsApp.Utils.Constant
import com.example.newappdi.newapp.DI.network.NETWORK_PAGE_SIZE
import com.example.newappdi.newapp.Model.MovieResponse
import java.io.IOException
import javax.inject.Inject

private const val TMDB_STARTING_PAGE_INDEX = 1


class MoviesPagingSource @Inject constructor(
    val service: API_Interface
) : PagingSource<Int, Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getBreakingNews1(
                countryCode = "us",
                pageNumber = pageIndex, apiKey = Constant.API_KEY
            )
            val movies = response.body()!!.articles
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + 1
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}