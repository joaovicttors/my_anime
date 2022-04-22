package com.joaovicttors.data.models

abstract class AnimeModel {
    abstract val id: Int
    abstract val format: Int
    abstract val status: Int
    abstract val titles: Map<String, String>
    abstract val descriptions: Map<String, String>
    abstract val coverImage: String
    abstract val score: Float
}