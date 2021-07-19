package com.example.cardgame.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.databinding.ViewDataBinding
import com.appknot.core_rx.base.RxBaseDialog
import com.example.cardgame.R
import com.example.cardgame.vm.BaseViewModel

/**
 *
 * @author Ethan on 2021-07-14.
 */
abstract class BaseDialog<VB: ViewDataBinding, VM: BaseViewModel>(
    context: Context
) : RxBaseDialog<VB, VM>(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
    }

}