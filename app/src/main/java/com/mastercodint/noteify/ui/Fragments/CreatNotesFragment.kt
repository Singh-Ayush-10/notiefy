package com.mastercodint.noteify.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mastercodint.noteify.Notes
import com.mastercodint.noteify.NotesViewModel
import com.mastercodint.noteify.R
import com.mastercodint.noteify.databinding.FragmentCreatNotesBinding
import com.mastercodint.noteify.databinding.FragmentHomeBinding
import java.util.Date


class CreatNotesFragment : Fragment() {

    lateinit var binding: FragmentCreatNotesBinding
     var priority:String="1"
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentCreatNotesBinding.inflate(layoutInflater,container,false)

        binding.Pgreen.setImageResource(R.drawable.baseline_check_24)

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

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title=binding.edtTitle.text.toString()
        val subtitle=binding.edtSubtitle.text.toString()
        val notes=binding.edtNotes.text.toString()
        val d= Date()
        val notesDate:CharSequence= DateFormat.format("MMM d,yyyy",d.time)

        val data=Notes(null,
            title=title,
            subTitle=subtitle,
            notes=notes,
            notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_creatNotesFragment2_to_homeFragment2)

    }

}