package com.revealquran.tweety.repository

import com.revealquran.tweety.api.TweetsyApi
import com.revealquran.tweety.models.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {


    // Isko hamny isliya MutableStateFlow ma rakha ha taky ya sirf yahin sy update ho saky or koi isko update na kr saky
    private val _categories = MutableStateFlow<List<String>>(emptyList())

    // Or ys stateflow ka object ha joky read only ha
    val categories: StateFlow<List<String>>
        get() = _categories

    // Isko hamny isliya MutableStateFlow ma rakha ha taky ya sirf yahin sy update ho saky or koi isko update na kr saky
    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())

    // Or ys stateflow ka object ha joky read only ha
    val tweets: StateFlow<List<Tweet>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyApi.getCategories()
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsyApi.getTweets("tweets[?(@.category==\"${category}\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }
    }

}