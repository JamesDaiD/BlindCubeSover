package com.example.blindfoldedcube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blindfoldedcube.MyCubeDatabase.SolveEntry;

import java.util.List;

public class SolveEntryAdapter extends RecyclerView.Adapter<SolveEntryAdapter.ViewHolder> {

    List<SolveEntry> SolveEntries;
    private RecyclerViewClickListener listener;

    public SolveEntryAdapter(List<SolveEntry> SolveEntries) {
        this.SolveEntries = SolveEntries;
    }

    public SolveEntryAdapter(List<SolveEntry> SolveEntries, RecyclerViewClickListener listener) {
        this.SolveEntries = SolveEntries;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solve_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.scrambleTV.setText(SolveEntries.get(position).getSolveScramble());
        holder.solutionTV.setText(SolveEntries.get(position).getSolveSolution());
    }

    @Override
    public int getItemCount() {
        if (SolveEntries != null) {
            return SolveEntries.size();
        }
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView scrambleTV;
        public TextView solutionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scrambleTV = itemView.findViewById(R.id.scrambleTVRec);
            solutionTV = itemView.findViewById(R.id.solutionTVRec);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}