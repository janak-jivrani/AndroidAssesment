package com.zw.zwbase.core

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.zw.zwbase.R


/**
 * Created by Janak on 06/02/24.
 */
abstract class BaseDialogFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> VB,
) : DialogFragment(), View.OnClickListener {
    abstract val viewModel: VM

    private var _binding: VB? = null
    val binding: VB
        get() = checkNotNull(_binding)

    val isBindingNull
        get() = _binding == null

    private var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater)
        return binding.root
    }


    override fun onClick(p0: View?) {
        // Not Implemented yet
    }

    fun setUpClicks(vararg view: View) {
        view.forEach {
            it.setOnClickListener(this)
        }
    }

    private fun initProgressbar() {
        progressDialog = Dialog(requireContext())
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setContentView(R.layout.dialog_loading)
        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog?.setCancelable(false)
    }


    fun showProgress() {
        if (progressDialog != null && !progressDialog?.isShowing!!) {
            progressDialog?.show()
        } else {
            initProgressbar()
            progressDialog?.show()
        }
    }

    fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.dismiss()
        }
    }
}