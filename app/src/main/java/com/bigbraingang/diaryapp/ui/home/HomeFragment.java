package com.bigbraingang.diaryapp.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigbraingang.diaryapp.QuoteDialog;
import com.bigbraingang.diaryapp.QuoteManager;
import com.bigbraingang.diaryapp.R;
import com.bigbraingang.diaryapp.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    private Button submitButton;
    private FloatingActionButton fabButton;
    private TextView entry;
    private TextView descriptionOfRadioButtons;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private FragmentHomeBinding binding;
    DateFormat date = new SimpleDateFormat("MMM dd yyyy, h:mm");
    public String quote;

    //    private Toast toast;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        descriptionOfRadioButtons = root.findViewById(R.id.text_home);
        submitButton = root.findViewById(R.id.homeSubmitButton);
        fabButton = root.findViewById(R.id.apiFabButton);
        entry = root.findViewById(R.id.entryTextBox);
        radioButton1 = root.findViewById(R.id.radioButton1);
        radioButton2 = root.findViewById(R.id.radioButton2);
        radioButton3 = root.findViewById(R.id.radioButton3);
        radioButton4 = root.findViewById(R.id.radioButton4);
        radioButton5 = root.findViewById(R.id.radioButton5);

        descriptionOfRadioButtons.setText("Select 1-5 on how you are feeling today.");

        fabButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // DO API STUFF
//                Toast.makeText(getActivity(), "API BUTTON CLICKED", Toast.LENGTH_LONG).show();
                String quoteURL = "https://zenquotes.io/api/random";
                try {
                    sendAndRequestResponse(quoteURL);
                    openDialog();
                } catch (Exception e) {
                    Log.i ("info", e.getMessage());
                }
            }
        });


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

    public void openDialog(){
        QuoteDialog dialog = new QuoteDialog(this.quote);
        dialog.show(getActivity().getSupportFragmentManager(), "Example Dialog");
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

    public void setQuote(String q){
        this.quote = q;
    }
    public void sendAndRequestResponse (String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        /*
        Constructor for StringRequest takes 4 parameters.
         */
        StringRequest requestString = new StringRequest (
                /*
                First parameter specify which method you want to use to make HTTP connection.
                There are two possibilities. We can either use get method or post metho.
                In my example I am using Get method.
                 */
                Request.Method.GET,
                /*
                Second parameter is the URl (in form of a string for the Web server
                 */
                url,
                /*
                Third parameter is Response.Listener object which must override
                the onResponse method
                 */
                new Response.Listener<String>() {
                    public void onResponse (String response) {
                        QuoteManager quote = new QuoteManager (response);
                        setQuote(quote.getQuote()); // do something with this quote now.

                    }
                },
                /*
                Fourt parameter is Response.Error listener object which must override
                omErrorResponse method.
                 */
                new Response.ErrorListener() {
                    public void onErrorResponse (VolleyError error) {
                        Log.i("info", "Error: " + error.toString());
                    }
                }
        );

        requestQueue.add(requestString);

    }
}