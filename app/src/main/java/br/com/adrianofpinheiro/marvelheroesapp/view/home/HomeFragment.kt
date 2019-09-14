package br.com.adrianofpinheiro.marvelheroesapp.view.home


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.marvelheroesapp.R
import br.com.adrianofpinheiro.marvelheroesapp.model.Item
import kotlinx.android.synthetic.main.fragment_home.*
import br.com.adrianofpinheiro.marvelheroesapp.model.Result
import br.com.adrianofpinheiro.marvelheroesapp.view.details.DetailsActivity


class HomeFragment : Fragment(),
    MarvelCharacterAdapter.AdapterListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var viewModel: HomeViewModel =
        HomeViewModel()
    private lateinit var adapter: MarvelCharacterAdapter

    private var offset: Int = 150

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureObservers()
        adapter = MarvelCharacterAdapter(mutableListOf(), this)
        //adapter = MarvelCharacterAdapter(itemReciclerview(), this)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        recycler_view_characters.layoutManager = GridLayoutManager(context, 2)
        recycler_view_characters.itemAnimator = DefaultItemAnimator()
        recycler_view_characters.adapter = adapter

        viewModel.getHeroes(offset)

    }

    fun configureObservers(){
        showLoading()

        viewModel.getHeroesList().observe(this, Observer { heroes ->
            heroes?.let {
                adapter.updateList(heroes.data.results as MutableList<Result>)
                if (heroes.data.count > 0){
                    updateOffset()
                }
            }
        })
        hideLoading()
    }

    private fun updateOffset() {
        offset += 100
        viewModel.getHeroes(offset)
    }

    fun showLoading(){
        progressbar_home.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progressbar_home.visibility = View.GONE
    }

    override fun showDetails(char: Result) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("image", char.thumbnail.path + "." + char.thumbnail.extension)
        intent.putExtra("name", char.name)
        intent.putExtra("description", char.description)
        intent.putExtra("comics", char.comics.available.toString())
        intent.putExtra("series", char.series.available.toString())
        intent.putExtra("stories", char.stories.available.toString())
        intent.putExtra("events", char.events.available.toString())

        char.urls.forEach{
            intent.putExtra(it.type, it.url)
        }

        startActivity(intent)
        Log.d("CLick", "clicado no item")
    }

    private fun itemReciclerview(): List<Item> {
        return listOf(
            Item(
                "Teste",
                "Mock"
            ),
            Item(
                "Teste",
                "Mock"
            ),
            Item(
                "Teste",
                "Mock"
            )
        )
    }

}