package com.bigbraingang.diaryapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bigbraingang.diaryapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private LinearLayout nameLinearLayout;
    private LinearLayout ageLinearLayout;
    private LinearLayout emergencyContactLinearLayout;
    private LinearLayout getHelpLinearLayout;
    private TextView ageValue;
    private TextView emergencyContactValue;
    private TextView nameValue;
    private Button getHelpButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        this.nameLinearLayout = binding.profileNameLinearLayout;
        this.ageLinearLayout = binding.profileAgeLinearLayout;
        this.emergencyContactLinearLayout = binding.emergencyContactLinearLayout;
        this.getHelpLinearLayout = binding.profileGetHelpLinearLayout;
        this.ageValue = binding.profileAgeValue;
        this.nameValue = binding.profileNameValue;
        this.emergencyContactValue = binding.profileEmergencyContactValue;
        this.getHelpButton = binding.profileGetHelpButton;
        View root = binding.getRoot();

        nameLinearLayout.setOnClickListener( v -> {
            EditPopUp popUp = new EditPopUp(EnumProfile.NAME);
            popUp.displayPopup(root, getLayoutInflater());
        });
        ageLinearLayout.setOnClickListener( v -> {
            EditPopUp popUp = new EditPopUp(EnumProfile.AGE);
            popUp.displayPopup(root, getLayoutInflater());
        });
        emergencyContactLinearLayout.setOnClickListener( v -> {
            EditPopUp popUp = new EditPopUp(EnumProfile.EMERGENCY);
            popUp.displayPopup(root, getLayoutInflater());
        });

        getHelpButton.setOnClickListener(v -> {
            EditPopUp popUp = new EditPopUp(EnumProfile.GETHELP);
            popUp.displayGetHelpPopUp(root, getLayoutInflater(), getActivity());
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}