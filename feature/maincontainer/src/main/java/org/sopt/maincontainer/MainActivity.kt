package org.sopt.maincontainer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.maincontainer.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private var mBinding: ActivityMainBinding? = null
  private val binding get() = mBinding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)
    initView()
  }

  private fun initView() {
    val navController =
      supportFragmentManager.findFragmentById(R.id.fcv_main)?.findNavController()!!

    with(binding) {
      bnvMain.itemIconTintList = null
      navController?.let { NavController ->
        bnvMain.setupWithNavController(NavController)
      }
    }

    changeBottomNavigationFragment(navController)
  }
  private fun changeBottomNavigationFragment(navController: NavController) {
    binding.bnvMain.setOnItemSelectedListener {
      if (binding.bnvMain.menu.findItem(it.itemId).isChecked) {
        false
      } else {
        when (it.itemId) {
          R.id.navigation_home -> {
            navController.navigate(org.sopt.home.R.id.nav_graph_home)
            true
          }
          R.id.navigation_clip -> {
            navController.navigate(org.sopt.clip.R.id.nav_graph_clip)
            true
          }
          R.id.navigation_my -> {
            navController.navigate(org.sopt.mypage.R.id.nav_graph_mypage)
            true
          }
          R.id.navigation_timer -> {
            navController.navigate(org.sopt.timer.R.id.nav_graph_timer)
            true
          }
          else -> { false }
        }
      }
    }
  }
  /*private fun setBottomVisible(navController: NavController) {
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
  }*/
}
