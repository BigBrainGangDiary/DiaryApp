package com.bigbraingang.diaryapp.ui;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.bigbraingang.diaryapp.ui.profile.EnumProfile;

public class validateInputs {

    private static final String TAG = "InputValidator";

    public static void feedback(Context context, EnumProfile detail) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Error: Invalid Entry");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {} );

        switch (detail) {
            case AGE:
                alertDialog.setMessage("Invalid entry for Birthdate.");
                break;
            case NAME:
                alertDialog.setMessage("Invalid entry for Name.");
                break;
            case EMERGENCY:
                alertDialog.setMessage("Invalid entry for Emergency Contact.");
                break;
        }

        alertDialog.show();
    }

    public static boolean isValidBirthdate(String age) {
        int myAge = 0;
        Log.d(TAG, "AgeisValidBirthdate: "+ age);
        if(age!=null){
            myAge = Integer.parseInt(age);
        }
        if(myAge<=0||myAge>100){
            return false;
        }else{
            return true;
        }
    }

}
