package com.example.fw5_nmf;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fw5_nmf.databinding.ItemTodoBinding;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    public ArrayList<Todo> localDataSet;
    private OnDeleteClickListener onDeleteClickListener;
    private OnTextClickListener onTextClickListener;
    public interface OnDeleteClickListener {
        void onClickDeleteIcon(int todo);
    }
    public interface OnTextClickListener {
        void onClickText(int todo);
    }
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public ItemTodoBinding binding;

        public TodoViewHolder(@NonNull ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        int todo = getAdapterPosition();
                        if (todo != RecyclerView.NO_POSITION) {
                            onDeleteClickListener.onClickDeleteIcon(todo);
                        }
                    }
                }
            });
            binding.todoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTextClickListener != null) {
                        int todo = getAdapterPosition();
                        if (todo != RecyclerView.NO_POSITION) {
                            onTextClickListener.onClickText(todo);
                        }
                    }
                }
            });
        }
        public TextView getTextView() {
            return binding.todoText;
        }
    }

//    public TodoAdapter(ArrayList<String> dataSet){
    public TodoAdapter(ArrayList<Todo> dataSet){
        localDataSet = dataSet;
//        Log.d("greentea", "THIS IS INIT OF TODOADAPTER");
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public void setOnTextClickListener(OnTextClickListener listener) {
        this.onTextClickListener = listener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        ItemTodoBinding binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        Log.d("greentea", "create viewholder");
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position){
        Todo todo = localDataSet.get(position);
        Log.d("greentea", todo.getText());
        Log.d("greentea", String.valueOf(holder.binding.todoText));
        holder.binding.todoText.setText(todo.getText());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
