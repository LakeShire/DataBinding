package com.example.databinding

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActNoteListBinding

class NoteListActivity : AppCompatActivity() {
    private var notes: ArrayList<Note> = ArrayList()
    private var user: UserModel? = null
    private var name: String = ""
    private var adapter: CommonAdapter<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent?.getParcelableExtra("user")
        name = if (user == null || TextUtils.isEmpty(user?.name)) {
            "UNKNOWN"
        } else {
            user?.name.toString()
        }

        val binding: ActNoteListBinding = DataBindingUtil.setContentView(this, R.layout.act_note_list)

        for (i in 1..10) {
            val title = "$name #$i"
            val message = "$name #$i"
            val note = Note(i, title, message)
            notes.add(note)
        }
        adapter = CommonAdapter(this, notes, R.layout.item_note, BR.note, NoteItemPresenter());

        binding.adapter = adapter;

        val presenter = Presenter()
        binding.presenter = presenter
    }

    inner class Presenter {
        fun onAdd() {
            val count = notes.size + 1
            val title = "$name #$count"
            val message = "$name #$count"
            val note = Note(count, title, message)
            notes.add(0, note)
            adapter?.notifyDataSetChanged()
        }
    }

    inner class NoteItemPresenter : ItemPresenter<Note> {
        override fun onClick(note: Note) {
            val intent = Intent(this@NoteListActivity, NoteDetailActivity::class.java)
            intent.putExtra("note", note)
            startActivityForResult(intent, NoteDetailActivity.CODE_DONE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NoteDetailActivity.CODE_DONE) {
            val note = data?.getParcelableExtra<Note>("note")
            for (item in notes) {
                if (item.id == note?.id) {
                    item.title = note.title
                    item.message = note.message
                    adapter?.notifyDataSetChanged()
                    break;
                }
            }
        }
    }
}