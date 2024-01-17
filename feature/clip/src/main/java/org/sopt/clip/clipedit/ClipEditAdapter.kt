package org.sopt.clip.clipedit

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.clip.ItemTouchHelperListener
import org.sopt.clip.databinding.ItemClipEditClipBinding
import org.sopt.model.category.Category
import org.sopt.ui.view.ItemDiffCallback

class ClipEditAdapter(
  private val itemClick: (Long, String, Long) -> Unit,
  private val deleteClip: (Long) -> Unit,
  private val patchClip: (Long , Int) -> Unit,
) : ListAdapter<Category, ClipEditViewHolder>(DiffUtil), ItemTouchHelperListener {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipEditViewHolder {
    return ClipEditViewHolder(
      ItemClipEditClipBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      itemClick,
      deleteClip
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
    patchClip(item?.categoryId?:0, to)
    newList.removeAt(from)
    if (item != null) {
      newList.add(to, item)
    }
    notifyItemMoved(from, to)
  }

  override fun onItemSwipe(position: Int) {
    val newList = ArrayList(currentList)
    Log.d("test", "${newList[position].categoryId ?: 0}")
    deleteClip(newList[position].categoryId ?: 0)
    newList.removeAt(position)
    notifyItemRemoved(position)
  }
}
