package com.example.databinding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActNoteDetailBinding

class NoteDetailActivity: AppCompatActivity() {
    companion object {
        const val CODE_DONE = 99
    }

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = intent?.getParcelableExtra("note")

        val binding: ActNoteDetailBinding = DataBindingUtil.setContentView(this, R.layout.act_note_detail)
        binding.note = note
        binding.presenter = Presenter()
    }

    inner class Presenter {
        fun onDone() {
            val intent = Intent()
            intent.putExtra("note", note)
            setResult(CODE_DONE, intent)
            finish()
        }
    }
}