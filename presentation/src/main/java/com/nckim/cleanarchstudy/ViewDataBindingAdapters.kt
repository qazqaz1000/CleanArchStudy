package com.nckim.cleanarchstudy

import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nckim.cleanarchstudy.utils.EndlessRecyclerViewScrollListener
import com.nckim.cleanarchstudy.views.search.MovieAdapter
import com.nckim.cleanarchstudy.views.search.MovieSearchViewModel
import com.nckim.domain.model.movie.Movie


@BindingAdapter("htmlText")
fun TextView.setHtmlText(html : String){
    text = Html.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String){
    Glide.with(this).load(url)
        .placeholder(R.drawable.ic_default)
        .into(this)
}

@BindingAdapter("movieRating")
fun RatingBar.setMovieRating(score: String){
    rating = (score.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("setItems")
fun RecyclerView.setAdapterItem(items: MutableList<Movie>?){
    items?.let{
        (adapter as MovieAdapter).submitList(it.toMutableList())
    }
}


@BindingAdapter("endlessScroll")
fun RecyclerView.setEndlessScroll(vm: MovieSearchViewModel){
    val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager){
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            vm.requestPagingMovie(totalItemsCount + 1)
        }
    }
    addOnScrollListener(scrollListener)
}