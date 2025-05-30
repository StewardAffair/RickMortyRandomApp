package com.roman.rickmortyrandomapp.presentation

import com.roman.rickmortyrandomapp.data.testCharacter
import com.roman.rickmortyrandomapp.domain.RandomCharacterUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.setMain

class MainViewModelTest {

    lateinit var viewModel: MainViewModel

    val useCase : RandomCharacterUseCase = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `when use case return result, state shows character`() : Unit = runBlocking {
        whenever(useCase()).thenReturn(testCharacter)

        viewModel.onButtonClicked()

        assertEquals(viewModel.state.value.characterUI!!.id, testCharacter.id)
    }
}