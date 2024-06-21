package com.mastercodint.noteify.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mastercodint.noteify.Notes
import com.mastercodint.noteify.R
import com.mastercodint.noteify.databinding.ItemNotesBinding
import com.mastercodint.noteify.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,var notesList:List<Notes>):
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

        fun filtering(newFilteredList: ArrayList<Notes>) {
            notesList=newFilteredList
            notifyDataSetChanged()
        }

    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data =notesList[position]
        holder.binding.title.text=data.title.toString()
        holder.binding.discription.text=data.subTitle.toString()
        holder.binding.date.text=data.data.toString()
        when(data.priority){
            "1"->{
                holder.binding.viewDot.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewDot.setBackgroundResource(R.drawable.yello_dot)
            }
            "3"->{
                holder.binding.viewDot.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragment2ToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }
}