package com.roman.rickmortyrandomapp

import com.roman.rickmortyrandomapp.data.Api
import com.roman.rickmortyrandomapp.data.CharacterRepository
import com.roman.rickmortyrandomapp.data.CharacterRepositoryImpl
import com.roman.rickmortyrandomapp.domain.RandomCharacterUseCase
import com.roman.rickmortyrandomapp.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Api> {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }

    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    single<RandomCharacterUseCase> { RandomCharacterUseCase(get()) }

    viewModel { MainViewModel(get()) }

}