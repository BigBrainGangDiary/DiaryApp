package com.bigbraingang.diaryapp.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bigbraingang.diaryapp.R;
import com.bigbraingang.diaryapp.databinding.FragmentHomeBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    private Button submitButton;
    private TextView entry;
    private TextView descriptionOfRadioButtons;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private FragmentHomeBinding binding;
    DateFormat date = new SimpleDateFormat("MMM dd yyyy, h:mm");

    //    private Toast toast;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        descriptionOfRadioButtons = root.findViewById(R.id.text_home);
        submitButton = root.findViewById(R.id.homeSubmitButton);
        entry = root.findViewById(R.id.entryTextBox);
        radioButton1 = root.findViewById(R.id.radioButton1);
        radioButton2 = root.findViewById(R.id.radioButton2);
        radioButton3 = root.findViewById(R.id.radioButton3);
        radioButton4 = root.findViewById(R.id.radioButton4);
        radioButton5 = root.findViewById(R.id.radioButton5);

        descriptionOfRadioButtons.setText("Select 1-5 on how you are feeling today.");

        submitButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               submitButton.setBackgroundColor(Color.RED);  // test to see if button is responsive.
               int rating = getChecked();                                          // Save ratings
               String textEntry = entry.getText().toString();                      // Save textEntry
               String dateFormat = date.format(Calendar.getInstance().getTime());  // Save Date

               // save to SQL

               // Clear entries
               Toast.makeText(getActivity(), String.valueOf(rating), Toast.LENGTH_LONG).show();

               entry.setText("");
               radioButton1.setChecked(false);
               radioButton2.setChecked(false);
               radioButton3.setChecked(false);
               radioButton4.setChecked(false);
               radioButton5.setChecked(false);
           }
        });
        return root;
    }

    public int getChecked(){
        if (radioButton1.isChecked()){
            return 1;
        }
        if(radioButton2.isChecked()){
            return 2;
        }
        if(radioButton3.isChecked()){
            return 3;
        }
        if(radioButton4.isChecked()){
            return 4;
        }
        if(radioButton5.isChecked()){
            return 5;
        }
        return 3;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}