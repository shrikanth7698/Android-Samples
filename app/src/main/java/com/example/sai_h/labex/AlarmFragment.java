package com.example.sai_h.labex;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sai_h.labex.utils.TypefaceSpan;

import java.io.IOException;
import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    static AlarmManager alm;
    static PendingIntent pi;
    static int id = 0;
    View v;
    Button setalarm,cancelalm;
    static Button stopalm;
    DialogFragment df;
    public AlarmFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_alarm, container, false);
        stopalm = v.findViewById(R.id.alarmstopBTN);
        stopalm.setVisibility(View.INVISIBLE);
        SpannableString s = new SpannableString("Set Alarm");
        SpannableString s1 = new SpannableString("Stop Alarm");
        SpannableString s2 = new SpannableString("Cancel Alarm");
        s.setSpan(new TypefaceSpan(getContext(), "product_san_regular.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s1.setSpan(new TypefaceSpan(getContext(),"product_san_regular.ttf"),0,s.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s2.setSpan(new TypefaceSpan(getContext(),"product_san_regular.ttf"),0,s.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setalarm = v.findViewById(R.id.timepickerBTN);
        cancelalm = v.findViewById(R.id.cancelalarmBTN);
        setalarm.setText(s);
        stopalm.setText(s1);
        cancelalm.setText(s2);
        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df = new TimeDialogFrag();
                df.show(getActivity().getSupportFragmentManager(),"Time Picker");
            }
        });
        cancelalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id==1) {
                    alm.cancel(pi);
                    Toast.makeText(getContext(), "Alarm is Cancelled", Toast.LENGTH_SHORT).show();
                    id = 0;
                }
                else{
                    Toast.makeText(getContext(),"No Alarm has been Set",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
    public static class TimeDialogFrag extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this,hour,min,DateFormat.is24HourFormat(getActivity()));

        }

        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(id==0) {
                    alm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                    Intent in = new Intent(getContext(), AlarmReceiver.class);
                    pi = PendingIntent.getBroadcast(getContext(), 0, in, 0);
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    c.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    c.set(Calendar.SECOND, 0);
                    alm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                    id = 1;
                    Toast.makeText(getContext(),"Alarm is Set",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Alarm already Set",Toast.LENGTH_SHORT).show();
                }
        }
    }
    public static class AlarmReceiver extends BroadcastReceiver {
        MediaPlayer mMediaPlayer;
        @Override
        public void onReceive(final Context context, Intent intent) {
            NotificationCompat.Builder nb = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.chat).setContentTitle("Alarm").setContentText("Get Up").setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Notification n = nb.build();
            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            nm.notify(0,n);
            stopalm.setVisibility(View.VISIBLE);
            Uri alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alert == null)
            {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                if (alert == null)
                {
                    alert = RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                }
            }
            mMediaPlayer = new MediaPlayer();
            try
            {
                mMediaPlayer.setDataSource(context,alert);
                final AudioManager audioManager = (AudioManager) context
                        .getSystemService(Context.AUDIO_SERVICE);
                if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0)
                {
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMediaPlayer.stop();
                            id=0;
                            stopalm.setVisibility(View.INVISIBLE);
                        }
                    },10000);
                }
            }
            catch (IOException e)
            {
                System.out.println("OOPS");
            }
            stopalm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMediaPlayer.stop();
                    id=0;
                    Toast.makeText(context, "Alarm is Stopped", Toast.LENGTH_SHORT).show();
                    stopalm.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
