package br.com.adrianofpinheiro.marvelheroesapp.view.details


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.marvelheroesapp.R
import br.com.adrianofpinheiro.marvelheroesapp.model.Details
import br.com.adrianofpinheiro.marvelheroesapp.model.Links
import kotlinx.android.synthetic.main.carousel_layout.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), DetailsAdapter.DetailsListener {

    companion object {

        lateinit var detalhes : Details

        fun newInstance(details: Details): DetailsFragment{
            this.detalhes = details
            return DetailsFragment()
        }

    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var adapter: DetailsAdapter
    private lateinit var listLinks: MutableList<Links>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        adapter = DetailsAdapter(mutableListOf(), this)
        recycler_links.layoutManager = LinearLayoutManager(context)
        recycler_links.adapter = adapter

        viewModel.configureScreen(detalhes, image_thumbnail, text_name, text_description,
            text_comics_value, text_events_value, text_series_value, text_stories_value)

        adapter.updateList(viewModel.populateLinkList(detalhes.linkComics,
            detalhes.linkDetails,
            detalhes.linkWiki,
            context))

    }

    override fun openHyperLink(links: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links))
        startActivity(browserIntent)
    }

}

