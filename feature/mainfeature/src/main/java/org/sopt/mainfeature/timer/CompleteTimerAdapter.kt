package org.sopt.mainfeature.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.mainfeature.databinding.ItemTimerCompleteBinding
import org.sopt.mainfeature.timer.dummymodel.Timer
import org.sopt.ui.view.ItemDiffCallback

class CompleteTimerAdapter(
    private val onClicked: (Timer) -> Unit,
) : ListAdapter<Timer, CompleteTimerViewHolder>(DiffUtil) {
    override fun onBindViewHolder(holder: CompleteTimerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteTimerViewHolder {
        return CompleteTimerViewHolder(
            ItemTimerCompleteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClicked
        )
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Timer>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
