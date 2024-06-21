package com.mastercodint.noteify.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.mastercodint.noteify.Notes
import com.mastercodint.noteify.NotesViewModel
import com.mastercodint.noteify.R
import com.mastercodint.noteify.databinding.FragmentHomeBinding
import com.mastercodint.noteify.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewmodel:NotesViewModel by viewModels()
    var oldNotes= arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_creatNotesFragment2)
        }
        viewmodel.getNotes().observe(viewLifecycleOwner,{ notesList->
             oldNotes= notesList as ArrayList<Notes>
            binding.recyclerView.layoutManager=GridLayoutManager(requireActivity(),2)
            adapter= NotesAdapter(requireContext(),notesList)
            binding.recyclerView.adapter= adapter
        })

        binding.filterHigh.setOnClickListener{
            viewmodel.getHighNotes().observe(viewLifecycleOwner,{ notesList->
                oldNotes= notesList as ArrayList<Notes>
                binding.recyclerView.layoutManager=GridLayoutManager(requireActivity(),2)
                adapter= NotesAdapter(requireContext(),notesList)
                binding.recyclerView.adapter= adapter
            })
        }
        binding.filterLow.setOnClickListener{
            viewmodel.getLowNotes().observe(viewLifecycleOwner,{ notesList->
                oldNotes= notesList as ArrayList<Notes>
                binding.recyclerView.layoutManager=GridLayoutManager(requireActivity(),2)
                adapter= NotesAdapter(requireContext(),notesList)
                binding.recyclerView.adapter= adapter
            })
        }
        binding.filterMedium.setOnClickListener {
            viewmodel.getMediumNotes().observe(viewLifecycleOwner,{ notesList->
                oldNotes= notesList as ArrayList<Notes>
                binding.recyclerView.layoutManager=GridLayoutManager(requireActivity(),2)
                adapter= NotesAdapter(requireContext(),notesList)
                binding.recyclerView.adapter= adapter
            })
        }
        binding.filter.setOnClickListener{
            viewmodel.getNotes().observe(viewLifecycleOwner,{ notesList->
                binding.recyclerView.layoutManager=GridLayoutManager(requireActivity(),2)
                binding.recyclerView.adapter=NotesAdapter(requireContext(),notesList)
            })
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.app_bar_search)
        val searchView=item.actionView as SearchView
        searchView.queryHint="Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltring(p0)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltring(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in oldNotes){
            if (i.title.contains(p0!!)||i.subTitle.contains(p0!!)||i.data.contains(p0!!))
                newFilteredList.add(i)
        }
        adapter.filtering(newFilteredList)
    }
}