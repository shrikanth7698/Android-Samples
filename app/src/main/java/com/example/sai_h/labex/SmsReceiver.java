package com.example.sai_h.labex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends Service {
    BroadcastReceiver b = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++)
                {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    NotificationCompat.Builder nb = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.chat).setContentTitle(msgs[i].getOriginatingAddress().toString()).setContentText(msgs[i].getMessageBody().toString()).setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    Notification n = nb.build();
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(0,n);
                }
            }
        }
    };
    public SmsReceiver() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("Service Started");
        IntentFilter iF = new IntentFilter();
        iF.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(b,iF);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(b);
    }
}
