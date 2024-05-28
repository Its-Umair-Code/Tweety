package com.revealquran.tweety.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revealquran.tweety.models.Tweet
import com.revealquran.tweety.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Ham yahan py SavedStateHandle ko isliya use kr rahy hain ky q ky hamary nav arguments yahan py bhi store hoty hain navigation framework ky through
// So, isliya agar hamny nav arguments ko yahan fetch karky use karna ha to ham SavedStateHandle ko use kr rahy hain
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    val tweets: StateFlow<List<Tweet>>
        get() = tweetRepository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "android" // agar category null hui to android wali string pass ho jay gi
            tweetRepository.getTweets(category)
        }
    }

}