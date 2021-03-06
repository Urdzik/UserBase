package com.example.myapplication.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater, R.layout.home_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        val adapter = UserAdapter(UserListener { userId -> viewModel.onUserClicked(userId) })

        viewModel.buttonAction.observe(this, Observer { btn ->
            btn?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewInfoFragment())
                viewModel.doneNavigated()
            }
        })

        viewModel.navigationToDetailInfo.observe(this, Observer { night->
            night?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailInfoFragment(night))
                viewModel.onUserDetailInfoNavigated()
            }
        })


        binding.lifecycleOwner = this

        binding.homeViewModel = viewModel

        binding.userList.adapter  = adapter

        viewModel.users.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }


}
