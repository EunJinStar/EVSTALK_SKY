package com.example.carboard


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView


class CarAdminFragment : Fragment() {

    lateinit var ChargeTab: TextView
    lateinit var AdminTab: TextView
    lateinit var selectedTextView : TextView
    lateinit var nonSelectedTextView : TextView
    // 현재 탭 위치 담는 변수
    private var selectedTabNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate한 loyout을 지속적으로 사용할 수 있도록 저장...
        var view = inflater.inflate(R.layout.fragment_car_admin, container, false)
        // 현재 이곳은 Activity가 아니기에!! fragment_home에 접근하려면 xml코드를 눈에 보이는 view로 바꿔주어야함.
        // inflater.intlate를 통해서 가능...
        // 바로 retrue를 하게 되면 사용이 불가능 하기에 view라는 변수에 저장해놓고 사용해야함.
        ChargeTab = view.findViewById(R.id.chargeTab)
        AdminTab = view.findViewById(R.id.adminTab)
        val FragmentCcontainerView = view.findViewById<FragmentContainerView>(R.id.fragmentCcontainerView)
        // 디폴트 1번 탭
        selectTab(1)
        // 충전 탭 클릭 이벤트
        ChargeTab.setOnClickListener {
            selectTab(1)
        }
        // 정비 탭 클릭 이벤트
        AdminTab.setOnClickListener {
            selectTab(2)
        }
        return view
    }
    // 탭 선택 이벤트
    private fun selectTab(tabNumber: Int){
        if (tabNumber == 1){
            selectedTextView = ChargeTab
            nonSelectedTextView = AdminTab
            // 플래그먼트 설정
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentCcontainerView, ChargeFragment()).commit()
        }else if(tabNumber == 2){
            selectedTextView = AdminTab
            nonSelectedTextView = ChargeTab
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentCcontainerView, AdminFragment()).commit()
        }
        // 이동할 위치 값 담은 변수 : (선택 탭 번호 - 현재 위치번호) * 텍스트뷰 넓이값
        var slideTo: Float = ((tabNumber - selectedTabNumber) * selectedTextView.width).toFloat()
        var translateAnimation = TranslateAnimation(0F,slideTo,0F,0F)
        // 애니메이션 구현 시간 (1000 = 1초)
        translateAnimation.duration = 300
        // 선택 번호에 따른 애니메이션 적용
        when(selectedTabNumber){
            1 -> ChargeTab.startAnimation(translateAnimation)
            2 -> AdminTab.startAnimation(translateAnimation)
        }
        // 애니메이션 이벤트
        translateAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }
            // 애니메이션 끝나면 실행
            override fun onAnimationEnd(p0: Animation?) {
                // 선택 탭 설정
                selectedTextView.setBackgroundResource(R.drawable.round_back_white100)
                selectedTextView.setTypeface(null, Typeface.BOLD)
                selectedTextView.setTextColor(Color.BLACK)
                // 비선택 탭 설정
                nonSelectedTextView.setBackgroundResource(android.R.color.transparent)
                nonSelectedTextView.setTypeface(null, Typeface.NORMAL)
                nonSelectedTextView.setTextColor(Color.parseColor("#80FFFFFF"))
            }
            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        // 탭 번호 선택탭 변수에 담기
        selectedTabNumber = tabNumber
    }
}