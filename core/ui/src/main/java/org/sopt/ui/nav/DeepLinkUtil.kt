package org.sopt.ui.nav

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import org.sopt.ui.R

object DeepLinkUtil {
  fun getNavRequestPopUpAndOption(
    popUpToId: Int = -1,
    inclusive: Boolean = false,
    uri: String,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    return setNavRequestPopUpAndOption(
      popUpToId,
      inclusive,
      uri,
    )
  }

  fun getNavRequestNotPopUpAndOption(
    uri: String,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    return setNavRequestNotPopUpAndOption(
      uri,
    )
  }

  private fun setNavRequestNotPopUpAndOption(
    uri: String,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    val request = NavDeepLinkRequest.Builder
      .fromUri(uri.toUri())
      .build()

    val navOptions = NavOptions.Builder()
      .build()

    return (request to navOptions)
  }

  private fun setNavRequestPopUpAndOption(
    popUpToId: Int = -1,
    inclusive: Boolean = false,
    uri: String,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    val request = NavDeepLinkRequest.Builder
      .fromUri(uri.toUri())
      .build()

    val navOptions = NavOptions.Builder()
      .setPopUpTo(popUpToId, inclusive)
      .build()

    return (request to navOptions)
  }
}
