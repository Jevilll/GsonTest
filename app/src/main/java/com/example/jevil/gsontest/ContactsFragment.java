package com.example.jevil.gsontest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ContactsFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        Button btnPhone = (Button) v.findViewById(R.id.btnPhone);
        Button btnEmail = (Button) v.findViewById(R.id.btnEmail);
        Button btnSummary = (Button) v.findViewById(R.id.btnSummary);
        btnPhone.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnSummary.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPhone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+79994220042"));
                startActivity(intent);
                break;
            case R.id.btnEmail:
                Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:a.liholetoff@yandex.ru"));
                email.setType("message/rfc822");
                startActivity(email);
                break;
            case R.id.btnSummary:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hh.ru/resume/7d842402ff038f533e0039ed1f6b4776325970"));
                startActivity(intent);
                break;
        }
    }
}
