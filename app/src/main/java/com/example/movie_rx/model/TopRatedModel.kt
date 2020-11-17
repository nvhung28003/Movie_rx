package com.example.movie_rx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopRatedModel(
    @Expose
    @SerializedName("results")
    val result: List<TopratedResults>
)
data class TopratedResults(
    @Expose
    @SerializedName("popularity")
    val popularity: Double,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("video")
    val video: Boolean,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("release_date")
    val releaseDate: String,
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,
    @Expose
    @SerializedName("original_title")
    val originalTitle: String,
    @Expose
    @SerializedName("genre_ids")
    val genreIds: List<Integer>,
    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("overview")
    val overview: String,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String
)



