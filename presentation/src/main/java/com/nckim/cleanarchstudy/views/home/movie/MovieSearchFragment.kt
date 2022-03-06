package com.nckim.cleanarchstudy.views.home.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.databinding.ActivityMovieSearchBinding
import com.nckim.cleanarchstudy.views.home.HomeViewModel

class MovieSearchFragment : Fragment() {

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding : ActivityMovieSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_movie_search, container, false)

        return binding.root
//        return inflater.inflate(R.layout.fragm, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}