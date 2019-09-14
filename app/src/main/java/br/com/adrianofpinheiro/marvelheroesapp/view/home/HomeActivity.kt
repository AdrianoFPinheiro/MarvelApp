package br.com.adrianofpinheiro.marvelheroesapp.view.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.adrianofpinheiro.marvelheroesapp.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    HomeFragment.newInstance()
                )
                .commitNow()
        }
    }

}
