package com.example.youtube.di

import com.example.youtube.core.network.networkModules
import com.example.youtube.remote.remoteDataSourceModules

val koinModules = listOf(
    repoModules,viewModules,networkModules,remoteDataSourceModules
)
