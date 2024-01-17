package org.sopt.clip.clipedit

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.sopt.clip.ItemTouchHelperListener
import org.sopt.model.category.Category

class ItemTouchCallback(private val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {

  override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
    val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
    val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

    return makeMovementFlags(dragFlags, swipeFlags)
  }

  override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
    listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    Log.d("test","test")
    return false
  }

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    listener.onItemSwipe(viewHolder.layoutPosition)
    Log.d("test","test2")
  }
}
