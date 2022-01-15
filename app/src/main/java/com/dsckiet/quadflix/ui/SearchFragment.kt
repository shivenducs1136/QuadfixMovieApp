package com.dsckiet.quadflix.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat.getSystemService

import android.app.SearchManager
import android.content.Context
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.quadflix.R
import com.dsckiet.quadflix.Repo.Repository
import com.dsckiet.quadflix.ViewModel.MainViewModel
import com.dsckiet.quadflix.ViewModel.MainViewModelFactory
import com.dsckiet.quadflix.adapter.ActionAdapter
import com.dsckiet.quadflix.adapter.SearchAdapter
import com.dsckiet.quadflix.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    lateinit var binding:FragmentSearchBinding
    lateinit var viewmodel: MainViewModel
    private val feedAdapter: SearchAdapter by lazy{ SearchAdapter(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeSearchBtn.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewmodel = ViewModelProvider(requireActivity(),viewModelFactory).get(MainViewModel::class.java)
                viewmodel.getMovie(newText)
                viewmodel.myResponse.observe(viewLifecycleOwner, Observer {
                    feedAdapter.setStateWiseTracker(it)
                })
                binding.searchRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                binding.searchRecview.adapter = feedAdapter
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewmodel = ViewModelProvider(requireActivity(),viewModelFactory).get(MainViewModel::class.java)
                viewmodel.getMovie(query)
                viewmodel.myResponse.observe(viewLifecycleOwner, Observer {
                    feedAdapter.setStateWiseTracker(it)
                })
                binding.searchRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                binding.searchRecview.adapter = feedAdapter
                return false
            }

        })
    }
}