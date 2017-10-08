package com.example.jevil.gsontest.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jevil.gsontest.R;


class MainListHolder extends RecyclerView.ViewHolder{

    CardView cv;
    TextView tvName, tvTitle, tvText, tvEmail, tvTitlePhoto;
    EditText etId;
    Button btrConfirm;
    ImageView iv;
    ListView lvUsers;
    View separator;
    CheckBox cbComplete;
    ConstraintLayout clButton, clPhoto;



    MainListHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvText = (TextView) itemView.findViewById(R.id.tvText);
        tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        etId = (EditText) itemView.findViewById(R.id.etId);
        btrConfirm = (Button) itemView.findViewById(R.id.btnConfirm);
        iv = (ImageView) itemView.findViewById(R.id.ivMain);
        lvUsers = (ListView) itemView.findViewById(R.id.lvUsers);
        separator = itemView.findViewById(R.id.separator);
        cbComplete = (CheckBox) itemView.findViewById(R.id.cbComplete);
        clButton = (ConstraintLayout) itemView.findViewById(R.id.clButton);
        clPhoto = (ConstraintLayout) itemView.findViewById(R.id.clPhoto);
        tvTitlePhoto = (TextView) itemView.findViewById(R.id.tvImageText);
    }
}