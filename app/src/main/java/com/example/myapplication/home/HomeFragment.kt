package com.example.myapplication.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater, R.layout.home_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)



        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.buttonAction.observe(this, Observer { btn ->
            btn?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewInfoFragment())

            }
        })






        binding.lifecycleOwner = this

        binding.homeViewModel = viewModel




        return binding.root
    }


}
