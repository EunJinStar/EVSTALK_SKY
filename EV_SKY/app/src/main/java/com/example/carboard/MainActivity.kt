package com.example.carboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bnv = findViewById<BottomNavigationView>(R.id.navigation)    // ID값 초기화
        val fl = findViewById<FrameLayout>(R.id.frame)
        // Fragment를 구현하는 방법 : NavigationView에서 선택한 메뉴에 따라 FrameLayout에 표시할 Fragment를 바꾼다.
        // 첫 화면을 MapFragment로 설정
        // FrameLayout에 MapFragment로 바꾸기. / replace(넣을 곳, 넣을 화면).commit()
        supportFragmentManager.beginTransaction().replace(R.id.frame, MapFragment()).commit()
        // bnv를 클릭했을 때..
        bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {                      // 내가 선택한 메뉴의 정보인 item이 어떤 id값을 가지고 있는지 판단
                R.id.tab1 -> {
                    Toast.makeText(this, "Map 화면", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, MapFragment()).commit()
                }
                R.id.tab2 -> {
                    Toast.makeText(this, "Community 화면", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, CommunityFragment()).commit()
                }
                R.id.tab3 -> {
                    Toast.makeText(this, "CarAdmin 화면", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, CarAdminFragment()).commit()
                }
                R.id.tab4 -> {
                    Toast.makeText(this, "Mypage 화면", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, MypageFragment()).commit()
                }
            }
            // event 종료 여부를 감지해서 표시해주는... : true는 종료->다음버튼클릭, false는 종료하지 않았다 판단
            true
        }
    }
}