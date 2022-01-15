package com.dsckiet.quadflix.Repo

import androidx.lifecycle.MutableLiveData
import com.dsckiet.quadflix.api.RetrofitInstance
import com.dsckiet.quadflix.model.Movies

class Repository{
    val showProgress = MutableLiveData<Boolean>()

     suspend fun getMovie(q:String): Movies{
        showProgress.value = true
        return RetrofitInstance.api.getMovie1(q)
    }

}