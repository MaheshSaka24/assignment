package com.assignment.xento.core.core

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.assignment.xento.core.core.utility.getService


abstract class BindingBottomSheetDialogFragment<T : ViewBinding> : BottomSheetDialogFragment() {

    lateinit var bindingBottomSheet: T

    @get:LayoutRes
    abstract var resourceIdRes: Int
    abstract fun initilizeView()
    private var mBehavior: BottomSheetBehavior<*>? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)

        bindingBottomSheet = DataBindingUtil.inflate<ViewDataBinding>(
            requireContext().getService(Context.LAYOUT_INFLATER_SERVICE),
            resourceIdRes, null, false
        ) as T

        val frameLayout = FrameLayout(requireContext())
        val layoutParam = FrameLayout.LayoutParams(-1, -1)
        val view = bindingBottomSheet.root
        frameLayout.addView(bindingBottomSheet.root)
        view.layoutParams = layoutParam
        val root: View = frameLayout
        dialog.setContentView(root)
        /*try {
            val mBehaviorField = dialog.javaClass.getDeclaredField("mBehavior")
            mBehaviorField.isAccessible = true
            val behavior = mBehaviorField[dialog] as BottomSheetBehavior<*>
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == 1) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e2: IllegalAccessException) {
            e2.printStackTrace()
        }*/
        dialog.setOnShowListener {
            var linearLayout = bindingBottomSheet.root
            linearLayout.measure(0, 0);
            this.mBehavior = BottomSheetBehavior.from(root.parent as View)
            val layoutParams = linearLayout.layoutParams
            linearLayout.fitsSystemWindows = true

            val windowHeight: Int = getScreenHeight()
            if (layoutParams != null) {
                layoutParams.height = windowHeight
                mBehavior!!.peekHeight = windowHeight;
            }
            linearLayout.layoutParams = layoutParams
            this.mBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        }
        initilizeView()
        return dialog
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}