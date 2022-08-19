package com.example.justmoveit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.justmoveit.R;

public class BlankFragment extends Fragment {
    private String message;

    public BlankFragment() {this.message = "상영 중인 영화가 없습니다.";}
    public BlankFragment(String message){
        this.message = message;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blank, container, false);

        if(message == null){
            message = "상영 중인 영화가 없습니다";
        }
        ((TextView) rootView.findViewById(R.id.message)).setText(message);

        return rootView;
    }
}
