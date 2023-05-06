package com.example.todoapplication.presentation.adapter

import android.app.AlertDialog
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.EditDialogBinding
import com.example.todoapplication.databinding.ItemTodoListBinding
import com.example.todoapplication.domain.entities.ToDo
import com.example.todoapplication.presentation.ui.list_page.ToDoViewModel

class ToDoAdapter(
    private val viewModel: ToDoViewModel,
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val context: Context,
    private val click: (ToDo) -> Unit
) :
    ListAdapter<ToDo, ToDoAdapter.MyViewHolder>(DiffUtilItemCallBack()) {

    private lateinit var editDialog: EditDialogBinding
    private lateinit var alertDialog: AlertDialog

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTodoListBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(val binding: ItemTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo) {
            with(binding) {
                if (!toDo.check) {
                    todoText.text = toDo.toDo
                } else {
                    val spannableString = SpannableString(toDo.toDo)
                    val strikethroughSpan = StrikethroughSpan()
                    spannableString.setSpan(
                        strikethroughSpan,
                        0,
                        toDo.toDo.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    todoText.text = spannableString
                }
                checkbox.isChecked = toDo.check
                icTodoDelete.setOnClickListener {
                    click(toDo)
                }
                icTodoEdit.setOnClickListener {
                    editDialog = EditDialogBinding.inflate(inflater, container, false)
                    alertDialog = AlertDialog.Builder(context).apply {
                        setView(editDialog.root)
                        editDialog.etEditText.setText(toDo.toDo)
                    }.show()


                    editDialog.btnEditTodo.setOnClickListener {
                        if (!editDialog.etEditText.text?.trim().isNullOrEmpty()) {
                            viewModel.editTodo(
                                toDo.copy(toDo = editDialog.etEditText.text.toString())
                            )
                        }
                        alertDialog.dismiss()
                    }
                    alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                }

                checkbox.setOnClickListener {
                    viewModel.updateTodo(toDo.copy(check = checkbox.isChecked))
                }
            }
        }
    }

    class DiffUtilItemCallBack : DiffUtil.ItemCallback<ToDo>() {

        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
            oldItem == newItem
    }
}