package com.dsckiet.quadflix.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.quadflix.R
import com.dsckiet.quadflix.Repo.Repository
import com.dsckiet.quadflix.ViewModel.*
import com.dsckiet.quadflix.adapter.*
import com.dsckiet.quadflix.databinding.FragmentHomeBinding
import com.dsckiet.quadflix.model.Movies


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel2: MainViewModel2
    private lateinit var viewModel3: MainViewModel3
    private lateinit var viewModel4: MainViewModel4
    private lateinit var viewModel5: MainViewModel5
    private val feedAdapter1:ActionAdapter by lazy{ActionAdapter(requireContext())}
    private val feedAdapter2:DramaAdapter by lazy{DramaAdapter(requireContext())}
    private val feedAdapter3:ComedyAdapter by lazy{ComedyAdapter(requireContext())}
    private val feedAdapter4:HorrorAdapter by lazy{HorrorAdapter(requireContext())}
    private val feedAdapter5:ThrillerAdapter by lazy{ThrillerAdapter(requireContext())}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeSearchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.progressBar.visibility = View.VISIBLE
            drama()
            action()
            comedy()
            horror()
            thriller()

    }

     fun thriller() {
         val repository = Repository()
         val viewModelFactory = MainViewModelFactory5(repository)
         viewModel5 = ViewModelProvider(this,viewModelFactory).get(MainViewModel5::class.java)
                viewModel5.getMovie("Thriller")
                viewModel5.myResponse.observe(viewLifecycleOwner, Observer {
                        feedAdapter5.setStateWiseTracker(it)
                    binding.progressBar.visibility = View.GONE
                })
                binding.thrillerRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                binding.thrillerRecview.adapter = feedAdapter5


    }

     fun horror() {
         val repository = Repository()
         val viewModelFactory = MainViewModelFactory4(repository)
         viewModel4 = ViewModelProvider(this,viewModelFactory).get(MainViewModel4::class.java)
        viewModel4.getMovie("Horror")
        viewModel4.myResponse.observe(viewLifecycleOwner, Observer {
            Log.d("BOLTy","success"+it.toString())
                feedAdapter4.setStateWiseTracker(it)
        })
        binding.horrorRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.horrorRecview.adapter = feedAdapter4
    }

     fun comedy() {
         val repository = Repository()
         val viewModelFactory = MainViewModelFactory3(repository)
         viewModel3 = ViewModelProvider(this,viewModelFactory).get(MainViewModel3::class.java)
        viewModel3.getMovie("Comedy")
        viewModel3.myResponse.observe(viewLifecycleOwner, Observer {
                feedAdapter3.setStateWiseTracker(it)
        })
        binding.comedyRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.comedyRecview.adapter = feedAdapter3

    }

     fun drama() {
         val repository = Repository()
         val viewModelFactory = MainViewModelFactory2(repository)
         viewModel2 = ViewModelProvider(this,viewModelFactory).get(MainViewModel2::class.java)
        viewModel2.getMovie("Drama")
        viewModel2.myResponse.observe(viewLifecycleOwner, Observer {
                feedAdapter2.setStateWiseTracker(it)
        })
        binding.dramaRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.dramaRecview.adapter = feedAdapter2

    }

     fun action() {
         val repository = Repository()
         val viewModelFactory = MainViewModelFactory(repository)
         viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getMovie("Action")
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
                feedAdapter1.setStateWiseTracker(it)
        })
        binding.actionRecview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.actionRecview.adapter = feedAdapter1

    }

}
