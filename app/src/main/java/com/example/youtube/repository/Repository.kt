package com.example.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.model.Playlists
import com.example.youtube.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

open class Repository(private val dataSource: RemoteDataSource) {

    fun getPlaylists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlaylists()
        emit(response)
    }

    fun getPlaylistItems(playlistId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlaylistItems(playlistId)
        emit(response)
    }

    fun getVideos(videoId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getVideos(videoId)
        emit(response)
    }
}