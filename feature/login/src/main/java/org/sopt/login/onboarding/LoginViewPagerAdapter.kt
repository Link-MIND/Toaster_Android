package org.sopt.login.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.login.R

class LoginViewPagerAdapter(private val context: Context) : RecyclerView.Adapter<LoginViewPagerAdapter.ItemViewHolder>() {

  private val layoutResIds = listOf(
    R.layout.item_onboarding_first,
    R.layout.item_onboarding_second,
    R.layout.item_onboarding_third,
    R.layout.item_onboarding_fourth
  )

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val itemView = LayoutInflater.from(context).inflate(layoutResIds[viewType], parent, false)
    return ItemViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
  }

  override fun getItemCount() = layoutResIds.size

  override fun getItemViewType(position: Int): Int {
    return position
  }

  inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  }
}
