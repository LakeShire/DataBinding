package com.example.databinding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.databinding.databinding.FraNoteListBinding

class NoteListFragment: Fragment() {
    private var notes: ArrayList<Note> = ArrayList()
    private lateinit var adapter: CommonAdapter<Note>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FraNoteListBinding = DataBindingUtil.inflate(inflater, R.layout.fra_note_list, container, true)

        for (i in 1..10) {
            val title = "Note #$i"
            val message = "Note #$i"
            val note = Note(i, title, message)
            notes.add(note)
        }
        adapter = CommonAdapter(this.context, notes, R.layout.item_note, BR.note, NoteItemPresenter());

        binding.adapter = adapter;
        binding.presenter = Presenter()
        return binding.root
    }

    inner class Presenter {
        fun onAdd() {
            val count = notes.size + 1
            val title = "Note #$count"
            val message = "Note #$count"
            val note = Note(count, title, message)
            notes.add(0, note)
            adapter.notifyDataSetChanged()
        }
    }

    inner class NoteItemPresenter : ItemPresenter<Note> {
        override fun onClick(item: Note) {
            val intent = Intent(this@NoteListFragment.activity, NoteDetailActivity::class.java)
            intent.putExtra("note", item)
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
                    adapter.notifyDataSetChanged()
                    break;
                }
            }
        }
    }
}