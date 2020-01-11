package com.example.myapplication.datailinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R

class DetailInfoFragment : Fragment() {

    companion object {
        fun newInstance() = DetailInfoFragment()
    }

    private lateinit var viewModel: DetailInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_info_fragment, container, false)
    }



}
