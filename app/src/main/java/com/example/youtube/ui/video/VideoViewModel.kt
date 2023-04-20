package com.example.youtube.ui.video

import androidx.lifecycle.LiveData
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.VideoItem
import com.example.youtube.repository.Repository

class VideoViewModel(private val repository: Repository) : BaseViewModel() {

    fun getVideos(videoId: String): LiveData<Resource<VideoItem>> {
        return repository.getVideos(videoId)
    }
}