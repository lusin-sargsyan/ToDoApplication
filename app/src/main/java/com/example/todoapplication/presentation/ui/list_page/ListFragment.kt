package com.example.todoapplication.presentation.ui.list_page

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.databinding.FragmentListBinding
import com.example.todoapplication.databinding.TodoDialogBinding
import com.example.todoapplication.domain.entities.ToDo
import com.example.todoapplication.presentation.adapter.ToDoAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var dialog: TodoDialogBinding
    private lateinit var alertDialog: AlertDialog
    private val viewModel by viewModel<ToDoViewModel>()
    private val args by navArgs<ListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        collectViewModel()
        initAdapter(inflater, container)
        initClickListeners(inflater, container)

        return binding.root
    }

    private fun initDialog(inflater: LayoutInflater, container: ViewGroup?) {
        dialog = TodoDialogBinding.inflate(inflater, container, false)
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialog.root)
            .show()

        dialog.btnAddTodo.setOnClickListener {
            if (!dialog.edText.text?.trim().isNullOrEmpty()) {
                viewModel.addTodo(
                    ToDo(
                        0,
                        args.userId,
                        dialog.edText.text.toString(),
                        false
                    )
                )
            }
            alertDialog.dismiss()
        }
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        toDoAdapter = ToDoAdapter(viewModel, inflater, container, requireContext()) {
            viewModel.deleteTodo(it)
        }
        with(binding.recyclerViewToDo) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = toDoAdapter
        }
    }

    private fun collectViewModel() {
        viewModel.getTitle(args.userId)
        lifecycleScope.launch {
            viewModel.getTitle.collect {
                binding.tbHeading.title = it.title
            }
        }
        viewModel.getAllToDo(args.userId)
        lifecycleScope.launch {
            viewModel.getAllToDos.first().collect {
                toDoAdapter.submitList(it)
            }
        }
    }

    private fun initClickListeners(inflater: LayoutInflater, container: ViewGroup?) {
        binding.floatingAddTodo.setOnClickListener {
            initDialog(inflater, container)
        }
    }
}