package com.example.cardgame.vm

import com.appknot.core_rx.base.RxBaseViewModel
import com.example.cardgame.R

/**
 *
 * @author Ethan on 2021-07-13.
 */
open class BaseViewModel : RxBaseViewModel() {
    companion object {
        const val LEVEL_EASY = "쉬움"
        const val LEVEL_NORMAL = "보통"
        const val LEVEL_HARD = "어려움"

        const val MENU_RESUME = "이어서"
        const val MENU_RESTART = "다시 시작"
        const val MENU_FINISH = "종료"

        val CARD_LIST = arrayListOf(
            R.drawable.ahri,
            R.drawable.anivia,
            R.drawable.aphelios,
            R.drawable.ashe,
            R.drawable.blitzcrank,
            R.drawable.caitlyn,
            R.drawable.darius,
            R.drawable.ezreal,
            R.drawable.heimerdinger,
            R.drawable.jinx,
            R.drawable.leona,
            R.drawable.lux,
            R.drawable.masteryi,
            R.drawable.missfortune,
            R.drawable.pyke,
            R.drawable.ryze,
            R.drawable.teemo,
            R.drawable.viego,
            R.drawable.yasuo,
            R.drawable.yuumi,
            R.drawable.ziggs,
            R.drawable.zoe
        )
    }
}