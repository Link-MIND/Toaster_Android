package org.sopt.clip

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.clip.databinding.FragmentClipBinding
import org.sopt.ui.base.BindingFragment

class ClipFragment : BindingFragment<FragmentClipBinding>({ FragmentClipBinding.inflate(it) }) {
  private val viewModel by viewModels<ClipViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_clip, container, false)
  }

  companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClipFragment.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      ClipFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}
