package com.endeavour.ecobee.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.endeavour.ecobee.manager.CacheManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    cacheManager: CacheManager
) : ViewModel() {

    val tasks = cacheManager.getTasks().asLiveData()

}