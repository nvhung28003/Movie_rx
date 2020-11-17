package com.example.movie_rx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SimilarModelModel(
    @Expose
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("results")
    val results: List<Results>,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int
)

data class Results(
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @Expose
    @SerializedName("genre_ids")
    val genreIds: List<Integer>,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,
    @Expose
    @SerializedName("original_title")
    val originalTitle: String,
    @Expose
    @SerializedName("overview")
    val overview: String,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String,
    @Expose
    @SerializedName("release_date")
    val releaseDate: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("video")
    val video: Boolean,
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,
    @Expose
    @SerializedName("popularity")
    val popularity: Double
)