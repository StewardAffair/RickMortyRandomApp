package com.roman.rickmortyrandomapp.domain

import com.roman.rickmortyrandomapp.data.CharacterDTO
import com.roman.rickmortyrandomapp.data.CharacterRepository
import kotlin.random.Random

class RandomCharacterUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke() : CharacterDTO {
        val randomId = Random.nextInt(0, 826)
        return repository.getCharacter(randomId)
    }
}