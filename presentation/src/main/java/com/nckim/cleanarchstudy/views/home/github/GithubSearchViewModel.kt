package com.nckim.cleanarchstudy.views.home.github

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nckim.cleanarchstudy.base.BaseViewModel
import com.nckim.cleanarchstudy.utils.NetworkManager
import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.usecase.GetGithubRepositoryUseCase
import com.nckim.domain.usecase.GetPagingGithubRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GithubSearchViewModel @Inject constructor(
    private val getGithubRepositoryUseCase: GetGithubRepositoryUseCase,
    private val getPagingGithubRepositoryUseCase: GetPagingGithubRepositoryUseCase,
    private val networkManager: NetworkManager
): BaseViewModel(){

    private var currentQuery : String = ""
    val query = MutableLiveData<String>()
    private val _githubList = MutableLiveData<MutableList<GithubRepositoryModel>>()

    val githubList : LiveData<MutableList<GithubRepositoryModel>>
        get() {
            return _githubList
        }

    fun requestGithubList(){
        currentQuery = query.value.toString().trim()
        if(currentQuery.isEmpty()){
            return
        }
        if(!checkNetworkState()){
            return
        }
        compositeDisposable.add(
            getGithubRepositoryUseCase.execute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{showProgress()}
                .doAfterTerminate{hideProgress()}
                .subscribe({ githubRepositories ->
                    if(githubRepositories.isEmpty()){
                        Log.e("NCTEST", "######## fail")
                        //fail
                    }else{
                        _githubList.value = githubRepositories as ArrayList<GithubRepositoryModel>

                        Log.e("NCTEST", "######## succes")
                    }
                }, {
                    //fail

                    Log.e("NCTEST", "######## fail " + it.message)
                })
        )

    }

    private fun requestPagingGithubList(offset: Int){
        compositeDisposable.add(
            getPagingGithubRepositoryUseCase.execute(currentQuery, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{showProgress()}
                .doAfterTerminate{hideProgress()}
                .subscribe({ githubRepositories ->
                    if(githubRepositories.isEmpty()){
                        Log.e("NCTEST", "######## paging fail1 ")
                        //fail
                    }else{
//                        _githubList.value = githubRepositories as ArrayList<GithubRepositoryModel>
                        val curList = _githubList.value
                        curList?.addAll(githubRepositories)
                        _githubList.value = curList!!
                        Log.e("NCTEST", "######## paging success")
                    }
                }, {
                    //fail

                    Log.e("NCTEST", "######## paging fail2 " + it.message)
                })
        )
    }

    override fun onEndlessScroll(offset: Int) {
        requestPagingGithubList(offset)
    }

    fun checkNetworkState(): Boolean {
        return networkManager.checkNetworkState()
    }

}