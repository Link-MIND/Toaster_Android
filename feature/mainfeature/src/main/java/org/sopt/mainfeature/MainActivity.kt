package org.sopt.mainfeature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import org.sopt.mainfeature.databinding.ActivityMainBinding

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

    setBottomVisible(navController)
  }

  private fun setBottomVisible(navController: NavController) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
      binding.bnvMain.visibility = if (destination.id in listOf(
          R.id.navigation_home,
          R.id.navigation_clip,
          R.id.navigation_timer,
          R.id.navigation_my,
        )
      ) View.VISIBLE else View.GONE

    }
  }

}
