package com.example.cardgame.ui.dialog

import android.content.Context
import com.example.cardgame.R
import com.example.cardgame.databinding.DialogGameOverBinding
import com.example.cardgame.databinding.DialogMenuBinding
import com.example.cardgame.vm.BaseViewModel

/**
 *
 * @author Ethan on 2021-07-15.
 */
class GameOverDialog(
    context: Context,
    private val onClickMenu: (String) -> Unit
) : BaseDialog<DialogGameOverBinding, BaseViewModel>(context) {
    override val layoutResourceId: Int = R.layout.dialog_game_over
    override val viewModel = BaseViewModel()

    override fun initStartView() {
        this.setCanceledOnTouchOutside(false)
    }

    override fun initDataBinding() {
        viewDataBinding.gameOver = this
    }

    override fun initAfterBinding() {
    }

    fun selectMenu(menu: String) {
        onClickMenu.invoke(menu)
        dismiss()
    }

    override fun onBackPressed() {
    }

}