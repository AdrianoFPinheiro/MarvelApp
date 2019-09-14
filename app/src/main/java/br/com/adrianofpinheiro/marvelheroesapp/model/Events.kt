package br.com.adrianofpinheiro.marvelheroesapp.model

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)