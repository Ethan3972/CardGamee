package com.example.cardgame.ui.activity

import androidx.databinding.ViewDataBinding
import com.appknot.core_rx.base.RxBaseActivity
import com.example.cardgame.vm.BaseViewModel

/**
 *
 * @author Ethan on 2021-07-13.
 */
abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel> : RxBaseActivity<VB, VM>() {
    companion object {
        const val INTENT_KEY_LEVEL = "LEVEL"
    }
}