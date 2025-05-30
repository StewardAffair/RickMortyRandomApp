package com.roman.rickmortyrandomapp.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CharacterRepositoryTest {
    
    lateinit var characterRepository: CharacterRepository
    
    val api: Api = mock()
    
    @Before
    fun setup() {
        characterRepository = CharacterRepositoryImpl(api)
    }
    
    @Test
    fun `when api returns result, repo also return that result`(): Unit = runBlocking {
        whenever(api.getCharacter(any())).thenReturn(testCharacter)

        val characterDTO = characterRepository.getCharacter(-1)

        assertEquals(characterDTO.id, testCharacter.id)
    }

    @Test(expected = Exception::class)
    fun `when api throws, repo also throws error`(): Unit = runBlocking {
        whenever(api.getCharacter(any())).thenThrow(Exception())

        characterRepository.getCharacter(-1)
    }

}


val testCharacter = CharacterDTO(
    id = -1,
    name = "",
    status = "",
    species = "",
    type = "",
    gender = "",
    origin = Origin(""),
    location = Location(""),
    image = "",
    episode = emptyList(),
    url = "",
    created = ""
)