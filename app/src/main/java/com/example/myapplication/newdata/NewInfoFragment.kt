package com.example.myapplication.newdata

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
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.databinding.NewInfoFragmentBinding

class NewInfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<NewInfoFragmentBinding>(
            inflater,
            R.layout.new_info_fragment, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = NewInfoViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewInfoViewModel::class.java)

        binding.goToHomeBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val info = binding.infoEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            viewModel.createNewUser(name, age, info)

        }

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
