package com.nckim.cleanarchstudy.views.search

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.base.BaseActivity
import com.nckim.cleanarchstudy.databinding.ActivityMovieSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchActivity : BaseActivity<ActivityMovieSearchBinding>(R.layout.activity_movie_search) {
    private val movieSearchViewModel : MovieSearchViewModel by viewModels()
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = movieSearchViewModel
        initViewModelCallback()
        initAdapter()

    }

    private fun initAdapter(){
        movieAdapter = MovieAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run (this::startActivity)
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)))
        }
        binding.recyclerMovie.adapter = movieAdapter
    }


    private fun initViewModelCallback() {
        with(movieSearchViewModel) {
            toastMsg.observe(this@MovieSearchActivity, Observer {
                when (toastMsg.value) {
                    MovieSearchViewModel.MessageSet.LAST_PAGE -> showToast(getString(R.string.last_page_msg))
                    MovieSearchViewModel.MessageSet.EMPTY_QUERY -> showToast(getString(R.string.search_input_query_msg))
                    MovieSearchViewModel.MessageSet.NETWORK_NOT_CONNECTED -> showToast(getString(R.string.network_error_msg))
                    MovieSearchViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.load_movie_success_msg))
                    MovieSearchViewModel.MessageSet.NO_RESULT -> showToast(getString(R.string.no_movie_error_msg))
                    MovieSearchViewModel.MessageSet.ERROR -> showToast(getString(R.string.error_msg))
                    MovieSearchViewModel.MessageSet.LOCAL_SUCCESS -> showToast(getString(R.string.local_db_msg))
                }
            })
        }
    }

}