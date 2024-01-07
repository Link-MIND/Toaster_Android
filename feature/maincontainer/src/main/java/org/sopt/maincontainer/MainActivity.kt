package org.sopt.maincontainer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.maincontainer.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private var mBinding: ActivityMainBinding? = null
  private val binding get() = mBinding!!

  private lateinit var navController: NavController
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)
    initView()
  }

  private fun initView() {
    setFcv()
    changeBottomNavigationFragment()
    setBottomVisible()
  }

  private fun setFcv() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
    navController = navHostFragment.navController
    binding.bnvMain.itemIconTintList = null
    navController.graph.setStartDestination(org.sopt.home.R.id.nav_graph_home)
    binding.bnvMain.setupWithNavController(navController)
    binding.bnvMain.setOnItemReselectedListener { }
    changeBottomNavigationFragment()
  }

  private fun changeBottomNavigationFragment() {
    binding.bnvMain.setOnItemSelectedListener {
      if (binding.bnvMain.menu.findItem(it.itemId).isChecked) {
        false
      } else {
        navController.apply {
          popBackStack(org.sopt.clip.R.id.nav_graph_clip, false)
          popBackStack(org.sopt.timer.R.id.nav_graph_timer, false)
          popBackStack(org.sopt.mypage.R.id.nav_graph_mypage, false)
          if (it.itemId == R.id.navigation_home) {
            popBackStack(org.sopt.home.R.id.nav_graph_home, true)
          }
        }
        navigateToDestination(it.itemId)
      }
    }
  }
  private val navigationMap = mapOf(
    R.id.navigation_home to org.sopt.home.R.id.nav_graph_home,
    R.id.navigation_clip to org.sopt.clip.R.id.nav_graph_clip,
    R.id.navigation_my to org.sopt.mypage.R.id.nav_graph_mypage,
    R.id.navigation_timer to org.sopt.timer.R.id.nav_graph_timer,
  )

  private fun navigateToDestination(itemId: Int): Boolean {
    return navigationMap[itemId]?.let { destination ->
      navController.navigate(destination)
      true
    } ?: false
  }

  private fun setBottomVisible() {
    navController.addOnDestinationChangedListener { _, destination, _ ->
      binding.bnvMain.visibility = if (destination.id in listOf(
          R.id.navigation_home,
          R.id.navigation_clip,
          R.id.navigation_timer,
          R.id.navigation_my,
        )
      ) {
        View.VISIBLE
      } else {
        View.GONE
      }
    }
  }
}
