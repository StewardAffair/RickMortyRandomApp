package com.roman.rickmortyrandomapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.rickmortyrandomapp.CharacterUI
import com.roman.rickmortyrandomapp.Episode
import com.roman.rickmortyrandomapp.Status
import com.roman.rickmortyrandomapp.data.CharacterDTO
import com.roman.rickmortyrandomapp.domain.RandomCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(private val useCase: RandomCharacterUseCase) : ViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState(isLoading = false, characterUI = null))
    val state = _state.asStateFlow()

    fun onButtonClicked() {
        viewModelScope.launch {
            _state.value = UIState(
                isLoading = true,
                characterUI = null
            )

            val characterDTO = useCase()

          _state.value = UIState(
              isLoading = false,
              characterUI = characterDTO.toUi()
          )
        }
    }

    private fun CharacterDTO.toUi(): CharacterUI {
        return CharacterUI (
            id = id,
            url = image,
            name = name,
            status = status.toUiStatus(),
            episodes = episode.map {
                Episode(
                    id = Random.nextInt(),
                    url = it,
                    name = "Episode ${it.substringAfterLast(delimiter = '/')}"
                )
            }
        )
    }

    private fun String.toUiStatus() : Status {
        return when(this) {
            "Dead" -> Status.DEAD
            "Alive" -> Status.ALIVE
            else -> Status.DEAD
        }
    }
}

data class UIState(
    val isLoading: Boolean,
    val characterUI: CharacterUI?
)