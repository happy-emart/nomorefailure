package com.example.fw5_nmf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Contact> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView numberView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameView);
            numberView = itemView.findViewById(R.id.textView);
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getNumberView() {
            return numberView;
        }
    }

    public CustomAdapter(ArrayList<Contact> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = localDataSet.get(position);
        holder.getNameView().setText(contact.getName());
        holder.getNumberView().setText(contact.getNumber());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
