package org.sopt.savelink

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import designsystem.components.button.state.LinkMIndFullWidthButtonState
import org.sopt.savelink.databinding.FragmentSaveLinkBinding
import org.sopt.ui.base.BindingFragment

class SaveLinkFragment : BindingFragment<FragmentSaveLinkBinding>({ FragmentSaveLinkBinding.inflate(it) }) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.etvSaveCopyLink.apply {
      onClickTextClear {  }
      if(checkTextLength(15)) Log.d("test","test")
    }

    binding.btnSaveLinkNext.apply {
      state=LinkMIndFullWidthButtonState.DISABLE
    }
//    binding.root.setOnClickListener {
//      findNavController().navigateUp()
//    }
  }
}
