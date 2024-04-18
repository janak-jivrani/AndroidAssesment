package com.zw.composetemplate.presentation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zw.composetemplate.core.PaginationScrollListener
import com.zw.composetemplate.databinding.FragmentPostListBinding
import com.zw.composetemplate.presentation.ui.adapter.PostListAdapter
import com.zw.composetemplate.presentation.viewmodels.PostListViewModel
import com.zw.zwbase.core.BaseFragment
import com.zw.zwbase.core.Resource
import com.zw.zwbase.domain.Post
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Janak on 17/04/24.
 */
@AndroidEntryPoint
class PostLstFragmentFragment : BaseFragment<FragmentPostListBinding, PostListViewModel>(FragmentPostListBinding::inflate) {
	override val viewModel: PostListViewModel by viewModels()
	lateinit var postListAdapter: PostListAdapter
	val list = arrayListOf<Post>()
	lateinit var linearLayoutManager : LinearLayoutManager
	private val PAGE_START = 1
	private var isLoading = false
	private var isLastPage = false

	private val TOTAL_PAGES = 100
	private var currentPage = PAGE_START
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.loadPosts()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		postListAdapter = PostListAdapter(list) {
			findNavController().navigate(PostLstFragmentFragmentDirections.actionPostListToPostDetail(list[it]))
		}
		linearLayoutManager = LinearLayoutManager(requireContext())
		binding.rvBusinessList.apply {
			adapter = postListAdapter
			layoutManager = linearLayoutManager
		}

		binding.rvBusinessList.addOnScrollListener(object: PaginationScrollListener(linearLayoutManager) {
			override fun loadMoreItems() {
				this@PostLstFragmentFragment.isLoading = true
				currentPage += 1;
				if (currentPage <= TOTAL_PAGES)
					// simulating network latency for API call
					Handler().postDelayed({
						viewModel.loadPosts()
					},100)
			}

			override val totalPageCount: Int
				get() = TOTAL_PAGES
			override val isLastPage: Boolean
				get() = this@PostLstFragmentFragment.isLastPage
			override val isLoading: Boolean
				get() = this@PostLstFragmentFragment.isLoading
		});

		viewModel.postListLiveData.observe(viewLifecycleOwner) {
			when(it) {
				is Resource.Loading -> {
					//adapter.addLoadingFooter();
					showProgress()
					isLoading = true
				}
				is Resource.Failure -> {
					//adapter.removeLoadingFooter()
					isLoading = false
					hideProgress()
				}
				is Resource.None -> {
					hideProgress()
				}
				is Resource.Success -> {
					//adapter.removeLoadingFooter();
					isLoading = false;
					hideProgress()
					it.data?.let { postArrayList ->
						val lastIndex = list.size
						list.addAll(postArrayList)
						postListAdapter.notifyItemRangeInserted(lastIndex,postArrayList.size)

						if (currentPage > TOTAL_PAGES) {
							isLastPage = true
						}
					}
				}
			}
		}
	}
}