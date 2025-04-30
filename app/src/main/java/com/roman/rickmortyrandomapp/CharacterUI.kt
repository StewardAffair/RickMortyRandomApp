package com.roman.rickmortyrandomapp

data class CharacterUI (
    val id: Int,
    val url : String,
    val name: String,
    val status: Status,
    val episodes: List<Episode>
)

enum class Status {
    ALIVE, DEAD
}

data class Episode (
    val id: Int,
    val name: String,
    val url: String
)