package org.sopt.clip.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.sopt.clip.R
import org.sopt.clip.databinding.FragmentWebviewBinding
import org.sopt.ui.base.BindingFragment
import org.sopt.ui.context.hideKeyboard
import org.sopt.ui.view.onThrottleClick

class WebViewFragment : BindingFragment<FragmentWebviewBinding>({ FragmentWebviewBinding.inflate(it) }) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.wbClip.settings.javaScriptEnabled = true
    val args: WebViewFragmentArgs by navArgs()
    val site = args.site
    setupWebView(site)
    onClickClipLink()
    onClickWebViewClose()
    onClickWebViewReStart()
    initReadBtnClickLister()
    initNavigationBtnClickListener()
    initBrowserBtnClickListener()
    initEditorActionListener()
  }

  private fun onClickClipLink() {
    val url = arguments?.getString("url")
    url?.let {
      setupWebView(it)
    }
  }

  private fun setupWebView(url: String?) {
    val webView = binding.wbClip
    val WebViewAddress = binding.tvWebviewAddress

    url?.let {
      webView.webViewClient = WebViewClient()
      webView.loadUrl(it)
      WebViewAddress.setText(it)
    }
  }

  private fun initEditorActionListener() {
    binding.tvWebviewAddress.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE ||
        actionId == EditorInfo.IME_NULL ||
        actionId == EditorInfo.IME_ACTION_SEND ||
        actionId == EditorInfo.IME_ACTION_NEXT
      ) {
        val enteredUrl = binding.tvWebviewAddress.text.toString()
        if (enteredUrl.isNotBlank()) {
          binding.wbClip.loadUrl(enteredUrl)
          requireContext().hideKeyboard(requireView())
        }
        true
      } else {
        false
      }
    }
  }

  private fun initReadBtnClickLister() {
    with(binding) {
      ivReadBefore.onThrottleClick {
        handleVisibility(ivReadBefore, ivReadAfter)
      }

      ivReadAfter.onThrottleClick {
        handleVisibility(ivReadAfter, ivReadBefore)
      }
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

  private fun initNavigationBtnClickListener() {
    with(binding) {
      ivBack.onThrottleClick {
        if (wbClip.canGoBack()) {
          wbClip.goBack()
        }
      }

      ivNext.onThrottleClick {
        if (wbClip.canGoForward()) {
          wbClip.goForward()
        }
      }

      updateColors()

      wbClip.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
          updateColors()
        }
      }
    }
  }

  private fun updateColors() {
    with(binding) {
      ivBack.setColorFilter(
        ContextCompat.getColor(
          requireContext(),
          if (wbClip.canGoBack()) org.sopt.mainfeature.R.color.neutrals800 else org.sopt.mainfeature.R.color.neutrals150,
        ),
      )

      ivNext.setColorFilter(
        ContextCompat.getColor(
          requireContext(),
          if (wbClip.canGoForward()) org.sopt.mainfeature.R.color.neutrals800 else org.sopt.mainfeature.R.color.neutrals150,
        ),
      )
    }
  }

  private fun initBrowserBtnClickListener() {
    binding.ivInternet.onThrottleClick {
      val url = binding.wbClip.url
      if (url.isNullOrEmpty()) return@onThrottleClick
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      startActivity(intent)
    }
  }
}
