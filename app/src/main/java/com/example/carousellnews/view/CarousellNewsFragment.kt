package com.example.carousellnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.carousellnews.R
import com.example.carousellnews.core.util.Result
import com.example.carousellnews.databinding.FragmentCaarousellNewsListBinding
import com.example.carousellnews.entities.News
import com.example.carousellnews.ext.gone
import com.example.carousellnews.ext.visible
import com.example.carousellnews.viewmodel.CarousellNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CarousellNewsFragment : Fragment() {

    private val viewModel: CarousellNewsViewModel by viewModels()
    private lateinit var binding: FragmentCaarousellNewsListBinding
    private var adapter: NewsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaarousellNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        lifecycleScope.launchWhenCreated {
            viewModel.resultMutableStateFlow.collect {
                when (it) {
                    is Result.InProgress -> {
                        binding.progressBar.visible()
                        binding.errorLayout.root.gone()
                    }
                    is Result.Error -> {
                        binding.progressBar.gone()
                        binding.errorLayout.root.visible()
                    }
                    is Result.Success -> {
                        binding.progressBar.gone()
                        binding.errorLayout.root.gone()
                        it.data?.let { news ->
                            initAdapter(news)
                        }

                    }
                }
            }
        }
    }

    private fun initClickListener() {
        binding.errorLayout.retryButton.setOnClickListener {
            viewModel.fetchCarousellNews()
        }
    }

    private fun initAdapter(news: List<News>) {
        adapter = NewsAdapter(news)
        binding.list.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sort, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_popular -> {
                sortByPopular()
            }
            R.id.action_recent -> {
                sortByRecent()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortByPopular() = adapter?.sortByPopular()

    private fun sortByRecent() = adapter?.sortByRecent()

}