package com.example.assignment3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.assignment3.R
import com.example.assignment3.databinding.DetailsFragmentBinding
import com.example.assignment3.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.details_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)

        return binding.root
    }

}
