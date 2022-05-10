package com.lediya.dagger2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lediya.dagger2.R
import com.lediya.dagger2.databinding.ListScreenFragmentBinding
import com.lediya.dagger2.view.adapter.CountryAdapter
import com.lediya.dagger2.view.viewModel.ListScreenViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListScreenFragment : Fragment() {
    private lateinit var binding: ListScreenFragmentBinding
    private lateinit var viewModel: ListScreenViewModel
    private var adapter = CountryAdapter()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.list_screen_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(requireActivity()).get(ListScreenViewModel::class.java)
        binding.viewModel= viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDivider()
        observer()

    }

    override fun onStart() {
        super.onStart()
        viewModel.checkConnectivity()
    }
    private fun observer() {
        viewModel.title.observe(
            viewLifecycleOwner, Observer {
                (activity as AppCompatActivity).supportActionBar?.title = it
                binding.progressBar.visibility = View.GONE
            }
        )
    }
    private fun setDivider(){
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
    }
}