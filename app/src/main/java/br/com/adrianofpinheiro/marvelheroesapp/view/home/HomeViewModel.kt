package br.com.adrianofpinheiro.marvelheroesapp.view.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import br.com.adrianofpinheiro.marvelheroesapp.api.OnGetMarvelCallback
import br.com.adrianofpinheiro.marvelheroesapp.model.ReturnData
import br.com.adrianofpinheiro.marvelheroesapp.repository.MarvelRepository
import br.com.adrianofpinheiro.marvelheroesapp.repository.MarvelRepositoryImpl

class HomeViewModel(private val repository:MarvelRepository = MarvelRepositoryImpl())
    : ViewModel() {

    private val heroesList: MutableLiveData<ReturnData> = MutableLiveData()
    fun getHeroesList() = heroesList

    fun verifyConnectivity(activity: HomeActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getHeroes(offset: Int) {
        repository.getCharacter(offset, object : OnGetMarvelCallback {

            override fun onSuccess(response: ReturnData) {
                Log.d("Reponse", "It's Ok!!!")
                heroesList.value = response
            }

            override fun onError() {
                Log.e("ErrorViewModel", "Error in viewmodel call")
            }
        })
    }
}
