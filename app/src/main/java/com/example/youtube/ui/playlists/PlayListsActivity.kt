package com.example.youtube.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.youtube.App.Companion.KEY
import com.example.youtube.App.Companion.KEY_DESC
import com.example.youtube.App.Companion.KEY_ITEM_COUNT
import com.example.youtube.App.Companion.KEY_TITLE
import com.example.youtube.R
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.PlaylistsMainBinding
import com.example.youtube.ui.detail.DetailActivity
import com.example.youtube.core.utils.ConnectionLiveData
import com.example.youtube.core.extention.isNetworkConnected
import com.example.youtube.core.extention.showToast
import com.example.youtube.core.network.result.Status
import com.example.youtube.repository.Repository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListsActivity : BaseActivity<PlayListsViewModel, PlaylistsMainBinding>() {

    private val repository: Repository by inject()

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlayListsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPlaylists()
    }

    override fun initListener() {
        adapter = PlaylistsAdapter {
            val intent = Intent(this@PlayListsActivity, DetailActivity::class.java)
            intent.putExtra(KEY, it.id)
            intent.putExtra(KEY_TITLE, it.snippet.title)
            intent.putExtra(KEY_DESC, it.snippet.description)
            intent.putExtra(KEY_ITEM_COUNT, it.contentDetails.itemCount)
            startActivity(intent)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        val cld = ConnectionLiveData(application)
        cld.observe(this) {
            if (!it) {
                binding.noInternet.root.isVisible = true
                binding.noInternet.btnTryAgain.setOnClickListener {
                    if (!isNetworkConnected()) {
                        showToast(getString(R.string.no_internet))
                    }
                }
            } else {
                setPlaylists()
                binding.noInternet.root.isVisible = false
            }
        }
    }

    private fun setPlaylists() {
        binding.toolbar.tvBack.isVisible = false
        viewModel.loading.observe(this) {
            binding.progressCircular.isVisible = it
        }

        viewModel.getPlaylists().observe(this) {
            binding.rvPlaylists.adapter = adapter
            when (it.status) {
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    it.data?.items?.let { it1 -> adapter.setData(it1) }
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    showToast(it.message.toString())
                }
            }
        }
    }
}
