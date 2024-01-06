package org.sopt.timer

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.timer.databinding.FragmentExampleTimePickerBinding
import org.sopt.timer.dummymodel.PickerItem
import org.sopt.ui.base.BindingFragment

class ExampleTimePickerFragment : BindingFragment<FragmentExampleTimePickerBinding>({ FragmentExampleTimePickerBinding.inflate(it) }) {
  private lateinit var textAdapter: TextAdapter
  private lateinit var numberAdapter1: NumberAdapter
  private lateinit var numberAdapter2: NumberAdapter
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerViews()
  }

  private fun setupRecyclerViews() {
    val list1 = generateList1()
    val list2 = generateNumberList(1, 12)
    val list3 = generateNumberList(0, 59)
    textAdapter = TextAdapter()
    numberAdapter1 = NumberAdapter()
    numberAdapter2 = NumberAdapter()
    setupRecyclerView2(binding.rv1, list1, textAdapter)
    setupRecyclerView1(binding.rv2, list2, numberAdapter1)
    setupRecyclerView1(binding.rv3, list3, numberAdapter2)
  }

  private fun generateList1() = listOf(PickerItem("", false), PickerItem("오전", true), PickerItem("오후", false), PickerItem("", false))

  private fun generateNumberList(start: Int, end: Int): MutableList<PickerItem> {
    val list = mutableListOf<PickerItem>()
    list.add(PickerItem(end.toString(), false))
    list.add(PickerItem(start.toString(), true))
    for (i in start + 1..end - 1) {
      list.add(PickerItem(i.toString(), false))
    }
    return list
  }

  private fun setupRecyclerView1(recyclerView: RecyclerView, list: List<PickerItem>, numberAdapter: NumberAdapter) {
    val snapHolder = CenterSnapHelper().apply {
      attachToRecyclerView(recyclerView)
    }

    with(numberAdapter) {
      submitList(list)
      recyclerView.adapter = this
      numberAdapter
    }

    recyclerView.itemAnimator = null
    recyclerView.scrollToPosition(numberAdapter.getMiddlePosition())
    recyclerView.addOnScrollListener(
      object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
          super.onScrollStateChanged(recyclerView, newState)
          val centerView = snapHolder.findSnapView(recyclerView.layoutManager)
          val pos = recyclerView.layoutManager?.getPosition(centerView!!)
          val newList: MutableList<PickerItem> = numberAdapter.currentList.mapIndexed { index, item ->
            item.copy(isSelected = index == pos!! % numberAdapter.currentList.size)
          }.toMutableList()
          numberAdapter.run {
            submitList(newList)
            notifyDataSetChanged()
          }
        }
      },
    )
    recyclerView.onFlingListener = object : RecyclerView.OnFlingListener() {
      override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        val centerView = snapHolder.findSnapView(recyclerView.layoutManager)
        val pos = recyclerView.layoutManager?.getPosition(centerView!!)
        val newList: MutableList<PickerItem> = numberAdapter.currentList.mapIndexed { index, item ->
          item.copy(isSelected = index == pos!! % numberAdapter.currentList.size)
        }.toMutableList()
        numberAdapter.run {
          submitList(newList)
          notifyDataSetChanged()
        }
        return false
      }
    }
  }

  private fun setupRecyclerView2(recyclerView: RecyclerView, list: List<PickerItem>, textAdapter: TextAdapter) {
    val snapHolder = CenterSnapHelper().apply {
      attachToRecyclerView(recyclerView)
    }

    with(textAdapter) {
      submitList(list)
      recyclerView.adapter = this
      textAdapter
    }

    recyclerView.itemAnimator = null
    recyclerView.addOnScrollListener(
      object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
          super.onScrollStateChanged(recyclerView, newState)
          val centerView = snapHolder.findSnapView(recyclerView.layoutManager)
          val pos = recyclerView.layoutManager?.getPosition(centerView!!)
          val newList: MutableList<PickerItem> = textAdapter.currentList.mapIndexed { index, item ->
            item.copy(isSelected = index == pos)
          }.toMutableList()
          textAdapter.submitList(newList)
        }
      },
    )
    recyclerView.onFlingListener = object : RecyclerView.OnFlingListener() {
      override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        val centerView = snapHolder.findSnapView(recyclerView.layoutManager)
        val pos = recyclerView.layoutManager?.getPosition(centerView!!)
        val newList: MutableList<PickerItem> = textAdapter.currentList.mapIndexed { index, item ->
          item.copy(isSelected = index == pos)
        }.toMutableList()
        textAdapter.submitList(newList)
        return false
      }
    }
  }
}
