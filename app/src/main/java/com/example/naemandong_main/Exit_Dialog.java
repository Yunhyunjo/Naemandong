package com.example.naemandong_main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

public class Exit_Dialog extends Dialog {

    private ImageButton yes, no;
    Intent intent;
    Activity closeActivity;
    Context context;
    Class where;

    public Exit_Dialog(@NonNull Context context, Class where, Activity closeActivity) {
        super(context);
        this.context = context;
        this.where = where;
        this.closeActivity = closeActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_dialog);

        //다이어로그 배경 투명하게
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //셋팅
        yes = (ImageButton)findViewById(R.id.yes);
        no = (ImageButton)findViewById(R.id.no);

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity.finish();
                dismiss();
                intent = new Intent(context, where);
                context.startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}
