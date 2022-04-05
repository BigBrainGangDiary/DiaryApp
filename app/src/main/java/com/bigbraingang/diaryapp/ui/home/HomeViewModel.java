package com.bigbraingang.diaryapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("From 1-10 how are you feeling today?");
    }


    public void submitButtonPressed(){
        // Description: When the user presses the submit button, the app should save the diary entry
        // and rating to DB/Sqlite and clear/update the page.
    }


    public void displayPreviousEntries(){
        // Description: When this is called. The list of previous entries should be displayed below
        // the today's entry and rating components.
    }


    public LiveData<String> getText() {
        return mText;
    }
}