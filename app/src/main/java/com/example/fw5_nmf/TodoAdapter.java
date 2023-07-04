package com.example.fw5_nmf;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
//    public ArrayList<Todo> localDataSet;
    public ArrayList<String> localDataSet;
    private OnItemClickListener onItemClickListener;
//    private Context context;
    public interface OnItemClickListener {
        void onClickDeleteIcon(int todo);
    }
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView todoText;
        private ImageView deleteImage;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteImage = itemView.findViewById(R.id.delete_image);
            todoText = itemView.findViewById(R.id.todoText);
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        if (v.getId()==R.id.delete_image){
//                        Log.d("greentea", String.valueOf(v.getId()==));
                        if (onItemClickListener != null) {
                            int todo = getAdapterPosition();
                            if (todo != RecyclerView.NO_POSITION) {
                                onItemClickListener.onClickDeleteIcon(todo);
                            }
                        }
//                        }
                }
            });
        }
        public TextView getTextView() {
            return todoText;
        }
    }

    public TodoAdapter(ArrayList<String> dataSet){
//    public TodoAdapter(ArrayList<Todo> dataSet){
        localDataSet = dataSet;
        Log.d("greentea", "THIS IS INIT OF TODOADAPTER");
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public interface OnDeleteIconClickListener{
        void onDeleteIconClick(Todo todo);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, parent, false);
        Log.d("greentea", "create viewholder");
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position){
        Log.d("greentea", "todo.getText()");
        String todo = localDataSet.get(position);
//        Todo todo = localDataSet.get(position);
//        holder.todoText.setText("todo.getText()");
        holder.todoText.setText(todo);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
