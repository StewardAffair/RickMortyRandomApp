package com.roman.rickmortyrandomapp.data

interface CharacterRepository {

    suspend fun getCharacter(id: Int) : CharacterDTO
}

class CharacterRepositoryImpl(private val api: Api) : CharacterRepository {

    override suspend fun getCharacter(id: Int): CharacterDTO {
        return api.getCharacter(id)
    }

}