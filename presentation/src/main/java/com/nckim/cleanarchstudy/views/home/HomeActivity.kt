package com.nckim.cleanarchstudy.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.base.BaseActivity
import com.nckim.cleanarchstudy.databinding.ActivityHomeBinding
import com.nckim.cleanarchstudy.views.home.github.GithubSearchFragment
import com.nckim.cleanarchstudy.views.home.movie.MovieSearchFragment
import com.nckim.cleanarchstudy.views.search.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel : HomeViewModel by viewModels()
    private val movieFragment by lazy {  MovieSearchFragment() }
    private val githubFragment by lazy { GithubSearchFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(movieFragment)
        initBottomNavigationView()
    }

    private fun initBottomNavigationView(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    changeFragment(movieFragment)
                    true
                }
                R.id.page_2 -> {
                    changeFragment(githubFragment)
                    true
                }
                else -> false
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }


}