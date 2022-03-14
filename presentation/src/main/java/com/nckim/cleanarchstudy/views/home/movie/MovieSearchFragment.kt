package com.nckim.cleanarchstudy.views.home.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.databinding.ActivityMovieSearchBinding
import com.nckim.cleanarchstudy.views.home.HomeActivity
import com.nckim.cleanarchstudy.views.search.MovieAdapter
import com.nckim.cleanarchstudy.views.search.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchFragment : Fragment() {

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    private val viewModel: MovieSearchViewModel by viewModels()
    private lateinit var binding : ActivityMovieSearchBinding
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_movie_search, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        initAdapter()
        initViewModelCallback()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    private fun initAdapter(){
        movieAdapter = MovieAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                activity?.let { it1 -> it.resolveActivity(it1.packageManager) } != null
            }?.run (this::startActivity)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)))
        }
        binding.recyclerMovie.adapter = movieAdapter
    }


    private fun initViewModelCallback() {
        with(viewModel) {
            toastMsg.observe(viewLifecycleOwner, Observer {
                val act = activity as HomeActivity
                when (toastMsg.value) {
                    MovieSearchViewModel.MessageSet.LAST_PAGE -> act.showToast(getString(
                        R.string.last_page_msg))
                    MovieSearchViewModel.MessageSet.EMPTY_QUERY -> act.showToast(getString(
                        R.string.search_input_query_msg))
                    MovieSearchViewModel.MessageSet.NETWORK_NOT_CONNECTED -> act.showToast(getString(
                        R.string.network_error_msg))
                    MovieSearchViewModel.MessageSet.SUCCESS -> act.showToast(getString(
                        R.string.load_movie_success_msg))
                    MovieSearchViewModel.MessageSet.NO_RESULT -> act.showToast(getString(
                        R.string.no_movie_error_msg))
                    MovieSearchViewModel.MessageSet.ERROR -> act.showToast(getString(
                        R.string.error_msg))
                    MovieSearchViewModel.MessageSet.LOCAL_SUCCESS -> act.showToast(getString(
                        R.string.local_db_msg))
                }
            })
        }
    }
}