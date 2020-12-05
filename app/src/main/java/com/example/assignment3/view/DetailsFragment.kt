package com.example.assignment3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.assignment3.R
import com.example.assignment3.data.db.AnimalDao
import com.example.assignment3.databinding.DetailsFragmentBinding
import com.example.assignment3.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.animal_list_item.*
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject


class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()

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

        viewModel = ViewModelProvider(activity as MainActivity).get(DetailsViewModel::class.java)


        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = args.animal.photoFullUrl
        Glide.with(context!!).load(url).into(main_photo_image)
        name.text = args.animal.name
        description.text = args.animal.description

        close_button.setOnClickListener {
            activity?.onBackPressed()
        }
    }


}
