package com.example.movie_rx.network


import com.example.movie_rx.model.DetailModel

import com.example.movie_rx.model.TopRatedModel
import com.example.movie_rx.model.VideoModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface API {
    @POST("movie/top_rated")
    fun getCallApiTopRated(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): Single<TopRatedModel>;

    @POST("movie/upcoming")
    fun getCallApiUpComming(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): Single<TopRatedModel>;

    @POST("movie/popular")
    fun getCallApiPopular(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): Single<TopRatedModel>;

    @POST("movie/now_playing")
    fun getCallApiNowPlaying(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): Single<TopRatedModel>;

    @GET("movie/{movie_id}")
    fun getCallApiDetaiMovie(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") apiKey: String?
    ): Observable<DetailModel>;

    @GET("movie/{movie_id}/similar")
    fun getCallApiSimilar(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") apiKey: String?
    ): Observable<TopRatedModel>;

    @GET("movie/{movie_id}/videos")
    fun getCallApiVideo(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") apiKey: String?
    ): Observable<VideoModel>;
}