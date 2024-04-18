package com.zw.composetemplate.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zw.composetemplate.databinding.PostListItemLayoutBinding
import com.zw.zwbase.core.setSafeOnClickListener
import com.zw.zwbase.domain.Post

/**
 * Created by Janak on 17/04/24.
 */
class PostListAdapter(val list: ArrayList<Post>, val itemClick: (Int) -> Unit) : RecyclerView.Adapter<PostListAdapter.BranchHolder>() {
	inner class BranchHolder(val binding: PostListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		init {
			binding.clMain.setSafeOnClickListener {
				itemClick.invoke(adapterPosition)
			}
		}
		fun bind(post: Post) {
			binding.tvId.text = "Id: "+post.id.toString()
			binding.tvUserId.text = "UserId: "+post.userId.toString()
			binding.tvTitle.text = "Title: "+post.title
			binding.tvBody.text = "Body: "+post.body
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchHolder {
		return BranchHolder(PostListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: BranchHolder, position: Int) {
		holder.bind(list[position])
	}
}