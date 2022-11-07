package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class EmptyFragment extends Fragment {
    private View view;
    private TextView tvAbout;
    private String strAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.about_fragment, parent, false);
        strAbout = "";
        tvAbout = (TextView) view.findViewById(R.id.textView_FragmentAbout);
        tvAbout.setText(strAbout);
        return view;
    }
}
