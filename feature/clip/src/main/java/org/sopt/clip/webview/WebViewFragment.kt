package org.sopt.clip.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.sopt.clip.databinding.FragmentWebviewBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.view.onThrottleClick

class WebViewFragment : BindingFragment<FragmentWebviewBinding>({ FragmentWebviewBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.wbClip.settings.javaScriptEnabled = true

    onClickClipLink()
    onClickWebViewClose()
    onClickWebViewReStart()
    handleReadBtn()
    handleWebViewNavigation()
    handleOpenInBrowser()
  }

  private fun onClickClipLink() {
    val url = arguments?.getString("url")
    url?.let {
      setupWebView(it)
    }
  }

  private fun setupWebView(url: String?) {
    val webView = binding.wbClip
    url?.let {
      webView.webViewClient = WebViewClient()
      webView.loadUrl(it)
      binding.tvWebviewAddress.text = it
    }
  }

  private fun handleReadBtn() {
    val btnReadAfter = binding.ivReadAfter
    val btnReadBefore = binding.ivReadBefore

    btnReadBefore.onThrottleClick {
      handleVisibility(btnReadBefore, btnReadAfter)
    }

    btnReadAfter.onThrottleClick {
      handleVisibility(btnReadAfter, btnReadBefore)
    }
  }

  private fun handleVisibility(visibleButton: View, invisibleButton: View) {
    visibleButton.isVisible = !visibleButton.isVisible
    invisibleButton.isVisible = !visibleButton.isVisible
  }

  private fun onClickWebViewClose() {
    binding.ivClose.onThrottleClick {
      findNavController().popBackStack()
    }
  }

  private fun onClickWebViewReStart() {
    val webView = binding.wbClip
    binding.ivWebviewRestart.onThrottleClick {
      webView.reload()
    }
  }

  private fun handleWebViewNavigation() {
    val ivBack = binding.ivBack
    val ivNext = binding.ivNext

    ivBack.onThrottleClick {
      if (binding.wbClip.canGoBack()) {
        binding.wbClip.goBack()
      }
    }

    ivNext.onThrottleClick {
      if (binding.wbClip.canGoForward()) {
        binding.wbClip.goForward()
      }
    }
  }

  private fun handleOpenInBrowser() {
    binding.ivInternet.onThrottleClick {
      val url = binding.wbClip.url
      if (url != null) {
        if (url.isNotBlank()) {
          val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
          startActivity(intent)
        }
      }
    }
  }
}
