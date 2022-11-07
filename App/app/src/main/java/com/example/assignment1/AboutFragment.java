package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class AboutFragment extends Fragment {
    private View view;
    private TextView tvAbout;
    private String strAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.about_fragment, parent, false);
        strAbout = "StudentID: B00786645\nStudentName: Zita Koczka" +
                "\n\nI confirm that I understand what plagiarism is and have read the University's " +
                "policy on plagiarism and understand the definition of plagiarism. The work that I have " +
                "submitted is entirely my own. Any work from other authors is duly referenced and " +
                "acknowledged. I understand that I may face sanctions in accordance with the policies" +
                " and procedures of the University.";
        tvAbout = (TextView) view.findViewById(R.id.textView_FragmentAbout);
        tvAbout.setText(strAbout);
        return view;
    }
}
