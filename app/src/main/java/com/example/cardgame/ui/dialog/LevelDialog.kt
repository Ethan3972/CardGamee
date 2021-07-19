package com.example.cardgame.ui.dialog

import android.content.Context
import com.example.cardgame.R
import com.example.cardgame.databinding.DialogLevelBinding
import com.example.cardgame.vm.BaseViewModel

/**
 *
 * @author Ethan on 2021-07-14.
 */
class LevelDialog(
    context: Context,
    private val onClickLevel: (String) -> Unit
) : BaseDialog<DialogLevelBinding, BaseViewModel>(context) {
    override val layoutResourceId: Int = R.layout.dialog_level
    override val viewModel = BaseViewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewDataBinding.level = this
    }

    override fun initAfterBinding() {
    }

    fun selectLevel(level: String) {
        onClickLevel.invoke(level)
        dismiss()
    }

}