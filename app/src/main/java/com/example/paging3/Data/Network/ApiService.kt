package com.example.paging3.Data.Network

import com.example.paging3.Data.Dogs
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.thedogapi.com"
    }

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") q: Int,
        @Query("from") from: Int,
    ): List<Dogs>

/* companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("photos")
    suspend fun getAllData(
        @Query("_start") _start: Int,
        @Query("_limit") _limit: Int,
    ): List<Dogs>

 */
}