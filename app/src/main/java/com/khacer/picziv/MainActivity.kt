package com.khacer.picziv

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khacer.picziv.adapter.MainAdapter
import com.khacer.picziv.databinding.ActivityMainBinding
import com.khacer.picziv.ui.follow.FollowFragment
import com.khacer.picziv.ui.home.HomeFragment
import com.khacer.picziv.ui.rank.RankFragment
import com.khacer.picziv.ui.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val fragments = ArrayList<Fragment>()
    val navMenuIds = ArrayList<Int>()

    private fun init() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // initialize
        init()

        // request permission no need here
        // DynamicPermission.get(this, 1)

        // initial layouts
        fragments.add(HomeFragment())
        fragments.add(FollowFragment())
        fragments.add(RankFragment())
        fragments.add(SettingFragment())

        // set viewPager adapter
        binding.viewPager.adapter = MainAdapter(supportFragmentManager, lifecycle, fragments)
        binding.viewPager.offscreenPageLimit = fragments.size

        // bind id with navMenuButton
        for (item in binding.navView.menu) {
            navMenuIds.add(item.itemId)
        }

        // switch navMenuButton when page changed
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.navView.selectedItemId = binding.navView.menu[position].itemId
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        // switch page when navMenuButton is select
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                else -> {
                    binding.viewPager.currentItem = navMenuIds.indexOf(item.itemId)
                    true
                }
            }
        }
    }
}