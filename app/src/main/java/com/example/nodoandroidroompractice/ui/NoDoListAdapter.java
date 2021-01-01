package com.example.nodoandroidroompractice.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodoandroidroompractice.R;
import com.example.nodoandroidroompractice.model.NoDo;

import java.util.List;

public class NoDoListAdapter extends RecyclerView.Adapter<NoDoListAdapter.NoDoViewHolder> {
    private final LayoutInflater noDoInflater;
    private List<NoDo> noDoList;   // Save and add all information for our noDo items.

    public NoDoListAdapter(Context context) {
        noDoInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = noDoInflater.inflate(R.layout.recycler_view_item , parent , false);
        return new NoDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoDoViewHolder noDoViewholder, int position) {
        if(noDoList!=null){
            NoDo current = noDoList.get(position);
            noDoViewholder.noDoTextView.setText(current.getNoDo());
        }else{
            noDoViewholder.noDoTextView.setText(R.string.no_no_toDo);
        }
    }

    public void setNoDo(List<NoDo> noDos){
        noDoList = noDos;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(noDoList!=null)  return noDoList.size();
        else return 0;
    }

    public class NoDoViewHolder extends RecyclerView.ViewHolder {
        public TextView noDoTextView;
        public NoDoViewHolder(@NonNull View itemView) {
            super(itemView);
            noDoTextView = itemView.findViewById(R.id.textView);
        }
    }
}
