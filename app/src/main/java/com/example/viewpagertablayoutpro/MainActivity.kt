package com.example.viewpagertablayoutpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagertablayoutpro.databinding.ActivityMainBinding
import com.example.viewpagertablayoutpro.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter
    lateinit var tabTitleList: MutableList<String>
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customAdapter = CustomAdapter(this)
        customAdapter.addListFragment(OneFragment())
        customAdapter.addListFragment(TwoFragment())
        customAdapter.addListFragment(ThreeFragment())
        tabTitleList = mutableListOf("car","home","air")
        binding.viewpager2.adapter = customAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager2){ tab,position ->
            tab.setCustomView(tabCustomView(position))
        }.attach()

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this,binding.,R.string.drawer_open,R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    fun tabCustomView(position:Int): View{
        val binding = UsertabButtonBinding.inflate(layoutInflater)
       when(position){
           0 -> binding.ivIcon.setImageResource(R.drawable.car_24)
           1 -> binding.ivIcon.setImageResource(R.drawable.houses_24)
           2 -> binding.ivIcon.setImageResource(R.drawable.airplane_24)

       }
        binding.tvTabName.text = tabTitleList.get(position)
        return binding.root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}