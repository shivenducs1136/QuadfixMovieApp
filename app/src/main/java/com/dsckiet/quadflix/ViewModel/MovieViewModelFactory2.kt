package com.dsckiet.quadflix.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dsckiet.quadflix.Repo.Repository

class MainViewModelFactory2(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel2(repository) as T
    }

}