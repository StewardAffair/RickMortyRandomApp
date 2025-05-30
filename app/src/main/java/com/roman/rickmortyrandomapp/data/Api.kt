package com.roman.rickmortyrandomapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int) : CharacterDTO


    companion object {
        fun create() : Api {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}