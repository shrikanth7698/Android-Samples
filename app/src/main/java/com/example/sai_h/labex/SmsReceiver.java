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
//In Main Activity the Service is started using an Intent
/*Eg:
        Intent in = new Intent(this,SmsReceiver.class);
        startService(in);
The second line invokes the method onStart in the service class*/
public class SmsReceiver extends Service {
        BroadcastReceiver b = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            if (bundle != null) {
                //The received messages are obtained
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++)
                {
                    //Messages are displayed one by one in order
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    //Notification is built for each message
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
        //IntentFilter to listen only to SMS receive event is given
        IntentFilter iF = new IntentFilter();
        iF.addAction("android.provider.Telephony.SMS_RECEIVED");
        //BroadCast Receiver is registered for the SMS receive event alone
        registerReceiver(b,iF);
    }
}
