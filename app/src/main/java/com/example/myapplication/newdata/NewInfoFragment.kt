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
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.databinding.NewInfoFragmentBinding

class NewInfoFragment : Fragment() {

    private lateinit var viewModel: NewInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<NewInfoFragmentBinding>(inflater,
            R.layout.new_info_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = NewInfoViewModelFactory(dataSource,  application)

        binding.goToHomeBtn.setOnClickListener {
            val name  = binding.nameEditText.text.toString()
            val info = binding.infoEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            Log.i("age", age.toString())
            val url = binding.urlEditText.toString()
                viewModel.createNewUser(name, age, info, url)

        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewInfoViewModel::class.java)

        viewModel.userData.observe(this, Observer { user ->
            user?.let {
                findNavController().navigate(NewInfoFragmentDirections.actionNewInfoFragmentToHomeFragment())
                viewModel.doneNavigating()
            }
        })

        binding.lifecycleOwner = this
        binding.newInfoViewModel = viewModel

        return binding.root
    }



}
