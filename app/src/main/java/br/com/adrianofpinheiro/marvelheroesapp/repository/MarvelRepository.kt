package br.com.adrianofpinheiro.marvelheroesapp.repository

import android.arch.lifecycle.LiveData
import br.com.adrianofpinheiro.marvelheroesapp.api.OnGetMarvelCallback
import br.com.adrianofpinheiro.marvelheroesapp.model.ReturnData

interface MarvelRepository{
    //name: String, apiKey: String, ts: String, hash: String,
    fun getCharacter(offset: Int, callback: OnGetMarvelCallback)
    fun getHeroes(): LiveData<ReturnData>
}