package com.audit_new.scanner.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.audit_new.scanner.R;
import com.audit_new.scanner.home;


public class PRNumberGeneration extends Fragment {

    TextView textView;
    String txt;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prnumber_generation, container, false);
        textView = (TextView) v.findViewById(R.id.prNumberTxt);
        btn = (Button) v.findViewById(R.id.prNumberBtn);
        txt = "PR Number: xxxxxxxxx is generated and sent for Approval.";
        textView.setText(txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), home.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
