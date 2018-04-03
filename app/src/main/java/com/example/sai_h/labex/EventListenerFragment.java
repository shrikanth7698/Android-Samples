package com.example.sai_h.labex;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class EventListenerFragment extends Fragment implements View.OnTouchListener{
    View v;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    Canvas canvas;
    Paint paint;
    ImageView imageView;
    Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_event_listener,container,false);
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        imageView = (ImageView) v.findViewById(R.id.img);
        Display currentDisplay = getActivity().getWindowManager().getDefaultDisplay();
        float dw = currentDisplay.getWidth();
        float dh = currentDisplay.getHeight();
        bitmap = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener((View.OnTouchListener) this);
        return v; //Returns the Viewgroup to the activity class for inflation
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
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
                canvas.drawLine(downx, downy, upx, upy, paint);
                imageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;

    }
}
