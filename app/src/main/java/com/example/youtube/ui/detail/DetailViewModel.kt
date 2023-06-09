package com.example.youtube.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.PlaylistItem
import com.example.youtube.repository.Repository

class DetailViewModel(private val repository: Repository) : BaseViewModel() {
    fun getItemsList(id: String): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItems(id)
    }
}