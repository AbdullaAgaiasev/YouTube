package com.example.youtube.remote

import com.example.youtube.App
import com.example.youtube.BuildConfig
import com.example.youtube.core.network.BaseDataSource
import org.koin.dsl.module

val remoteDataSourceModules = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {


    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(BuildConfig.API_KEY, App.PART, App.CHANNEL_ID)
    }


    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(BuildConfig.API_KEY, App.PART, playlistId)
    }


    suspend fun getVideos(videoId: String) = getResult {
        apiService.getVideos(BuildConfig.API_KEY, App.PART, videoId)
    }
}