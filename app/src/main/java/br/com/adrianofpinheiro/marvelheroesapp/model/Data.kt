package br.com.adrianofpinheiro.marvelheroesapp.model

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)