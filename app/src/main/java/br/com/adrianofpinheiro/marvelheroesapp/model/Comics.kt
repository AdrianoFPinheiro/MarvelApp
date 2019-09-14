package br.com.adrianofpinheiro.marvelheroesapp.model

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)