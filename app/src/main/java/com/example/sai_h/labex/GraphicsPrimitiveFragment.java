package com.example.sai_h.labex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class GraphicsPrimitiveFragment extends Fragment implements View.OnTouchListener,View.OnClickListener{
    View v;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    Canvas canvas;
    Paint paint;
    ImageView imageView;
    Bitmap bitmap;
    int shape;
    Button[] b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_graphics_primitive,container,false);
        b = new Button[5];
        b[0] = v.findViewById(R.id.rectangle);
        b[1] = v.findViewById(R.id.circle);
        b[2] = v.findViewById(R.id.redcolor);
        b[3] = v.findViewById(R.id.bluecolor);
        b[4] = v.findViewById(R.id.greencolor);
        for(int i=0;i<5;i++)
            b[i].setOnClickListener(this);
        imageView = (ImageView)v.findViewById(R.id.imgview);
        Display currentDisplay = getActivity().getWindowManager().getDefaultDisplay();
        float dw = currentDisplay.getWidth();
        float dh = currentDisplay.getHeight();
        bitmap = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener((View.OnTouchListener) this);
        return v;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                upy = event.getY();
                //canvas.drawLine(downx, downy, upx, upy, paint);
                if(shape==1) {
                    canvas.drawRect(downx, downy, upx, upy, paint);
                }
                else if(shape==0) {
                    canvas.drawCircle(downx, downy, 100, paint);}
                imageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch(v.getTag().toString()){
            case "circle":shape = 0;break;
            case "rectangle": shape=1;break;
            case "red": paint.setColor(Color.RED);break;
            case "blue": paint.setColor(Color.BLUE);break;
            case "green": paint.setColor(Color.GREEN);break;
        }
    }
}
