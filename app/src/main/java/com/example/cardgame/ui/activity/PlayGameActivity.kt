package com.example.cardgame.ui.activity

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cardgame.R
import com.example.cardgame.databinding.ActivityPlayGameBinding
import com.example.cardgame.ui.adapter.GameBoardAdapter
import com.example.cardgame.ui.dialog.GameOverDialog
import com.example.cardgame.ui.dialog.MenuDialog
import com.example.cardgame.vm.BaseViewModel
import com.example.cardgame.vm.PlayGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayGameActivity : BaseActivity<ActivityPlayGameBinding, PlayGameViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_play_game
    override val viewModel: PlayGameViewModel by viewModel()
    private val menuDialog: MenuDialog? = null

    override fun initStartView() {
        viewModel.level.value = intent.getStringExtra(INTENT_KEY_LEVEL) ?: BaseViewModel.LEVEL_EASY
    }

    override fun initDataBinding() {
        viewDataBinding.run {
            playGame = this@PlayGameActivity
            vm = viewModel
        }
    }

    override fun initAfterBinding() {
        viewModel.run {
            // 레벨에 맞는 게임 세팅 완료
            level.observe(this@PlayGameActivity, Observer {
                if (!it.isNullOrEmpty()) {
                    initGame(it)
                }
            })
            // 덱 준비 완료
            deck.observe(this@PlayGameActivity, Observer {
                if (!it.isNullOrEmpty()) {
                    setGame(it)
                    startGame()
                }
            })
        }

    }

    override fun onPause() {
        super.onPause()
        pauseGame()
    }

    override fun onBackPressed() {
        pauseGame()
    }

    /**
     * 게임 만들기
     */
    private fun setGame(cardList: ArrayList<Int>) {
        viewDataBinding.rvGameBoard.apply {
            animation = null
            layoutManager = GridLayoutManager(context, viewModel.x)
            adapter = GameBoardAdapter(
                {
                    viewModel.reverseCount.set(
                        viewModel.reverseCount.get()?.plus(1)
                    )
                }, {
                    finishGame()
                }
            ) .apply {
                submitList(cardList)
            }
        }
    }

    /**
     * 게임 시작
     */
    private fun startGame() {

    }

    /**
     * 이어하기
     */
    private fun resumeGame() {
        menuDialog?.dismiss()
    }

    /**
     * 일시 정지 = 메뉴
     */
    private fun pauseGame() {
        showMenu()
    }

    /**
     * 같은 난이도 처음부터 재시작
     */
    private fun restartGame() {
        viewModel.level.value?.let {
            viewModel.initGame(it)
        }
    }

    /**
     * 중도 포기
     */
    private fun dropGame() {

    }

    /**
     * 게임 종료
     */
    private fun finishGame() {
        GameOverDialog(this) {
            when (it) {
                BaseViewModel.MENU_RESTART -> {
                    restartGame()
                }
                BaseViewModel.MENU_FINISH -> {
                    finish()
                }
            }
        }.show()
    }

    /**
     * 메뉴
     */
    fun showMenu() {
        if (menuDialog == null) {
            MenuDialog(this) {
                when (it) {
                    BaseViewModel.MENU_RESUME -> {
                        resumeGame()
                    }
                    BaseViewModel.MENU_FINISH -> {
                        finish()
                    }
                }
            }.show()
        } else {
            if (!menuDialog.isShowing) {
                menuDialog.show()
            }
        }
    }
}