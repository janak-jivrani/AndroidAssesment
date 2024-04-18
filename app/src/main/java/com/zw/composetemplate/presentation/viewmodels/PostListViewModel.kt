package com.zw.composetemplate.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zw.zwbase.core.BaseViewModel
import com.zw.zwbase.core.Resource
import com.zw.zwbase.core.SingleLiveEvent
import com.zw.zwbase.data.repositories.UserRepository
import com.zw.zwbase.domain.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val userRepository: UserRepository): BaseViewModel() {
	private val _postListLiveData = SingleLiveEvent<Resource<ArrayList<Post>>>()
	val postListLiveData : LiveData<Resource<ArrayList<Post>>> = _postListLiveData

	fun loadPosts() {
		_postListLiveData.postValue(Resource.Loading())
		viewModelScope.launch(Dispatchers.IO) {
			userRepository.getPosts().collect {
				_postListLiveData.postValue(it)
			}
		}
	}
}