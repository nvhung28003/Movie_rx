package com.example.movie_rx.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_rx.model.AllModel
import com.example.movie_rx.model.DetailModel
import com.example.movie_rx.network.NetWorkController
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {
    val requestAll: MutableLiveData<AllModel> = MutableLiveData();
    fun callDetail(movie_id: Int, key_api: String) {
        val callDetailMovie : Observable<AllModel> = NetWorkController.callAllDetail(movie_id,key_api)!!

        callDetailMovie.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.run {
                    requestAll.postValue(it);
                }
            })
    }
}