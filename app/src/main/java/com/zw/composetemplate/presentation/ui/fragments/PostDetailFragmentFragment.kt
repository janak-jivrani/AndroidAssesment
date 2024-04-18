package com.zw.composetemplate.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zw.composetemplate.R
import com.zw.composetemplate.databinding.FragmentPostDetailBinding
import com.zw.composetemplate.presentation.viewmodels.PostDetailViewModel
import com.zw.zwbase.core.BaseDialogFragment
import com.zw.zwbase.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Janak on 17/04/24.
 */
@AndroidEntryPoint
class PostDetailFragmentFragment : BaseFragment<FragmentPostDetailBinding, PostDetailViewModel>(FragmentPostDetailBinding::inflate) {
	override val viewModel: PostDetailViewModel by viewModels()
	val args: PostDetailFragmentFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.tvId.text = "Id: "+args.post.id.toString()
		binding.tvUserId.text = "UserId: "+args.post.userId.toString()
		binding.tvTitle.text = "Title: "+args.post.title
		binding.tvBody.text = "Body: "+args.post.body
	}
}