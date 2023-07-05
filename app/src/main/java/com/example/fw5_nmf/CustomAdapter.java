package com.example.fw5_nmf;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nametextView;
        private TextView numbertextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nametextView = itemView.findViewById(R.id.nametextView);
            numbertextView = itemView.findViewById(R.id.numberTextView);
        }
        public TextView getNameTextView() {
            return nametextView;
        }

        public TextView getNumbertextView() {return numbertextView; }
        @Override
        public void onClick(View view) {

        }
    }
    public CustomAdapter(ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] data = localDataSet.get(holder.getAdapterPosition()).split("\n");
        String name = data[0];
        String number = data[1];
        holder.getNameTextView().setText(name);
        holder.getNumbertextView().setText(number);
    }
    @Override
    public int getItemCount() {
        Log.d("getItemCountFromCA", String.valueOf(localDataSet.size()));
        return localDataSet.size();
    }
    public void clearSwipeBackground() {
        Fragment1.leftBackground = null;
        Fragment1.rightBackground = null;
        Fragment1.initiated = false;
        notifyDataSetChanged();
    }
}
