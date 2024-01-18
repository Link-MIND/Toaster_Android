package org.sopt.clip.clipedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.databinding.ItemClipEditClipBinding
import org.sopt.model.category.Category
import org.sopt.ui.view.ItemDiffCallback

class ClipEditAdapter(
  private val itemClick: (Long, String, Long, String) -> Unit,
  private val deleteClip: (Long) -> Unit,
  private val onLongClick: (Long) -> Unit,
  private val onLongClick2: (Long) -> Unit,
) : ListAdapter<Category, ClipEditViewHolder>(DiffUtil), ItemTouchHelperListener {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipEditViewHolder {
    return ClipEditViewHolder(
      ItemClipEditClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      itemClick,
      onLongClick,
    )
  }

  override fun onBindViewHolder(holder: ClipEditViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<Category>(
      onItemsTheSame = { old, new -> old.categoryId == new.categoryId },
      onContentsTheSame = { old, new -> old == new },
    )
  }

  override fun onItemMove(from: Int, to: Int) {
    val item: Category? = currentList[from]
    val newList = ArrayList<Category>()
    newList.addAll(currentList)
    newList.removeAt(from)
    if (item != null) {
      newList.add(to, item)
    }
    notifyItemMoved(from, to)
    onLongClick2(to.toLong())
  }

  override fun onItemSwipe(position: Int) {
    val newList = ArrayList(currentList)
    deleteClip(newList[position].categoryId ?: 0)
    newList.removeAt(position)
    notifyItemRemoved(position)
  }
}
