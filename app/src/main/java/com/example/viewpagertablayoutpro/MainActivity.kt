package com.example.viewpagertablayoutpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagertablayoutpro.databinding.ActivityMainBinding
import com.example.viewpagertablayoutpro.databinding.UsertabButtonBinding
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationView
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
        //리사클러뷰 어뎁터설정 BEGIN
        customAdapter = CustomAdapter(this)
        customAdapter.addListFragment(OneFragment())
        customAdapter.addListFragment(TwoFragment())
        customAdapter.addListFragment(ThreeFragment())
        tabTitleList = mutableListOf("car", "home", "air")
        binding.viewpager2.adapter = customAdapter
        //리사클러뷰 어뎁터설정 end


        //액션바를 툴바로 대체 BEGIN
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close)
        // <- 업버튼을 활성화(Backpress 기능을 담당 이전화면으로 돌아가는 기능 액션바에 만들어주는 기능)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //업버튼자리에 토글버튼을 동기화시킴
        toggle.syncState()
        //액션바를 툴바로 대체 End

        // 네비게이션뷰에있는 메뉴항목을 클릭했을때 이벤트 처리
        binding.navigationview.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.item_age -> Toast.makeText(applicationContext,"Age",Toast.LENGTH_SHORT).show()
                    R.id.item_email -> Toast.makeText(applicationContext,"Email",Toast.LENGTH_SHORT).show()
                    R.id.item_info -> Toast.makeText(applicationContext,"Info",Toast.LENGTH_SHORT).show()
                    R.id.item_user -> Toast.makeText(applicationContext,"User   ",Toast.LENGTH_SHORT).show()
                }
                return true
            }

        })


        //탭레이아웃과 뷰 페이저 연동
        TabLayoutMediator(binding.tabLayout, binding.viewpager2) { tab, position ->
            tab.setCustomView(tabCustomView(position))
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //토클버튼이 눌러졌는지 체크
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
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

    }
