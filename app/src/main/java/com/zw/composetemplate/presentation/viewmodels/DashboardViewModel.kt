package com.zw.composetemplate.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zw.zwbase.core.BaseViewModel
import com.zw.zwbase.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UserRepository,
) : BaseViewModel() {

    private val _initialSataLoad = MutableLiveData<Boolean>()
    val initialSataLoad : LiveData<Boolean> = _initialSataLoad

    fun setLoaded(isLoaded: Boolean) {
        _initialSataLoad.postValue(isLoaded)
    }
}