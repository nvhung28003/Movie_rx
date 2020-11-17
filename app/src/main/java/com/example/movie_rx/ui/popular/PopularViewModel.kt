package com.example.movie_rx.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_rx.model.TopRatedModel
import com.example.movie_rx.model.TopratedResults
import com.example.movie_rx.network.NetWorkController
import com.example.movie_rx.ui.toprated.TopRatedViewModel.Companion.KEY
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularViewModel : ViewModel() {

    val requestPopular: MutableLiveData<List<TopratedResults>> = MutableLiveData();


    fun getApiPopular(page: Int) {
        var callPopular: Single<TopRatedModel> = NetWorkController.callApiPopular(KEY, page);
        callPopular.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    requestPopular.postValue(it.result)
                }
            }, {
                it.run {
                }
            })
    }
}