package com.bigbraingang.diaryapp.ui.summary;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbraingang.diaryapp.DataManager;
import com.bigbraingang.diaryapp.R;
import com.bigbraingang.diaryapp.databinding.FragmentSummaryBinding;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {

    private FragmentSummaryBinding binding;
    private DataManager dm;
    ArrayList<String> entry, rating, date;
    SummaryAdapter summaryAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dm = new DataManager(getActivity());
        entry = new ArrayList<>();
        rating = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = root.findViewById(R.id.summaryRecyclerView);

        storeDataInArrays();
        summaryAdapter = new SummaryAdapter(getActivity(), entry, rating, date);
        recyclerView.setAdapter(summaryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }


    void storeDataInArrays(){
        Cursor cursor = dm.selectAll();
        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                entry.add(cursor.getString(1));
                rating.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}