package com.dsckiet.quadflix.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsckiet.quadflix.Repo.Repository
import com.dsckiet.quadflix.model.Movies
import kotlinx.coroutines.launch

class MainViewModel2(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Movies> = MutableLiveData()
    var showProgress: LiveData<Boolean>
    init {
        showProgress = repository.showProgress
    }
    fun getMovie(q:String){
        viewModelScope.launch {
            val response: Movies = repository.getMovie(q)
            myResponse.value = response
        }
    }



}