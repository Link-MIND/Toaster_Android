package org.sopt.clip.clipedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.ClipsDTO
import org.sopt.clip.ItemTouchHelperListener
import org.sopt.clip.databinding.ItemClipEditClipBinding
import org.sopt.ui.view.ItemDiffCallback

class ClipEditAdapter(
  private val itemClick: (Long, String, Long) -> Unit,
) : ListAdapter<ClipsDTO, ClipEditViewHolder>(DiffUtil), ItemTouchHelperListener {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipEditViewHolder {
    return ClipEditViewHolder(
        ItemClipEditClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        itemClick,
    )
  }

  override fun onBindViewHolder(holder: ClipEditViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<ClipsDTO>(
      onItemsTheSame = { old, new -> old.clipName == new.clipName },
      onContentsTheSame = { old, new -> old == new },
    )
  }


  override fun onItemMove(from: Int, to: Int) {
    val item: ClipsDTO = currentList[from]
    val newList = ArrayList<ClipsDTO>()
    newList.addAll(currentList)
    newList.removeAt(from)
    newList.add(to, item)
    notifyItemMoved(from, to)
  }

  override fun onItemSwipe(position: Int) {
    val newList = ArrayList(currentList)
    newList.removeAt(position)
    notifyItemRemoved(position)
  }
}
