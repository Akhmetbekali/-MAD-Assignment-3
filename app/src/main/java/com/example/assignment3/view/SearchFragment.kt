package com.example.assignment3.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.R
import com.example.assignment3.data.db.AnimalDbModel
import com.example.assignment3.databinding.SearchFragmentBinding
import com.example.assignment3.view.adapter.AnimalItemAdapter
import com.example.assignment3.view.adapter.VerticalItemDecorator
import com.example.assignment3.viewmodel.SearchViewModel
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var binding: SearchFragmentBinding

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )

        val adapter = AnimalItemAdapter()

        binding.recyclerView.addItemDecoration(
            VerticalItemDecorator(
                5,
                true
            )
        )

        viewModel = (activity as MainActivity).viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(context);

        binding.recyclerView.adapter = adapter


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.allAnimals.observe(this.viewLifecycleOwner, Observer { list ->
            setAnimals(list)
        })

        viewModel.allTypes.observe(this.viewLifecycleOwner, Observer { types ->
            val animalKindSpinnerTypesAdapter = createAdapter(types)
            binding.animalKindSpinner.adapter = animalKindSpinnerTypesAdapter
           // TODO Add binding logic

        })

       viewModel.breeds.observe(this.viewLifecycleOwner, Observer { breeds ->
           val breedSpinnerTypesAdapter = createAdapter(breeds)
           binding.breedSpinner.adapter = breedSpinnerTypesAdapter
           // TODO Add binding logic
       })


        viewModel.networkActive.observe(this.viewLifecycleOwner, Observer { isActive ->
            if (!isActive) {
                Toast.makeText(
                    activity!!,
                    R.string.no_internet, Toast.LENGTH_LONG
                ).show()

            }
        })

        binding.animalKindSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // TODO Implement logic
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (parent?.selectedItem.toString() == "Any") {
                        viewModel.findAnimals(null, null)
                    }
                    else{
                        viewModel.findAnimals(parent?.selectedItem.toString(), null)
                    }
                    viewModel.selectType(parent?.selectedItem.toString())
                    // TODO Implement logic
                }
            }

        binding.breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Implement logic
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent?.selectedItem.toString() != "Any") {
                    viewModel.findAnimals(binding.animalKindSpinner.selectedItem.toString(), parent?.selectedItem.toString())
                }
                // TODO Implement logic
            }

        }
    }

    fun createAdapter(array: List<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            array
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    fun setAnimals(animalDbModels: List<AnimalDbModel>) {
        (binding.recyclerView.adapter as AnimalItemAdapter).setAnimalList(animalDbModels)
    }

}
