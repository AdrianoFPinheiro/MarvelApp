package br.com.adrianofpinheiro.marvelheroesapp.api

import br.com.adrianofpinheiro.marvelheroesapp.model.ReturnData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi{

    @GET("characters")
    fun getCharacters(@Query("ts") ts: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("limit") limit: String = "99",
                      @Query("offset") offset: String = "203"): Call<ReturnData>

}

interface OnGetMarvelCallback{
    fun onSuccess(marvelResponse: ReturnData)
    fun onError()
}