package com.example.databinding

import android.os.Bundle
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.databinding.databinding.ActMainBinding

class MainActivity: AppCompatActivity() {
    // 一定要Observable的
    var position: ObservableInt = ObservableInt(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActMainBinding = DataBindingUtil.setContentView(this, R.layout.act_main)

        val adapter = CustomPageAdapter(supportFragmentManager)
        binding.adapter = adapter
        // 无法直接用viewPager.currentItem改变UI 因为其内部变量不是Observable的
        binding.position = position
        binding.viewPager.addOnPageChangeListener(MyPageChangeListener())
    }

    inner class MyPageChangeListener: OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageSelected(position: Int) {
            this@MainActivity.position.set(position);
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, @Px positionOffsetPixels: Int) {

        }
    }
}