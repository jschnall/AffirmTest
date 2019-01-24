package com.sporksoft.surfscrib.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.Navigation
import com.sporksoft.surfscrib.R
import com.sporksoft.surfscrib.ui.adapter.FeedAdapter
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_feed.*
import android.app.SearchManager
import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sporksoft.surfscrib.databinding.FragmentFeedBinding
import com.sporksoft.surfscrib.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private val LOGTAG = FeedFragment::class.java.simpleName

    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        adapter = FeedAdapter()
        binding.recyclerView.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener { viewModel.updateItems(true) }

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity)
                .colorResId(R.color.grey_light)
                .sizeResId(R.dimen.divider_height)
                .marginResId(R.dimen.divider_left_margin, R.dimen.divider_right_margin).build())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_feed, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val activity = context as Activity
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = searchItem?.getActionView() as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.updateQuery(query)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi(adapter: FeedAdapter) {
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            swipeRefreshLayout.isRefreshing = false
            adapter.submitList(items)
        })
    }
}
