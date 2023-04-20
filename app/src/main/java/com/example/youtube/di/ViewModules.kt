package com.example.youtube.di

import com.example.youtube.ui.detail.DetailViewModel
import com.example.youtube.ui.playlists.PlayListsViewModel
import com.example.youtube.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlayListsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}