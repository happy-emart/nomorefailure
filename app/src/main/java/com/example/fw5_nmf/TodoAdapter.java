package com.example.fw5_nmf;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    public ArrayList<Todo> localDataSet;
//    private OnDeleteIconClickListener onDeleteIconClickListener;
//    private OnItemClickListener onItemClickListener;
//    private Context context;
//    public interface OnItemClickListener {
//        void onClickDeleteIcon(int todo);
//    }


    public TodoAdapter(ArrayList<Todo> dataSet){
        localDataSet = dataSet;
        System.out.println("THIS IS INIT OF TODOADAPTER");
    }

//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.onItemClickListener = listener;
//    }
//    public interface OnDeleteIconClickListener{
//        void onDeleteIconClick(Todo todo);
//    }
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView todoText;
//        private ImageView deleteImage;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.deleteImage = itemView.findViewById(R.id.delete_image);
//            System.out.println("THIS IS INIT OF VIEWHOLDER");
            this.todoText = itemView.findViewById(R.id.todoText);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onItemClickListener != null) {
//                        int todo = getAdapterPosition();
//                        if (todo != RecyclerView.NO_POSITION) {
//                            onItemClickListener.onClickDeleteIcon(todo);
//                        }
//                    }

//                }
//            });
        }
        public TextView getTextView() {
            return todoText;
        }
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//                    if (onItemClickListener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION) {
//                            onItemClickListener.onItemClick(position);
//                        }
//                    }
//                }
//            });
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        Log.d("greentea", "create viewholder");
        System.out.println("THIS IS CREATE");
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position){
        Log.d("greentea", "todo.getText()");
        Todo todo = localDataSet.get(position);
        holder.todoText.setText("todo.getText()");
//        holder.todoText.setText(todo.getText());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
