package com.bigbraingang.diaryapp.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbraingang.diaryapp.R;
import com.bigbraingang.diaryapp.ui.validateInputs;
import java.lang.String;



public class EditPopUp {
    private final EnumProfile targetDetails;

    public EditPopUp(EnumProfile targetDetails){
        this.targetDetails = targetDetails;
    }


    public void setClipboard(Context context, String text) {
        // DESCRIPTION: This function will set what ever that is passed in *text to the system clipboard
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }
    public void displayGetHelpPopUp(View parentView, LayoutInflater inflater, Activity activity){
        View popupView = inflater.inflate(R.layout.need_help_popup_template, null);
        ViewGroup inputViewGroup = popupView.findViewById(R.id.needHelpLinearLayout);
        TextView popUpText = popupView.findViewById(R.id.popupHelpTextView);
        View inputField;

        inputField = inflater.inflate(R.layout.need_help, null);
        popUpText.setText(R.string.need_help);


        inputViewGroup.addView(inputField);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        Button copyButton = popupView.findViewById(R.id.copyButton);
        copyButton.setOnClickListener(view -> {
            setClipboard(parentView.getContext(), "800-273-8255");
            Toast.makeText(activity, "Copied To Clipboard", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }

    public void displayPopup(View parentView, LayoutInflater inflater){
        View popupView = inflater.inflate(R.layout.popuptemplate, null);
        ViewGroup inputViewGroup = popupView.findViewById(R.id.editProfileLinearLayout);
        TextView popUpText = popupView.findViewById(R.id.popupTextView);
        View inputField;

        switch (targetDetails){
            case AGE:
                inputField = inflater.inflate(R.layout.edit_age, null);
                popUpText.setText(R.string.edit_age);
                break;
            case NAME:
                inputField = inflater.inflate(R.layout.edit_name, null);
                popUpText.setText(R.string.edit_name);
                break;

            case EMERGENCY:
                inputField = inflater.inflate(R.layout.edit_emergency_contact, null);
                popUpText.setText(R.string.edit_emergency);
                break;

            default:
                inputField = null;
        }
        inputViewGroup.addView(inputField);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        Button cancelButton = popupView.findViewById(R.id.popupCancelButton);
        Button submitButton = popupView.findViewById(R.id.popupSubmitButton);
        cancelButton.setOnClickListener(view -> {
            popupWindow.dismiss();
        });

        submitButton.setOnClickListener(view -> {
            switch (targetDetails){
                case AGE:
                    // Get String from birthdayEdit text box
                    EditText birthdayEdit = popupView.findViewById(R.id.editBirthDateEditText);
                    String newAge = birthdayEdit.getText().toString();
                    // Validate Age is 1 <-> 100
                    if (!validateInputs.isValidBirthdate(newAge)) {
                        validateInputs.feedback(parentView.getContext(), EnumProfile.AGE);
                    }
                    else{
                        // TODO: SAVE IN DATABASE
                        popupWindow.dismiss();
                    }
                    break;
                case EMERGENCY:
                    EditText emergencyContactEdit = popupView.findViewById(R.id.editEmergencyContactEditText);
                    String newContact = emergencyContactEdit.getText().toString();
                    // TODO: SAVE IN DATABASE AND CHECK IF PHONE NUMBER.
                    popupWindow.dismiss();
                    break;
                case NAME:
                    EditText nameEdit = popupView.findViewById(R.id.editNameEditText);
                    String newName = nameEdit.getText().toString();

                    // no validation because anyone's name can be anything.
                    // TODO: SAVE IN DATABASE.
                    popupWindow.dismiss();
                    break;
            }
        });
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }

}
