package com.example.naemandong_main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Bitmap bitmap, bitmap2;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private float mX, mY;
    private Canvas mCanvas;
    int color;
    private final int DEFFERENCE_SPACE = 4;

    public MyView(Context context){
        super((context));
        init();
    }

    public void init() {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        bitmap2 = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        //mergeBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.RGB_565);
        mCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    public void setToeraser(){
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setTopen(){
        mPaint.setXfermode(null);
        mPaint.setShader(null);
        mPaint.setMaskFilter(null);
        mPaint.setStrokeWidth(50);
        mPaint.setColor(color);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                touchStart(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }

        return true;
    }

    private void touchUp(){
        mPath.reset();
    }

    private void touchMove(float x, float y){
        float dx = Math.abs(x-mX);
        float dy = Math.abs(y-mY);

        if (dx >= DEFFERENCE_SPACE || dy >= DEFFERENCE_SPACE){
            mPath.quadTo(x,y,(x+mX)/2, (y+mY)/2);
            mX = x;
            mY = y;

            mCanvas.drawPath(mPath, mPaint);
            invalidate();
        }

    }

    private void touchStart(float x, float y){
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
    }

    public Bitmap getBitmap() {
        bitmap2.eraseColor(Color.WHITE);
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawBitmap(bitmap2, 0, 0, null);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return bitmap2;
    }
}
