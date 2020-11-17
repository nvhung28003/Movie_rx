package com.example.movie_rx.ui.toprated

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_rx.model.TopRatedModel
import com.example.movie_rx.model.TopratedResults
import com.example.movie_rx.network.NetWorkController
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TopRatedViewModel : ViewModel() {
    companion object {
        const val KEY: String = "a403e5197c05324c9ee7dcf3f7043692";
    }

    val requestToprated: MutableLiveData<List<TopratedResults>> = MutableLiveData();


    fun getApiToprated(page: Int) {
        var callToprate: Single<TopRatedModel> = NetWorkController.callApiToprated(KEY, page);
        callToprate.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    Log.d("!21", it.toString());
                    requestToprated.postValue(it.result)
                }
            }, {
                it.run {
                    Log.d("!21", it.toString());
                }
            })
    }
}