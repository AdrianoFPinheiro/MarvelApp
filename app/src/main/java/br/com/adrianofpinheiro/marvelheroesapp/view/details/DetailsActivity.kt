package br.com.adrianofpinheiro.marvelheroesapp.view.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.adrianofpinheiro.marvelheroesapp.R
import br.com.adrianofpinheiro.marvelheroesapp.model.Details


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (savedInstanceState == null) {
            val details = Details(name = intent.getStringExtra("name"),
                description = intent.getStringExtra("description"),
                comics = intent.getStringExtra("comics"),
                series = intent.getStringExtra("series"),
                events = intent.getStringExtra("events"),
                stories = intent.getStringExtra("stories"),
                image = intent.getStringExtra("image"),
                linkComics = intent.getStringExtra("comiclink"),
                linkDetails = intent.getStringExtra("detail"),
                linkWiki = intent.getStringExtra("wiki"))

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(details))
                .commitNow()
        }
    }

}
