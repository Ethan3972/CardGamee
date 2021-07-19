package com.example.cardgame.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.cardgame.R
import com.example.cardgame.databinding.ItemCardBinding
import kotlinx.coroutines.delay

/**
 *
 * @author Ethan on 2021-07-14.
 */
class GameBoardAdapter(
    private val countListener: () -> Unit,
    private val onFinish: () -> Unit
) : ListAdapter<Int, GameBoardAdapter.ViewHolder>(
object : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}
) {
    private val collectList = ArrayList<Int>()
    var firstItemPos = -1
    var secondItemPos = -1
    var waiting = false

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewDataBinding = ItemCardBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder.viewDataBinding) {
            ivCardFront.setImageResource(currentList[position])

            // 짝을 맞춘 패가 아니고, 카드 앞면이 보이는 상태라면 안보이는 상태로 되돌린다.
            if (collectList.indexOf(position) == -1 && ivCardFront.visibility == View.VISIBLE) {
                reverseCard(ivCardFront, ivCardBack)
            }
            // 짝을 맞춘 패가 간혹 애니메이션이 씹히므로 강제로 앞면을 만든다.
            if (collectList.indexOf(position) != -1) {
                ivCardFront.rotationY = 0f
                ivCardFront.visibility = View.VISIBLE
                ivCardBack.visibility = View.INVISIBLE
            }

            clCard.setOnClickListener {
                // 애니메이션이 작동 중이지 않을 때, 아직 짝을 맞추지 못한 패인 경우 누를 수 있다.
                Log.d("카드 선택: ", "$position")
                if (collectList.indexOf(position) == -1 && !waiting) {
                    Log.d("카드 눌림: ", "$position")
                    if (firstItemPos == -1) {
                        Log.d("첫 카드: ", "$position")
                        // 첫 카드 오픈
                        waiting = true
                        firstItemPos = position
                        countListener.invoke()
                        reverseCard(ivCardBack, ivCardFront)
                    } else if (secondItemPos == -1 && firstItemPos != position) {
                        Log.d("두번째 카드: ", "$position")
                        // 첫 번째 카드를 오픈하고, 이후 두번 째 카드 오픈 시 짝 확인
                        waiting = true
                        secondItemPos = position
                        countListener.invoke()
                        reverseCard(ivCardBack, ivCardFront)
                    }
                }
            }
        }
    }

    /**
     * 카드 뒤집기 애니메이션
     */
    private fun reverseCard(frontView: View, backView: View) {
        backView.rotationY = -90f
        frontView.animate()
            .rotationY(90f)
            .setDuration(150L)
            .withEndAction {
                frontView.visibility = View.INVISIBLE
                backView.visibility = View.VISIBLE
                backView.animate()
                    .rotationY(0f)
                    .setDuration(150L)
                    .setInterpolator(LinearInterpolator())
                    .withEndAction {
                        // 두번째 카드를 오픈 한 경우 확인
                        if (secondItemPos > -1) {
                            // 짝이 맞는지 확인
                            if (checkPair(firstItemPos, secondItemPos)) {
                                // 일치한 경우 hold를 위해 collectList에 넣음
                                savePair(firstItemPos, secondItemPos)
                                checkFinish()
                            } else {
                                // 불일치한 경우 다시 뒤집기
                                reversePair(firstItemPos, secondItemPos)
                            }
                        }
                        waiting = false
                    }
                    .start()
            }
            .setInterpolator(LinearInterpolator())
            .start()
    }

    /**
     * 짝 확인
     */
    private fun checkPair(firstPos: Int, secondPos: Int) : Boolean {
        return currentList[firstPos] == currentList[secondPos]
    }

    /**
     * 맞춘 카드 저장
     */
    private fun savePair(firstPos: Int, secondPos: Int) {
        collectList.add(firstPos)
        collectList.add(secondPos)
        // 맞췄는데 뒤집혀 있는 버그 -> 강제로 오픈 시키기
        collectList.forEach {
            notifyItemChanged(it)
        }
        firstItemPos = -1
        secondItemPos = -1
    }

    /**
     * 맞추기 실패한 경우 다시 뒤집기
     */
    private fun reversePair(firstPos: Int, secondPos: Int) {
        notifyItemChanged(firstPos)
        notifyItemChanged(secondPos)
        firstItemPos = -1
        secondItemPos = -1
    }

    /**
     * 짝을 다 맞췄는지 확인
     */
    private fun checkFinish() {
        if (currentList.size == collectList.size) {
            onFinish.invoke()
        }
    }
}