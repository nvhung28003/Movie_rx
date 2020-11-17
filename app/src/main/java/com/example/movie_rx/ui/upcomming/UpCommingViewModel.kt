package com.example.movie_rx.ui.upcomming

import android.util.Log
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

class UpCommingViewModel : ViewModel() {

    val requestUpComing: MutableLiveData<List<TopratedResults>> = MutableLiveData();


    fun getApiUpComing(page: Int) {
        var callUpComing: Single<TopRatedModel> = NetWorkController.callApiUpComming(KEY, page);
        callUpComing.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    Log.d("!21", it.toString());
                    requestUpComing.postValue(it.result)
                }
            }, {
                it.run {
                    Log.d("!21", it.toString());
                }
            })
    }
}