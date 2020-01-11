package com.example.myapplication.newdata

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.databinding.NewInfoFragmentBinding
import com.example.myapplication.home.HomeFragmentDirections
import com.example.myapplication.home.HomeViewModel
import com.example.myapplication.home.HomeViewModelFactory

class NewInfoFragment : Fragment() {

    private var name  = "name"
    var info: String = "info"
    var age: String = "1"

    private lateinit var viewModel: NewInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<NewInfoFragmentBinding>(inflater,
            R.layout.new_info_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao


        binding.getText.setOnClickListener {
            name = binding.nameEditText.text.toString()
        }


        var viewModelFactory = NewInfoViewModelFactory(dataSource, name, age,
            info, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewInfoViewModel::class.java)

        viewModel.buttonAction.observe(this, Observer {user ->
            user?.let {
                findNavController().navigate(NewInfoFragmentDirections.actionNewInfoFragmentToHomeFragment())
            }
        })


        binding.lifecycleOwner = this
        binding.newInfoViewModel = viewModel





        return binding.root
    }



}
