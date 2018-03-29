package com.example.sai_h.labex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ex2b extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ex2b,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ImageView i = (ImageView)v.findViewById(R.id.img);
        float w = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        float h = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        Bitmap b = Bitmap.createBitmap((int)w,(int)h,Bitmap.Config.ARGB_8888);
        final Canvas c = new Canvas(b);
        final Paint p = new Paint();
        p.setColor(Color.GREEN);
        i.setImageBitmap(b);
        i.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                float dx=0,dy=0,ux=0,uy=0;
                int action = m.getAction();
                switch(action){
                    case MotionEvent.ACTION_DOWN: dx = m.getX();
                                                    dy = m.getY();
                                                    break;
                    case MotionEvent.ACTION_UP: ux = m.getX();
                                                uy = m.getY();
                                                c.drawLine(dx,dy,ux,uy,p);
                                                i.invalidate();
                                                break;
                    case MotionEvent.ACTION_MOVE: break;
                    case MotionEvent.ACTION_CANCEL: break;
                    default: break;
                }
                return true;
            }
        });
    }
}
