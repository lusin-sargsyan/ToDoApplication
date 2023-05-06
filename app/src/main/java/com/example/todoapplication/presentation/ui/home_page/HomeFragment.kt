package com.example.todoapplication.presentation.ui.home_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.databinding.FragmentHomeBinding
import com.example.todoapplication.domain.entities.Title
import com.example.todoapplication.presentation.adapter.TitleAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var titleAdapter: TitleAdapter
    private val viewModel by viewModel<TitleViewModel>()
    private lateinit var myTitle: Title

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        collectViewModel()
        initAdapter()
        initClickListeners()

        return binding.root
    }

    private fun initAdapter() {
        titleAdapter = TitleAdapter(viewModel) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToListFragment(
                    it
                )
            )
        }
        with(binding.recyclerViewTitles) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = titleAdapter
        }
    }

    private fun initClickListeners() {
        with(binding) {
            btnDone.setOnClickListener {
                if (etTitle.text.toString().isNotEmpty()) {
                    myTitle = Title(
                        0,
                        etTitle.text.toString(),
                        emptyList()
                    )
                    viewModel.addTitle(
                        myTitle
                    )
                    etTitle.setText("")
                }
            }
        }
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            viewModel.getAllTitle()
            viewModel.getAllTitles.first().collect {
                titleAdapter.submitList(it)
            }
        }
    }
}
