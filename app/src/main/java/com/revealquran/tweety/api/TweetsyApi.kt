package com.revealquran.tweety.api

import com.revealquran.tweety.models.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {

    // To get all the tweets from the api of specific category
    // Give the end point of our api in get
    // Also we define header to filter out our api to specific category
    @GET("/v3/b/664db2bbacd3cb34a84bccbf?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<Tweet>>

    // To get all the categories of tweets from the api
    // Here we use headers to get only the categories of tweets from the api
    @GET("/v3/b/664db2bbacd3cb34a84bccbf?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : Response<List<String>>

}