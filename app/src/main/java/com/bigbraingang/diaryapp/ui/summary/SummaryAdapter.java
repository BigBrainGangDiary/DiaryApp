package com.bigbraingang.diaryapp.ui.summary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbraingang.diaryapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList entryList, ratingList, dateList, timeList;

    SummaryAdapter(Context context, ArrayList entry, ArrayList rating, ArrayList date, ArrayList time){
        this.context = context;
        this.entryList = entry;
        this.ratingList = rating;
        this.dateList = date;
        this.timeList = time;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.summary_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.entry_txt.setText(String.valueOf(entryList.get(getItemCount() - position - 1)));
        holder.rating_txt.setText("Rating: " + String.valueOf(ratingList.get(getItemCount() - position - 1)));
        holder.date_txt.setText(String.valueOf(dateList.get(getItemCount() - position - 1)));
        holder.time_txt.setText(String.valueOf(timeList.get(getItemCount() - position - 1)));


    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView entry_txt, rating_txt, date_txt, time_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            entry_txt = itemView.findViewById(R.id.entry_text);
            rating_txt = itemView.findViewById(R.id.rating_text);
            date_txt = itemView.findViewById(R.id.date_text);
            time_txt = itemView.findViewById(R.id.time_text);
        }
    }
}
