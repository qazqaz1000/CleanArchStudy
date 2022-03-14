package com.nckim.cleanarchstudy.views.home.github

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.databinding.GithubSearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubSearchFragment : Fragment() {

    companion object {
        fun newInstance() = GithubSearchFragment()
    }

    private val viewModel: GithubSearchViewModel by viewModels()
    private lateinit var binding : GithubSearchFragmentBinding;
    private lateinit var githubAdapter: GithubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.github_search_fragment, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        initAdapter()
        return binding.root
//        return inflater.inflate(R.layout.github_search_fragment, container, false)
    }

    private fun initAdapter(){
        githubAdapter = GithubAdapter { githubRepositoryModel ->
            Log.e("NCTEST", "######## test")
        }
        binding.recyclerGithub.adapter =  githubAdapter
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}