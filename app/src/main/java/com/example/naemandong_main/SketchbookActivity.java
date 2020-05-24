package com.example.naemandong_main;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentTransaction;




public class SketchbookActivity extends AppCompatActivity implements View.OnClickListener {

    private Exit_Dialog exitDialog;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketchbook);

        context = this;

        Bundle bundle = new Bundle();
        bundle.putString("what","sketchbook");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fatale_select fatale_select = new Fatale_select();
        fatale_select.setArguments(bundle);
        transaction.replace(R.id.framelayout, fatale_select);
        transaction.commit();  //저장

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_btn:
                exitDialog = new Exit_Dialog(this, Book.class, this);
                exitDialog.show();
                break;
            case R.id.voice_btn:
                exitDialog = new Exit_Dialog(this, Voice.class, this);
                exitDialog.show();
                break;
            case R.id.making_btn:
                exitDialog = new Exit_Dialog(this, Making.class, this);
                exitDialog.show();
                break;
        }
    }
}
