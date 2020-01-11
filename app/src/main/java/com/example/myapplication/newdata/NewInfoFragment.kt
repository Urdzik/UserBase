package com.example.myapplication.newdata

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.User
import com.example.myapplication.databinding.NewInfoFragmentBinding

class NewInfoFragment : Fragment() {

    private lateinit var viewModel: NewInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<NewInfoFragmentBinding>(inflater,
            R.layout.new_info_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(NewInfoViewModel::class.java)

        binding.getText.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val info = binding.infoEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            val url = ""
            viewModel.insertNewUser(User(name, age, url, info))
        }

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
