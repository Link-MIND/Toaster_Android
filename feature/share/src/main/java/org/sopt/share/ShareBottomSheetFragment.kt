package org.sopt.share

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import designsystem.components.button.state.LinkMindButtonState
import org.orbitmvi.orbit.viewmodel.observe
import org.sopt.share.adapter.ClipSelectAdapter
import org.sopt.share.databinding.FragmentShareBottomSheetBinding
import org.sopt.share.databinding.FragmentShareBottomSheetBinding.inflate
import org.sopt.ui.base.BindingBottomSheetDialogFragment

@AndroidEntryPoint
class ShareBottomSheetFragment : BindingBottomSheetDialogFragment<FragmentShareBottomSheetBinding>({ inflate(it) }) {
  private lateinit var adapter: ClipSelectAdapter
  private val viewModel by activityViewModels<ShareViewModel>()

  private var whenDismiss: () -> Unit = {}

  @SuppressLint("ClickableViewAccessibility")
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.let { activity ->
      adjustRecyclerViewHeightToScreen(activity, binding.rvItemTimerClipSelect, 0.5f)
    }

    collectState()
    initClickListener()
    viewModel.getCategoryAll()
    initSetClipAdapter()
    initBottomSheetDraggable()
    initCloseButtonClickListener()
  }

  private fun initCloseButtonClickListener() {
    binding.ivShareBottomSheetClose.setOnClickListener {
      dismiss()
    }
  }

  private fun initBottomSheetDraggable() {
    binding.btnTimerClipSelectNext.state = LinkMindButtonState.ENABLE
    val bottomSheet = dialog?.findViewById<View>(R.id.design_bottom_sheet)
    val behavior = BottomSheetBehavior.from(bottomSheet!!)
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
    behavior.isDraggable = false

    binding.rvItemTimerClipSelect.addOnScrollListener(
      object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)
          val layoutManager = recyclerView.layoutManager as LinearLayoutManager
          val firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

          behavior.isDraggable = firstVisibleItemPosition == 0
        }
      },
    )
  }

  override fun onDestroyView() {
    super.onDestroyView()
    whenDismiss()
  }

  private fun collectState() = viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)

  private fun render(state: ShareState) {
    adapter.onClickClip = { clip, position ->
      if (clip.isSelected) {
        state.categoryList.onEach { it.isSelected = false }
        state.categoryList[position].isSelected = true
        viewModel.updateCategoryId(clip.categoryId)
        binding.btnTimerClipSelectNext.state = LinkMindButtonState.ENABLE
      } else {
        state.categoryList.onEach { it.isSelected = false }
      }
    }
    adapter.submitList(state.categoryList)
  }

  private fun handleSideEffect(sideEffect: ShareSideEffect) {
    if (sideEffect !is ShareSideEffect.ShareBottomSheetSideEffect) return
    when (sideEffect) {
      ShareSideEffect.ShareBottomSheetSideEffect.SaveSuccess -> {
        dismiss()
      }
    }
  }

  private fun initSetClipAdapter() {
    adapter = ClipSelectAdapter()
    binding.rvItemTimerClipSelect.adapter = adapter
  }

  private fun initClickListener() {
    binding.btnTimerClipSelectNext.apply {
      btnClick {
        if (state == LinkMindButtonState.DISABLE) return@btnClick
        viewModel.saveLink()
      }
    }
  }

  fun adjustRecyclerViewHeightToScreen(context: Context, recyclerView: RecyclerView, screenHeightRatio: Float) {
    val screenHeight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context as Activity)
      windowMetrics.bounds.height()
    } else {
      val displayMetrics = DisplayMetrics()
      @Suppress("DEPRECATION")
      (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
      displayMetrics.heightPixels
    }

    val maxRecyclerViewHeight = (screenHeight * screenHeightRatio).toInt()

    recyclerView.layoutParams = recyclerView.layoutParams.apply {
      height = maxRecyclerViewHeight
    }
  }

  companion object {
    fun newInstance(handleDismiss: () -> Unit): ShareBottomSheetFragment {
      return ShareBottomSheetFragment().apply {
        whenDismiss = handleDismiss
      }
    }
  }
}
