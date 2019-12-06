package com.example.databinding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActLoginBinding

class LoginActivity : AppCompatActivity() {
    var user: UserModel = UserModel()
    var presenter: Presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActLoginBinding = DataBindingUtil.setContentView(this, R.layout.act_login)

        binding.presenter = presenter
        binding.user = user
    }

    inner class Presenter {
        fun onClick() {
            val string = this@LoginActivity.user.name + "-" + this@LoginActivity.user.password
            // 注意外部类对象的用法
            Toast.makeText(this@LoginActivity.applicationContext, "登录: $string", Toast.LENGTH_SHORT).show()
            // 注意这个类名的写法
            val intent = Intent(this@LoginActivity, NoteListActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}
