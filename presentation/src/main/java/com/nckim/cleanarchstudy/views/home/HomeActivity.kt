package com.nckim.cleanarchstudy.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.views.home.github.GithubSearchFragment
import com.nckim.cleanarchstudy.views.home.movie.MovieSearchFragment

class HomeActivity : AppCompatActivity() {

    private val movieFragment = MovieSearchFragment()
    private val githubFragment = GithubSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initBottomNavigationView()
    }

    fun initBottomNavigationView(){
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }


}