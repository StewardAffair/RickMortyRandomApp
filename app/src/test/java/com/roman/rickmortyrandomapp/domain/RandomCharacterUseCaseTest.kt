package com.roman.rickmortyrandomapp.domain

import com.roman.rickmortyrandomapp.data.CharacterRepository
import com.roman.rickmortyrandomapp.data.testCharacter
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RandomCharacterUseCaseTest {

    lateinit var useCase: RandomCharacterUseCase

    val repo : CharacterRepository = mock()

    @Before
    fun setup() {
        useCase = RandomCharacterUseCase(repo)
    }

    @Test
    fun `when repo return result, use case return result`(): Unit = runBlocking {
        whenever(repo.getCharacter(any())).thenReturn(testCharacter)

        val character = useCase()

        assertEquals(character.id, testCharacter.id)
    }
}