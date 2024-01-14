package org.sopt.ui.nav

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

object DeepLinkUtil {
  fun getNavRequestPopUpAndAnimption(
    popUpToId: Int = -1,
    inclusive: Boolean = false,
    uri: String,
    enterAnim: Int? = null,
    exitAnim: Int? = null,
    popEnterAnim: Int? = null,
    popExitAnim: Int? = null,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    return setNavRequestPopUpAndAnimOption(
      popUpToId,
      inclusive,
      uri,
      enterAnim,
      exitAnim,
      popEnterAnim,
      popExitAnim,
    )
  }

  fun getNavRequestNotPopUpAndOption(
    uri: String,
    enterAnim: Int? = null,
    exitAnim: Int? = null,
    popEnterAnim: Int? = null,
    popExitAnim: Int? = null,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    return setNavRequestNotPopUpAndAnimOption(
      uri,
      enterAnim,
      exitAnim,
      popEnterAnim,
      popExitAnim,
    )
  }

  private fun setNavRequestNotPopUpAndAnimOption(
    uri: String,
    enterAnim: Int? = null,
    exitAnim: Int? = null,
    popEnterAnim: Int? = null,
    popExitAnim: Int? = null,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    val request = NavDeepLinkRequest.Builder
      .fromUri(uri.toUri())
      .build()
    if (enterAnim == null || exitAnim == null || popEnterAnim == null || popExitAnim == null) {
      val navOptions = NavOptions.Builder()
        .build()
      return (request to navOptions)
    } else {
      val navOptions = NavOptions.Builder()
        .setEnterAnim(enterAnim)
        .setExitAnim(exitAnim)
        .setPopEnterAnim(popEnterAnim)
        .setPopExitAnim(popExitAnim)
        .build()
      return (request to navOptions)
    }
  }

  private fun setNavRequestPopUpAndAnimOption(
    popUpToId: Int = -1,
    inclusive: Boolean = false,
    uri: String,
    enterAnim: Int? = null,
    exitAnim: Int? = null,
    popEnterAnim: Int? = null,
    popExitAnim: Int? = null,
  ): Pair<NavDeepLinkRequest, NavOptions> {
    val request = NavDeepLinkRequest.Builder
      .fromUri(uri.toUri())
      .build()
    if (enterAnim == null || exitAnim == null || popEnterAnim == null || popExitAnim == null) {
      val navOptions = NavOptions.Builder()
        .setPopUpTo(popUpToId, inclusive)
        .build()
      return (request to navOptions)
    } else {
      val navOptions = NavOptions.Builder()
        .setPopUpTo(popUpToId, inclusive)
        .setEnterAnim(enterAnim)
        .setExitAnim(exitAnim)
        .setPopEnterAnim(popEnterAnim)
        .setPopExitAnim(popExitAnim)
        .build()
      return (request to navOptions)
    }
  }
}
