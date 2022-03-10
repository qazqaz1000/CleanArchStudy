package com.nckim.cleanarchstudy.views.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nckim.cleanarchstudy.base.BaseViewModel
import com.nckim.cleanarchstudy.utils.NetworkManager
import com.nckim.data.utils.LAST_PAGE
import com.nckim.domain.model.movie.Movie
import com.nckim.domain.usecase.GetLocalMoviesUseCase
import com.nckim.domain.usecase.GetMoviesUseCase
import com.nckim.domain.usecase.GetPagingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val getPagingMoviesUseCase: GetPagingMoviesUseCase,
    private val networkManager: NetworkManager
) : BaseViewModel(){

    private var currentQuery: String = ""
    val query = MutableLiveData<String>()
    private val _movieList = MutableLiveData<MutableList<Movie>>()
    private val _toastMsg = MutableLiveData<MessageSet>()

    val movieList: LiveData<MutableList<Movie>> get() = _movieList
    val toastMsg: LiveData<MessageSet> get() = _toastMsg
    //first movie 검색
    fun requestMovie(){
        currentQuery = query.value.toString().trim()
        if(currentQuery.isEmpty()){
            _toastMsg.value = MessageSet.EMPTY_QUERY
            return
        }
        if(!checkNetworkState()){
            return
        }
        compositeDisposable.add(
            getMoviesUseCase.excute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{showProgress()}
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    if(movies.isEmpty()){
                        _toastMsg.value = MessageSet.NO_RESULT
                    }else{
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                },{
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestPagingMovie(offest: Int){
        if(!checkNetworkState()) return
        compositeDisposable.add(
            getPagingMoviesUseCase.excute(currentQuery, offest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    val pagingMovieList = _movieList.value
                    pagingMovieList?.addAll(movies)
                    _movieList.value = pagingMovieList!!
                    _toastMsg.value = MessageSet.SUCCESS
                }, {
                    when(it.message){
                        LAST_PAGE -> _toastMsg.value = MessageSet.LAST_PAGE
                        else -> {
                            _toastMsg.value = MessageSet.ERROR
                        }
                    }
                })
        )
    }

    override fun onEndlessScroll(offset: Int){
        requestPagingMovie(offset)
    }

    private fun requestLocalMovies(){
        compositeDisposable.add(
            getLocalMoviesUseCase.excute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{showProgress()}
                .doAfterTerminate{hideProgress()}
                .subscribe({movies->
                    if(movies.isEmpty()){
                        _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                    }else{
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.LOCAL_SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                })
        )
    }

    fun checkNetworkState(): Boolean {
        return if(networkManager.checkNetworkState()){
            true
        }else{
            requestLocalMovies()
            false
        }
    }

    enum class MessageSet {
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_NOT_CONNECTED,
        ERROR,
        SUCCESS,
        NO_RESULT,
        LOCAL_SUCCESS
    }

}