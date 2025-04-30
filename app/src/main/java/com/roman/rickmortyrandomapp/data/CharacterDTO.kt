package com.roman.rickmortyrandomapp.data

import kotlinx.serialization.SerialName

data class CharacterDTO(

    @SerialName("id") var id: Int,
    @SerialName("name") var name: String,
    @SerialName("status") var status: String,
    @SerialName("species") var species: String,
    @SerialName("type") var type: String,
    @SerialName("gender") var gender: String,
    @SerialName("origin") var origin: Origin,
    @SerialName("location") var location: Location,
    @SerialName("image") var image: String,
    @SerialName("episode") var episode: List<String> = listOf(),
    @SerialName("url") var url: String,
    @SerialName("created") var created: String? = null

)

data class Origin(
    @SerialName("name") var name: String,
    @SerialName("url") var url: String? = null
)

data class Location(

    @SerialName("name") var name: String,
    @SerialName("url") var url: String? = null

)