package com.example.sai_h.labex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class ex4 extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ex4,container,false);
        RelativeLayout r = (RelativeLayout)v.findViewById(R.id.relgraph);
        r.addView(new Graphics(getContext()));
        v = inflater.inflate(R.layout.fragment_ex4,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public class Graphics extends View{

        public Graphics(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint ob = new Paint();
            ob.setStyle(Paint.Style.FILL);
            // make the entire canvas white
            ob.setColor(Color.WHITE);
            canvas.drawPaint(ob);
            // draw blue circle with anti aliasing turned off
            ob.setAntiAlias(false);
            ob.setColor(Color.GREEN);
            canvas.drawCircle(20, 20, 20, ob);
            // draw green circle with anti aliasing turned on
            ob.setAntiAlias(true);
            ob.setColor(Color.CYAN);
            canvas.drawCircle(60, 20, 30, ob);
            ob.setColor(Color.BLUE);
            RectF oval3 = new RectF(250, 50, 350, 400);
            canvas.drawOval(oval3, ob);
            // draw red rectangle with anti aliasing turned off
            ob.setAntiAlias(false);
            ob.setColor(Color.MAGENTA);
            canvas.drawRect(100, 5, 200, 40, ob);
            ob.setAntiAlias(false);
            ob.setColor(Color.RED);
            canvas.drawLine(250, 50, 350, 400,ob);
            // draw the rotated text
            canvas.rotate(-45);
            ob.setStyle(Paint.Style.FILL);
            canvas.drawText("FDP Training MAD", 50, 180, ob);
            //undo the rotate
            canvas.restore();

        }
    }
}
