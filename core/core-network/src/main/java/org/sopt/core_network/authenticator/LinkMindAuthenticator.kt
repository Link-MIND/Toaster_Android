package org.sopt.core_network.authenticator


import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class LinkMindAuthenticator():Authenticator {
  override fun authenticate(route: Route?, response: Response): Request? {
    TODO("Not yet implemented")
  }
}
