package com.example.cardgame.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 *
 * @author Ethan on 2021-07-14.
 */
class PlayGameViewModel : BaseViewModel() {
    // 게임 레벨
    val level = MutableLiveData<String>()
    // 뒤집은 횟수
    val reverseCount = ObservableField<Int>(0)
    // 게임 진행시간
    var timer = 30
    // x * y 레벨에 따른 게임 크기
    var x = 4
    var y = 4
    var deck = MutableLiveData<ArrayList<Int>>()

    /**
     * 레벨에 맞는 게임 세팅
     */
    fun initGame(level: String) {
        reverseCount.set(0)

        when (level) {
            LEVEL_EASY -> {
                x = 5
                y = 4
            }
            LEVEL_NORMAL -> {
                x = 5
                y = 6
            }
            LEVEL_HARD -> {
                x = 5
                y = 8
            }
        }
        shuffleDeck(x*y)
    }

    /**
     * 카드 섞기
     */
    private fun shuffleDeck(deckCount: Int){
        val newDeck = arrayListOf<Int>()
        val needCard = deckCount / 2
        CARD_LIST.shuffle()
        for (i in 0 until needCard) {
            newDeck.add(CARD_LIST[i])
            newDeck.add(CARD_LIST[i])
        }
        newDeck.shuffle()
        deck.value = newDeck
    }
}