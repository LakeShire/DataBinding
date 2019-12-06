package com.example.databinding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.databinding.databinding.FraNoteListBinding
import com.example.databinding.databinding.FraSettingBinding

class SettingFragment: Fragment() {
    private var notes: ArrayList<Note> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FraSettingBinding = DataBindingUtil.inflate(inflater, R.layout.fra_setting, container, true)
        return binding.root
    }
}