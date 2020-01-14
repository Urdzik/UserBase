package com.example.myapplication.datailinfo

import android.os.Bundle
import android.util.Log
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
import com.example.myapplication.databinding.DetailInfoFragmentBinding

class DetailInfoFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<DetailInfoFragmentBinding>(
            inflater,
            R.layout.detail_info_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val arguments = DetailInfoFragmentArgs.fromBundle(arguments!!).key
        Log.i("id", arguments.toString())

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModalFactory = DetailInfoViewModalFactory(arguments, dataSource)

        val viewModel = ViewModelProviders.of(this, viewModalFactory).get(DetailInfoViewModel::class.java)

        binding.detailViewModal = viewModel
        binding.lifecycleOwner = this

        viewModel.navigationToHomeFragment.observe(this, Observer {
            if (it == true) {
                findNavController().navigate(DetailInfoFragmentDirections.actionDetailInfoFragmentToHomeFragment())
                viewModel.doneNavigated()
            }
        })





        return binding.root
    }


}
