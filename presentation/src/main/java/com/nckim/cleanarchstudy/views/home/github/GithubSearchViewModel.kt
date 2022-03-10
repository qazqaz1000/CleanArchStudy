package com.nckim.cleanarchstudy.views.home.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nckim.cleanarchstudy.base.BaseViewModel
import com.nckim.cleanarchstudy.utils.NetworkManager
import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.usecase.GetGithubRepositoryUseCase
import com.nckim.domain.usecase.GetPagingGithubRepositoryUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

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
                        //fail
                    }else{
                        _githubList.value = githubRepositories as ArrayList<GithubRepositoryModel>

                    }
                }, {
                    //fail
                })
        )

    }

    override fun onEndlessScroll() {
    }

    fun checkNetworkState(): Boolean {
        return networkManager.checkNetworkState()
    }

}