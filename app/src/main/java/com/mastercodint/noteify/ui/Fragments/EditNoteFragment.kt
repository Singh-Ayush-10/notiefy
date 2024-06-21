package com.mastercodint.noteify.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mastercodint.noteify.Notes
import com.mastercodint.noteify.NotesViewModel
import com.mastercodint.noteify.R
import com.mastercodint.noteify.databinding.FragmentEditNoteBinding
import java.util.Date


class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority:String="1"
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEditNoteBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubtitle.setText((oldNotes.data.subTitle))
        binding.edtNotes.setText(oldNotes.data.notes)
        
        when(oldNotes.data.priority){
            "1"->{
                priority="1"
                binding.Pgreen.setImageResource(R.drawable.baseline_check_24)
                binding.Pred.setImageResource(0)
                binding.Pyellow.setImageResource(0)
            }
            "2"->{
                priority="2"
                binding.Pyellow.setImageResource(R.drawable.baseline_check_24)
                binding.Pred.setImageResource(0)
                binding.Pgreen.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.Pred.setImageResource(R.drawable.baseline_check_24)
                binding.Pgreen.setImageResource(0)
                binding.Pyellow.setImageResource(0)
            }
        }
        binding.Pgreen.setOnClickListener{
            priority="1"
            binding.Pgreen.setImageResource(R.drawable.baseline_check_24)
            binding.Pred.setImageResource(0)
            binding.Pyellow.setImageResource(0)
        }
        binding.Pyellow.setOnClickListener{
            priority="2"
            binding.Pyellow.setImageResource(R.drawable.baseline_check_24)
            binding.Pred.setImageResource(0)
            binding.Pgreen.setImageResource(0)
        }
        binding.Pred.setOnClickListener{
            priority="3"
            binding.Pred.setImageResource(R.drawable.baseline_check_24)
            binding.Pgreen.setImageResource(0)
            binding.Pyellow.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener { 
            updateNotes(it)
        }
        return binding.root

    }

    private fun updateNotes(it: View?) {
        val title=binding.edtTitle.text.toString()
        val subtitle=binding.edtSubtitle.text.toString()
        val notes=binding.edtNotes.text.toString()
        val d= Date()
        val notesDate:CharSequence= DateFormat.format("MMM d,yyyy",d.time)

        val data= Notes(
            oldNotes.data.id,
            title=title,
            subTitle=subtitle,
            notes=notes,
            notesDate.toString(),
            priority
        )
        viewModel.updateNote(data)
        Toast.makeText(requireContext(),"Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment2)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delet_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==R.id.menu_delete){
            val bottomSheet:BottomSheetDialog=BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delet)
            bottomSheet.show()

            val textViewYes=bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo=bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                view?.let {
                    Navigation.findNavController(it).navigate(R.id.action_editNoteFragment_to_homeFragment2)
                }
                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}