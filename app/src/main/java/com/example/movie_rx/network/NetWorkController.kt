package com.example.movie_rx.network

import com.example.movie_rx.model.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetWorkController {
    companion object {
        private var apiBuilder: API? = null
        fun getApiBuilder(): API {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            apiBuilder = retrofit.create(API::class.java)
            return apiBuilder as API
        };

        fun callApiToprated(apiKey: String, page: Int): Single<TopRatedModel> {
            return getApiBuilder().getCallApiTopRated(apiKey, page)
        }

        fun callApiUpComming(apiKey: String, page: Int): Single<TopRatedModel> {
            return getApiBuilder().getCallApiUpComming(apiKey, page)
        }

        fun callApiPopular(apiKey: String, page: Int): Single<TopRatedModel> {
            return getApiBuilder().getCallApiPopular(apiKey, page)
        }

        fun callApiNowPlaying(apiKey: String, page: Int): Single<TopRatedModel> {
            return getApiBuilder().getCallApiNowPlaying(apiKey, page)
        }

        fun callAllDetail(
            movie_id: Int,
            apiKey: String
        ): Observable<AllModel>? {
            return Observable.zip(apiBuilder?.getCallApiSimilar(movie_id, apiKey),
                apiBuilder?.getCallApiDetaiMovie(movie_id, apiKey),
                apiBuilder?.getCallApiVideo(movie_id, apiKey),
                object : Function3<SimilarModelModel, DetailModel,VideoModel, AllModel> {
                    override fun apply(t1: SimilarModelModel, t2: DetailModel,t3 : VideoModel): AllModel =
                        AllModel(t2, t1,t3)
                })

        }
    }
}