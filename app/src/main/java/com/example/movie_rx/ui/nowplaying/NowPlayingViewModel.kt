package com.example.movie_rx.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_rx.model.TopRatedModel
import com.example.movie_rx.model.TopratedResults
import com.example.movie_rx.network.NetWorkController
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NowPlayingViewModel : ViewModel() {

    val requestNowPlaying: MutableLiveData<List<TopratedResults>> = MutableLiveData();


    fun getApiNowPlaying(page: Int) {
        var callNowPlaying: Single<TopRatedModel> = NetWorkController.callApiNowPlaying(TopRatedViewModel.KEY, page);
        callNowPlaying.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    requestNowPlaying.postValue(it.result)
                }
            }, {
                it.run {
                }
            })
    }
}