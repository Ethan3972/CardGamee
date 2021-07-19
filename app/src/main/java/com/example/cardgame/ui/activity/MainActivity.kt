package com.example.cardgame.ui.activity

import com.appknot.core_rx.extensions.intentFor
import com.example.cardgame.vm.MainViewModel
import com.example.cardgame.R
import com.example.cardgame.databinding.ActivityMainBinding
import com.example.cardgame.ui.dialog.LevelDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewDataBinding.run {
            main = this@MainActivity
        }
    }

    override fun initAfterBinding() {
    }

    fun showLevelDialog() {
        LevelDialog(this){ level ->
            startActivity(intentFor<PlayGameActivity>(
                INTENT_KEY_LEVEL to level
            ))
        }.show()
    }
}