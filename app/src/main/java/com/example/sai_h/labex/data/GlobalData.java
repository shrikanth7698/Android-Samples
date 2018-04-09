package com.example.sai_h.labex.data;

public class GlobalData {

    public static String alarmCode = "\n# Alarm Code#\n\n### Java Code\n#### AlarmFragment.java\n```java\npackage com.example.sai_h.labex;\n" +
            "import android.app.AlarmManager;\n" +
            "import android.app.Dialog;\n" +
            "import android.app.Notification;\n" +
            "import android.app.NotificationManager;\n" +
            "import android.app.PendingIntent;\n" +
            "import android.app.TimePickerDialog;\n" +
            "import android.content.BroadcastReceiver;\n" +
            "import android.content.Context;\n" +
            "import android.content.Intent;\n" +
            "import android.content.IntentFilter;\n" +
            "import android.media.AudioManager;\n" +
            "import android.media.MediaPlayer;\n" +
            "import android.media.RingtoneManager;\n" +
            "import android.net.Uri;\n" +
            "import android.os.Bundle;\n" +
            "import android.os.Handler;\n" +
            "import android.support.v4.app.DialogFragment;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.support.v4.app.NotificationCompat;\n" +
            "import android.text.Spannable;\n" +
            "import android.text.SpannableString;\n" +
            "import android.text.format.DateFormat;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.TimePicker;\n" +
            "import android.widget.Toast;\n" +
            "\n" +
            "import com.example.sai_h.labex.utils.TypefaceSpan;\n" +
            "\n" +
            "import java.io.IOException;\n" +
            "import java.util.Calendar;\n" +
            "\n" +
            "import static android.content.Context.NOTIFICATION_SERVICE;\n" +
            "public class AlarmFragment extends Fragment {\n" +
            "    static AlarmManager alm;\n" +
            "    static PendingIntent pi;\n" +
            "    static int id = 0;\n" +
            "    View v;\n" +
            "    Button setalarm,cancelalm;\n" +
            "    static Button stopalm;\n" +
            "    DialogFragment df;\n" +
            "    public AlarmFragment() {\n" +
            "        // Required empty public constructor\n" +
            "    }\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, ViewGroup container,\n" +
            "                             Bundle savedInstanceState) {\n" +
            "        //Inflate the layout for this fragment\n" +
            "        v = inflater.inflate(R.layout.fragment_alarm, container, false);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        stopalm = v.findViewById(R.id.alarmstopBTN);\n" +
            "        stopalm.setVisibility(View.INVISIBLE);\n" +
            "        //Custom font is set for the text in the Buttons\n" +
            "        SpannableString s = new SpannableString(\"Set Alarm\");\n" +
            "        SpannableString s1 = new SpannableString(\"Stop Alarm\");\n" +
            "        SpannableString s2 = new SpannableString(\"Cancel Alarm\");\n" +
            "        s.setSpan(new TypefaceSpan(getContext(), \"product_san_regular.ttf\"), 0, s.length(),\n" +
            "                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);\n" +
            "        s1.setSpan(new TypefaceSpan(getContext(),\"product_san_regular.ttf\"),0,s.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);\n" +
            "        s2.setSpan(new TypefaceSpan(getContext(),\"product_san_regular.ttf\"),0,s.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);\n" +
            "        setalarm = v.findViewById(R.id.timepickerBTN);\n" +
            "        cancelalm = v.findViewById(R.id.cancelalarmBTN);\n" +
            "        setalarm.setText(s);\n" +
            "        stopalm.setText(s1);\n" +
            "        cancelalm.setText(s2);\n" +
            "        //Listener to open the Dialog for Time Picker\n" +
            "        setalarm.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                df = new TimeDialogFrag();\n" +
            "                //Invoking the onCreateDialog method of TimeDialogFragment\n" +
            "                df.show(getActivity().getSupportFragmentManager(),\"Time Picker\");\n" +
            "            }\n" +
            "        });\n" +
            "        //Listener to cancel the alarm\n" +
            "        cancelalm.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                if(id==1) {\n" +
            "                    alm.cancel(pi);//Cancels the alarm set. Cancelled using the same intent with which the alarm was set.\n" +
            "                    Toast.makeText(getContext(), \"Alarm is Cancelled\", Toast.LENGTH_SHORT).show();\n" +
            "                    id = 0;\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"No Alarm has been Set\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "            }\n" +
            "        });\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "    public static class TimeDialogFrag extends DialogFragment implements TimePickerDialog.OnTimeSetListener{\n" +
            "        @Override\n" +
            "        public Dialog onCreateDialog(Bundle savedInstanceState) {\n" +
            "            Calendar c = Calendar.getInstance();\n" +
            "            int hour = c.get(Calendar.HOUR_OF_DAY);\n" +
            "            int min = c.get(Calendar.MINUTE);\n" +
            "            //Returning the TimePickerDialog\n" +
            "            return new TimePickerDialog(getActivity(),this,hour,min,DateFormat.is24HourFormat(getActivity()));\n" +
            "\n" +
            "        }\n" +
            "        //onTimeSet listener to set the alarm after the time has been set using the TimePickerDialog\n" +
            "        @Override\n" +
            "        public void onTimeSet(TimePicker timePicker, int i, int i1) {\n" +
            "                if(id==0) {\n" +
            "                    //Get alarm service and stored in AlarmManager\n" +
            "                    alm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);\n" +
            "                    //Intent to invoke AlarmReceiver class\n" +
            "                    Intent in = new Intent(getContext(), AlarmReceiver.class);\n" +
            "                    //PendingIntent to set the broadcast to invoke the alarm\n" +
            "                    pi = PendingIntent.getBroadcast(getContext(), 0, in, 0);\n" +
            "                    Calendar c = Calendar.getInstance();\n" +
            "                    c.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());\n" +
            "                    c.set(Calendar.MINUTE, timePicker.getCurrentMinute());\n" +
            "                    c.set(Calendar.SECOND, 0);\n" +
            "                    /*Alarm set using the PendingIntent */\n" +
            "                    alm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);\n" +
            "                    id = 1;\n" +
            "                    Toast.makeText(getContext(),\"Alarm is Set\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"Alarm already Set\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "        }\n" +
            "    }\n" +
            "    public static class AlarmReceiver extends BroadcastReceiver {\n" +
            "        MediaPlayer mMediaPlayer;\n" +
            "        @Override\n" +
            "        public void onReceive(final Context context, Intent intent) {\n" +
            "            //Notification is built when the alarm is triggered (onReceive is triggered)\n" +
            "            NotificationCompat.Builder nb = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.chat).setContentTitle(\"Alarm\").setContentText(\"Get Up\").setPriority(NotificationCompat.PRIORITY_DEFAULT);\n" +
            "            Notification n = nb.build();\n" +
            "            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);\n" +
            "            nm.notify(0,n);\n" +
            "            //Button to stop alarm is now set visible\n" +
            "            stopalm.setVisibility(View.VISIBLE);\n" +
            "            //Ringtone for alarm is obtained\n" +
            "            Uri alert = RingtoneManager\n" +
            "                    .getDefaultUri(RingtoneManager.TYPE_ALARM);\n" +
            "            if (alert == null)\n" +
            "            {\n" +
            "                alert = RingtoneManager\n" +
            "                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);\n" +
            "                if (alert == null)\n" +
            "                {\n" +
            "                    alert = RingtoneManager\n" +
            "                            .getDefaultUri(RingtoneManager.TYPE_RINGTONE);\n" +
            "                }\n" +
            "            }\n" +
            "            //MediaPlayer is initialized to play the ringtone for alarm\n" +
            "            mMediaPlayer = new MediaPlayer();\n" +
            "            try\n" +
            "            {\n" +
            "                mMediaPlayer.setDataSource(context,alert);\n" +
            "                final AudioManager audioManager = (AudioManager) context\n" +
            "                        .getSystemService(Context.AUDIO_SERVICE);\n" +
            "                if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0)\n" +
            "                {\n" +
            "                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);\n" +
            "                    mMediaPlayer.prepare();\n" +
            "                    mMediaPlayer.start();\n" +
            "                    //Handler to stop the ringing after a Minute\n" +
            "                    new Handler().postDelayed(new Runnable() {\n" +
            "                        @Override\n" +
            "                        public void run() {\n" +
            "                            mMediaPlayer.stop();\n" +
            "                            id=0;\n" +
            "                            stopalm.setVisibility(View.INVISIBLE);\n" +
            "                        }\n" +
            "                    },10000*6);\n" +
            "                }\n" +
            "            }\n" +
            "            catch (IOException e)\n" +
            "            {\n" +
            "                System.out.println(\"OOPS\");\n" +
            "            }\n" +
            "            //Onclick Listener to stop the alarm immediately\n" +
            "            stopalm.setOnClickListener(new View.OnClickListener() {\n" +
            "                @Override\n" +
            "                public void onClick(View view) {\n" +
            "                    mMediaPlayer.stop();\n" +
            "                    id=0;\n" +
            "                    Toast.makeText(context, \"Alarm is Stopped\", Toast.LENGTH_SHORT).show();\n" +
            "                    stopalm.setVisibility(View.INVISIBLE);\n" +
            "                }\n" +
            "            });\n" +
            "        }\n" +
            "    }\n" +
            "}\n```" +
            "\n\n### XML Code\n#### fragment_alarm.xml\n" +
            "```xml" +
            "\n<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\".AlarmFragment\">\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/timepickerBTN\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:textSize=\"30sp\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/alarmstopBTN\"\n" +
            "        android:layout_below=\"@id/timepickerBTN\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:textSize=\"30sp\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/cancelalarmBTN\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_below=\"@id/alarmstopBTN\"\n" +
            "        android:background=\"@null\"/>\n" +
            "</RelativeLayout>\n" +
            "```";

    public static String BasicUI="\n# Basic UI Code\n\n### Java Code\n#### BasicUIFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.support.v4.app.FragmentTransaction;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.EditText;\n" +
            "public class BasicUIFragment extends Fragment {\n" +
            "    View v;\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, ViewGroup container,\n" +
            "                             Bundle savedInstanceState) {\n" +
            "         /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_basic_ui, container, false);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        v.findViewById(R.id.bex1).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                EditText t = (EditText) v.findViewById(R.id.val1);\n" +
            "                t = (EditText) v.findViewById(R.id.val2);\n" +
            "                //Initiating a fragment transaction.\n" +
            "                FragmentTransaction f = getFragmentManager().beginTransaction();\n" +
            "                // Current fragment is replaced by another fragment\n" +
            "                f.replace(R.id.frameLayout,new BasicUIFragment2());\n" +
            "                f.commit();\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n```\n#### BasicUIFragment2.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.CheckBox;\n" +
            "import android.widget.RadioGroup;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "public class BasicUIFragment2 extends Fragment {\n" +
            "    View v;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_ex12,container,false);\n" +
            "        return v;//Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        v.findViewById(R.id.bex12).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                //Bundle to pass the values from current fragment to next fragment. In activity, values are passed using Intent.\n" +
            "                Bundle b = new Bundle();\n" +
            "                TextView t1 = (TextView) v.findViewById(R.id.val1);\n" +
            "                b.putString(\"Name\",t1.getText().toString());\n" +
            "                RadioGroup rg = (RadioGroup) v.findViewById(R.id.radio);\n" +
            "                switch(rg.getCheckedRadioButtonId()){\n" +
            "                    case R.id.m: b.putString(\"Gender\",\"Male\");\n" +
            "                                 break;\n" +
            "                    case R.id.fm: b.putString(\"Gender\",\"Female\");\n" +
            "                                  break;\n" +
            "                }\n" +
            "                CheckBox c1 = (CheckBox)v.findViewById(R.id.ch1);\n" +
            "                CheckBox c2 = (CheckBox)v.findViewById(R.id.ch2);\n" +
            "                CheckBox c3 = (CheckBox)v.findViewById(R.id.ch3);\n" +
            "                if(c1.isChecked())\n" +
            "                    b.putString(\"H1\",c1.getText().toString());\n" +
            "                if(c2.isChecked())\n" +
            "                    b.putString(\"H2\",c2.getText().toString());\n" +
            "                if(c3.isChecked())\n" +
            "                    b.putString(\"H3\",c3.getText().toString());\n" +
            "                BasicUIFragment3 ui3 = new BasicUIFragment3();\n" +
            "                ui3.setArguments(b); //Bundle containing the arguments is set\n" +
            "                getFragmentManager().beginTransaction().replace(R.id.frameLayout,ui3).commit();\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n```\n#### BasicUIFragment3.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.TextView;\n" +
            "public class BasicUIFragment3 extends Fragment {\n" +
            "    View v;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_ex13,container,false);\n" +
            "        return v;//Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //Arguments stored in the bundle are obtained using getArguments.getString(<String key>)\n" +
            "        String val = \"Name:\"+getArguments().getString(\"Name\");\n" +
            "        val = val+\"\\n\\n\"+\"Gender:\"+getArguments().getString(\"Gender\");\n" +
            "        val = val +\"\\n\\n\"+\"Hobbies:\";\n" +
            "        if(getArguments().getString(\"H1\")!=null){\n" +
            "            val = val+\"\\n\"+getArguments().getString(\"H1\");\n" +
            "        }\n" +
            "        if(getArguments().getString(\"H2\")!=null){\n" +
            "            val = val+\"\\n\"+getArguments().getString(\"H2\");\n" +
            "        }\n" +
            "        if(getArguments().getString(\"H3\")!=null){\n" +
            "            val = val+\"\\n\"+getArguments().getString(\"H3\");\n" +
            "        }\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        TextView t = (TextView) v.findViewById(R.id.finalex1);\n" +
            "        t.setText(val);\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### XML Code\n#### fragment_basic_ui.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.BasicUIFragment\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/uname\"\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_marginTop=\"50dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:text=\"Username\"\n" +
            "        android:textSize=\"30sp\" />\n" +
            "\n" +
            "    <EditText\n" +
            "        android:layout_width=\"300dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_below=\"@+id/uname\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:id=\"@+id/val1\"\n" +
            "        android:hint=\"Email or Name\"/>\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_below=\"@+id/val1\"\n" +
            "        android:layout_marginTop=\"21dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:text=\"Password\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:id=\"@+id/pass\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"300dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:inputType=\"textPassword\"\n" +
            "        android:layout_below=\"@+id/pass\"\n" +
            "        android:id=\"@+id/val2\"\n" +
            "        android:layout_marginLeft=\"20dp\" />\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Login\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"25sp\"\n" +
            "        android:layout_below=\"@+id/val2\"\n" +
            "        android:id=\"@+id/bex1\"\n" +
            "        android:layout_centerHorizontal=\"true\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```\n#### fragment_ex12.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.BasicUIFragment2\">\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Name\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:id=\"@+id/name\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"200dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:layout_toRightOf=\"@+id/name\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/val1\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_below=\"@id/val1\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:id=\"@+id/gender\"\n" +
            "        android:text=\"Gender\"/>\n" +
            "    <RadioGroup\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_toRightOf=\"@+id/gender\"\n" +
            "        android:layout_below=\"@id/val1\"\n" +
            "        android:layout_marginTop=\"15dp\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/radio\">\n" +
            "        <RadioButton\n" +
            "            android:layout_width=\"wrap_content\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:text=\"Male\"\n" +
            "            android:id=\"@+id/m\"\n" +
            "            android:fontFamily=\"@font/alegreya_sans\"\n" +
            "            android:textSize=\"25sp\"/>\n" +
            "        <RadioButton\n" +
            "            android:layout_width=\"wrap_content\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:text=\"Female\"\n" +
            "            android:id=\"@+id/fm\"\n" +
            "            android:fontFamily=\"@font/alegreya_sans\"\n" +
            "            android:textSize=\"25sp\"/>\n" +
            "    </RadioGroup>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Hobbies\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_below=\"@+id/radio\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:id=\"@+id/hobbies\"/>\n" +
            "    <CheckBox\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_toRightOf=\"@+id/hobbies\"\n" +
            "        android:layout_below=\"@+id/radio\"\n" +
            "        android:layout_marginTop=\"26dp\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/ch1\"\n" +
            "        android:text=\"Chess\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"25sp\"/>\n" +
            "    <CheckBox\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"25sp\"\n" +
            "        android:text=\"Cricket\"\n" +
            "        android:layout_toRightOf=\"@+id/ch1\"\n" +
            "        android:layout_below=\"@+id/radio\"\n" +
            "        android:layout_marginTop=\"26dp\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/ch2\"/>\n" +
            "    <CheckBox\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"25sp\"\n" +
            "        android:text=\"Football\"\n" +
            "        android:layout_toRightOf=\"@+id/hobbies\"\n" +
            "        android:layout_below=\"@+id/ch1\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/ch3\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@+id/ch3\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:text=\"Submit\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"25sp\"\n" +
            "        android:id=\"@+id/bex12\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```\n#### fragment_ex13.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.BasicUIFragment3\">\n" +
            "\n" +
            "    <!-- TODO: Update blank fragment layout -->\n" +
            "    <TextView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"match_parent\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:id=\"@+id/finalex1\"/>\n" +
            "\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String eventListener="\n# Event Listerner Code\n\n### Java Code\n#### EventListenerFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.graphics.Bitmap;\n" +
            "import android.graphics.Canvas;\n" +
            "import android.graphics.Color;\n" +
            "import android.graphics.Paint;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.Display;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.MotionEvent;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.ImageView;\n" +
            "\n" +
            "public class EventListenerFragment extends Fragment implements View.OnTouchListener{\n" +
            "    View v;\n" +
            "    float downx = 0, downy = 0, upx = 0, upy = 0;\n" +
            "    Canvas canvas;\n" +
            "    Paint paint;\n" +
            "    ImageView imageView;\n" +
            "    Bitmap bitmap;\n" +
            "\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_event_listener,container,false);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        imageView = (ImageView) v.findViewById(R.id.img);\n" +
            "        Display currentDisplay = getActivity().getWindowManager().getDefaultDisplay();\n" +
            "        float dw = currentDisplay.getWidth();\n" +
            "        float dh = currentDisplay.getHeight();\n" +
            "        bitmap = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);\n" +
            "        canvas = new Canvas(bitmap);\n" +
            "        paint = new Paint();\n" +
            "        paint.setColor(Color.RED);\n" +
            "        imageView.setImageBitmap(bitmap);\n" +
            "        imageView.setOnTouchListener((View.OnTouchListener) this);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onTouch(View view, MotionEvent event) {\n" +
            "        int action = event.getAction();\n" +
            "        switch (action) {\n" +
            "            case MotionEvent.ACTION_DOWN:\n" +
            "                downx = event.getX();\n" +
            "                downy = event.getY();\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_MOVE:\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_UP:\n" +
            "                upx = event.getX();\n" +
            "                upy = event.getY();\n" +
            "                canvas.drawLine(downx, downy, upx, upy, paint);\n" +
            "                imageView.invalidate();\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_CANCEL:\n" +
            "                break;\n" +
            "            default:\n" +
            "                break;\n" +
            "        }\n" +
            "        return true;\n" +
            "\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### fragment_event_listener.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.EventListenerFragment\">\n" +
            "\n" +
            "    <ImageView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"match_parent\"\n" +
            "        android:id=\"@+id/img\"/>\n" +
            "\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String fontColor="\n# Font Color Code\n\n### Java Code\n##### FontColorFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.graphics.Color;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "public class FontColorFragment extends Fragment {\n" +
            "    View v;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_font_color,container,false);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        final TextView t = (TextView)v.findViewById(R.id.txt);\n" +
            "        v.findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                t.setBackgroundColor(Color.RED);\n" +
            "            }\n" +
            "        });\n" +
            "        v.findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                t.setBackgroundColor(Color.GREEN);\n" +
            "            }\n" +
            "        });\n" +
            "        v.findViewById(R.id.yellow).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                t.setBackgroundColor(Color.YELLOW);\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_font_color.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.FontColorFragment\">\n" +
            "    <Button\n" +
            "        android:id=\"@+id/red\"\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_marginTop=\"30dp\"\n" +
            "        android:text=\"Red\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"/>\n" +
            "    <Button\n" +
            "        android:id=\"@+id/green\"\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:text=\"green\"\n" +
            "        android:layout_below=\"@+id/red\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"/>\n" +
            "    <Button\n" +
            "        android:id=\"@+id/yellow\"\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:text=\"Yellow\"\n" +
            "        android:layout_below=\"@+id/green\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Text to be Displayed\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_below=\"@+id/yellow\"\n" +
            "        android:layout_margin=\"20dp\"\n" +
            "        android:id=\"@+id/txt\"\n" +
            "        android:textColor=\"@color/colorPrimaryDark\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String gps = "\n# GPS Location Code\n\n### Java Code\n#### GPSLocationFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.Manifest;\n" +
            "import android.app.Activity;\n" +
            "import android.content.Context;\n" +
            "import android.content.pm.PackageManager;\n" +
            "import android.location.Location;\n" +
            "import android.net.Uri;\n" +
            "import android.os.AsyncTask;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.NonNull;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.ActivityCompat;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.util.Log;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "import com.google.android.gms.common.ConnectionResult;\n" +
            "import com.google.android.gms.common.api.GoogleApiClient;\n" +
            "import com.google.android.gms.location.LocationRequest;\n" +
            "import com.google.android.gms.location.LocationServices;\n" +
            "\n" +
            "import org.w3c.dom.Text;\n" +
            "\n" +
            "import java.util.Timer;\n" +
            "\n" +
            "/*To use GoogleApiClient, add these lines in build.gradle (Module:app)\n" +
            "implementation 'com.google.android.gms:play-services-maps:11.8.0'\n" +
            "implementation 'com.google.android.gms:play-services-location:11.8.0'\n" +
            "implementation 'com.google.android.gms:play-services-places:11.8.0'*/\n" +
            "\n" +
            "public class GPSLocationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener{\n" +
            "    View v;\n" +
            "    TextView lt,ln;\n" +
            "    static double lat, lon;\n" +
            "    Location loc;\n" +
            "    GoogleApiClient mGoogleApiClient;\n" +
            "    LocationRequest mRequest;\n" +
            "    Context c;\n" +
            "    Activity a;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_gps_tracking,container,false);\n" +
            "        c = getContext();\n" +
            "        a = getActivity();\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        lt = (TextView)v.findViewById(R.id.gpslat);\n" +
            "        ln = (TextView)v.findViewById(R.id.gpslon);\n" +
            "        this.buildGoogleapiclient();\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    synchronized void buildGoogleapiclient() {\n" +
            "        /*Building a GoogleApiClient for LocationServices with\n" +
            "        ConnectionCallbacks, OnConnectionFailed Listener , onLocationChanged Listener*/\n" +
            "        mGoogleApiClient = new GoogleApiClient.Builder(c)\n" +
            "                .addConnectionCallbacks(this)\n" +
            "                .addOnConnectionFailedListener(this)\n" +
            "                .addApi(LocationServices.API)\n" +
            "                .build();\n" +
            "        mGoogleApiClient.connect();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onLocationChanged(Location location) {\n" +
            "        try {\n" +
            "            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)\n" +
            "                ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);\n" +
            "            //Requesting location updates\n" +
            "            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mRequest, this);\n" +
            "            //Getting last updated location\n" +
            "            loc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);\n" +
            "        } catch (Exception e) {\n" +
            "            Log.i(\"Error\", e.toString());\n" +
            "        }\n" +
            "        if (loc != null) {\n" +
            "            lat = loc.getLatitude();\n" +
            "            lon = loc.getLongitude();\n" +
            "        }\n" +
            "        update();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onConnected(@Nullable Bundle bundle) {\n" +
            "        mRequest = LocationRequest.create();\n" +
            "        mRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);\n" +
            "        mRequest.setMaxWaitTime(5000);\n" +
            "        mRequest.setInterval(10000);\n" +
            "        try {\n" +
            "            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)\n" +
            "                ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);\n" +
            "            //Requesting location updates\n" +
            "            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mRequest, (com.google.android.gms.location.LocationListener) this);\n" +
            "            //Getting last updated location\n" +
            "            loc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);\n" +
            "        } catch (Exception e) {\n" +
            "            Log.i(\"Error\", e.toString());\n" +
            "        }\n" +
            "        if (loc != null) {\n" +
            "            lat = loc.getLatitude();\n" +
            "            lon = loc.getLongitude();\n" +
            "        }\n" +
            "        update();\n" +
            "    }\n" +
            "    @Override\n" +
            "    public void onConnectionSuspended(int i) {\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {\n" +
            "        buildGoogleapiclient();\n" +
            "    }\n" +
            "    public void update(){\n" +
            "        lt.setText(String.valueOf(lat));\n" +
            "        ln.setText(String.valueOf(lon));\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_gps_tracking.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.GPSLocationFragment\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:text=\"Lattitude\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:id=\"@+id/gpslatlbl\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/gpslatlbl\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:id=\"@+id/gpslat\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:text=\"Longitude\"\n" +
            "        android:layout_below=\"@id/gpslat\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:id=\"@+id/gpslonlbl\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/gpslonlbl\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:id=\"@+id/gpslon\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String graphics="\n# Graphics Primitives Code\n\n### Java Code\n#### GraphicsPrimitivesFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "import android.graphics.Bitmap;\n" +
            "import android.graphics.Canvas;\n" +
            "import android.graphics.Color;\n" +
            "import android.graphics.Paint;\n" +
            "import android.graphics.RectF;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.Display;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.MotionEvent;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.ImageView;\n" +
            "import android.widget.RelativeLayout;\n" +
            "\n" +
            "\n" +
            "public class GraphicsPrimitiveFragment extends Fragment implements View.OnTouchListener,View.OnClickListener{\n" +
            "    View v;\n" +
            "    float downx = 0, downy = 0, upx = 0, upy = 0;\n" +
            "    Canvas canvas;\n" +
            "    Paint paint;\n" +
            "    ImageView imageView;\n" +
            "    Bitmap bitmap;\n" +
            "    int shape;\n" +
            "    Button[] b;\n" +
            "\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_graphics_primitive,container,false);\n" +
            "        b = new Button[5];\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        b[0] = v.findViewById(R.id.rectangle);\n" +
            "        b[1] = v.findViewById(R.id.circle);\n" +
            "        b[2] = v.findViewById(R.id.redcolor);\n" +
            "        b[3] = v.findViewById(R.id.bluecolor);\n" +
            "        b[4] = v.findViewById(R.id.greencolor);\n" +
            "        for(int i=0;i<5;i++)\n" +
            "            b[i].setOnClickListener(this);\n" +
            "        //Image View is identified and bitmap is set\n" +
            "        imageView = (ImageView)v.findViewById(R.id.imgview);\n" +
            "        Display currentDisplay = getActivity().getWindowManager().getDefaultDisplay();\n" +
            "        float dw = currentDisplay.getWidth();\n" +
            "        float dh = currentDisplay.getHeight();\n" +
            "        bitmap = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);\n" +
            "        canvas = new Canvas(bitmap);\n" +
            "        paint = new Paint();\n" +
            "        paint.setColor(Color.RED);\n" +
            "        imageView.setImageBitmap(bitmap);\n" +
            "        imageView.setOnTouchListener((View.OnTouchListener) this);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "    @Override\n" +
            "    public boolean onTouch(View v, MotionEvent event) {\n" +
            "        int action = event.getAction();\n" +
            "        switch (action) {\n" +
            "            //Co-ordinates of the touch action are obtained first\n" +
            "            case MotionEvent.ACTION_DOWN:\n" +
            "                downx = event.getX();\n" +
            "                downy = event.getY();\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_MOVE:\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_UP:\n" +
            "                upx = event.getX();\n" +
            "                upy = event.getY();\n" +
            "                //Required shape is drawn based on the co-ordinates obtained\n" +
            "                if(shape==1) {\n" +
            "                    canvas.drawRect(downx, downy, upx, upy, paint);\n" +
            "                }\n" +
            "                else if(shape==0) {\n" +
            "                    canvas.drawCircle(downx, downy, 100, paint);}\n" +
            "                imageView.invalidate();\n" +
            "                break;\n" +
            "            case MotionEvent.ACTION_CANCEL:\n" +
            "                break;\n" +
            "            default:\n" +
            "                break;\n" +
            "        }\n" +
            "        return true;\n" +
            "    }\n" +
            "    @Override\n" +
            "    public void onClick(View v) {\n" +
            "        //Listening to button clicks to set the shape and color\n" +
            "        switch(v.getTag().toString()){\n" +
            "            case \"circle\":shape = 0;break;\n" +
            "            case \"rectangle\": shape=1;break;\n" +
            "            case \"red\": paint.setColor(Color.RED);break;\n" +
            "            case \"blue\": paint.setColor(Color.BLUE);break;\n" +
            "            case \"green\": paint.setColor(Color.GREEN);break;\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_graphics_primitive.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.GraphicsPrimitiveFragment\"\n" +
            "    android:id=\"@+id/relgraph\">\n" +
            "    <ImageView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"400dp\"\n" +
            "        android:id=\"@+id/imgview\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/imgview\"\n" +
            "        android:text=\"Rectangle\"\n" +
            "        android:tag=\"rectangle\"\n" +
            "        android:id=\"@+id/rectangle\" />\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:tag=\"circle\"\n" +
            "        android:layout_below=\"@id/imgview\"\n" +
            "        android:layout_toRightOf=\"@id/rectangle\"\n" +
            "        android:id=\"@+id/circle\"\n" +
            "        android:text=\"Circle\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_toRightOf=\"@id/circle\"\n" +
            "        android:layout_below=\"@id/imgview\"\n" +
            "        android:text=\"Red\"\n" +
            "        android:tag=\"red\"\n" +
            "        android:id=\"@+id/redcolor\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_toRightOf=\"@id/redcolor\"\n" +
            "        android:layout_below=\"@id/imgview\"\n" +
            "        android:text=\"Blue\"\n" +
            "        android:tag=\"blue\"\n" +
            "        android:id=\"@+id/bluecolor\" />\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/redcolor\"\n" +
            "        android:text=\"Green\"\n" +
            "        android:tag=\"green\"\n" +
            "        android:id=\"@+id/greencolor\" />\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String multiThreading="\n# Multi Threading Code\n\n### Java Code\n#### MultiThreadingFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "import android.content.Intent;\n" +
            "import android.net.Uri;\n" +
            "import android.os.Bundle;\n" +
            "import android.os.Handler;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "import java.util.Timer;\n" +
            "\n" +
            "public class MultithreadingFragment extends Fragment{\n" +
            "    View v;\n" +
            "    Handler h;\n" +
            "    TextView c;\n" +
            "    Button b;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_multithreading,container,false);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        c = (TextView)v.findViewById(R.id.multxt2);\n" +
            "        b = (Button)v.findViewById(R.id.clickme);\n" +
            "        b.setVisibility(View.INVISIBLE);\n" +
            "        //Initializing handler to run a thread after one second\n" +
            "        h = new Handler();\n" +
            "        h.postDelayed(run,1000);\n" +
            "        return v;  //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        b.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(\"http://google.co.in/\"));\n" +
            "                startActivity(browserIntent);\n" +
            "\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "    //Thread to perform the operation\n" +
            "    Runnable run = new Runnable() {\n" +
            "        @Override\n" +
            "        public void run() {\n" +
            "            updateTime();\n" +
            "        }\n" +
            "    };\n" +
            "    public void updateTime(){\n" +
            "        c.setText(\"\"+(Integer.parseInt(c.getText().toString())-1));\n" +
            "        if(Integer.parseInt(c.getText().toString())==0){\n" +
            "            b.setVisibility(View.VISIBLE);\n" +
            "        }\n" +
            "        else{\n" +
            "            h.postDelayed(run,1000);\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_multithreading.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.MultithreadingFragment\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:text=\"Button will appear in\"\n" +
            "        android:id=\"@+id/multxt1\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/multxt1\"\n" +
            "        android:text=\"10\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"40sp\"\n" +
            "        android:id=\"@+id/multxt2\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/multxt2\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:text=\"Click Me\"\n" +
            "        android:id=\"@+id/clickme\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"/>\n" +
            "\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String calc="\n# Native Calculator\n\n### Java Code\n#### NativeCalculatorFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.EditText;\n" +
            "\n" +
            "\n" +
            "public class NativeCalculatorFragment extends Fragment implements View.OnClickListener{\n" +
            "    View v;\n" +
            "    float val1=0,val2=0;\n" +
            "    String op;\n" +
            "    EditText e;\n" +
            "    Button[] b;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_calculator,container,false);\n" +
            "        return v;//Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        b = new Button[16];\n" +
            "        e = (EditText)v.findViewById(R.id.val);\n" +
            "        b[0] = (Button)v.findViewById(R.id.b1);\n" +
            "        b[1] = (Button)v.findViewById(R.id.b2);\n" +
            "        b[2] = (Button)v.findViewById(R.id.b3);\n" +
            "        b[3] = (Button)v.findViewById(R.id.b4);\n" +
            "        b[4] = (Button)v.findViewById(R.id.b5);\n" +
            "        b[5] = (Button)v.findViewById(R.id.b6);\n" +
            "        b[6] = (Button)v.findViewById(R.id.b7);\n" +
            "        b[7] = (Button)v.findViewById(R.id.b8);\n" +
            "        b[8] = (Button)v.findViewById(R.id.b9);\n" +
            "        b[9] = (Button)v.findViewById(R.id.b0);\n" +
            "        b[10] = (Button)v.findViewById(R.id.eq);\n" +
            "        b[11] = (Button)v.findViewById(R.id.c);\n" +
            "        b[12] = (Button)v.findViewById(R.id.add);\n" +
            "        b[13] = (Button)v.findViewById(R.id.sub);\n" +
            "        b[14] = (Button)v.findViewById(R.id.mul);\n" +
            "        b[15] = (Button)v.findViewById(R.id.div);\n" +
            "        for(int i=0;i<16;i++){\n" +
            "            //Set onClick listener for each button\n" +
            "            b[i].setOnClickListener(this);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onClick(View view) {\n" +
            "        switch(view.getId()){\n" +
            "            //To erase the text in EditText.\n" +
            "            case R.id.c: e.setText(\"\");\n" +
            "                         val1=0;\n" +
            "                         val2=0;\n" +
            "                         break;\n" +
            "            case R.id.b1:\n" +
            "            case R.id.b2:\n" +
            "            case R.id.b3:\n" +
            "            case R.id.b4:\n" +
            "            case R.id.b5:\n" +
            "            case R.id.b6:\n" +
            "            case R.id.b7:\n" +
            "            case R.id.b8:\n" +
            "            case R.id.b9:\n" +
            "            case R.id.b0:// For digits 0-9 the corresponding number of button clicked is appended on the EditText\n" +
            "                         Button b = (Button)v.findViewById(view.getId());\n" +
            "                         e.setText(e.getText()+b.getText().toString());\n" +
            "                         break;\n" +
            "            case R.id.add:\n" +
            "            case R.id.sub:\n" +
            "            case R.id.mul:\n" +
            "            case R.id.div://The operation is identified here\n" +
            "                          val1 = Float.parseFloat(e.getText().toString());\n" +
            "                          e.setText(\"\");\n" +
            "                          Button b1 = (Button)v.findViewById(view.getId());\n" +
            "                          op = (String) b1.getText();\n" +
            "                          break;\n" +
            "            case R.id.eq: val2 =//Operation performed and result appended on EditText after '=' is pressed\n" +
            "                                Float.parseFloat(e.getText().toString());\n" +
            "                                switch(op){\n" +
            "                                case \"+\": val1 = val1+val2;\n" +
            "                                          break;\n" +
            "                                case \"-\": val1 = val1-val2;\n" +
            "                                    break;\n" +
            "                                case \"*\": val1 = val1*val2;\n" +
            "                                    break;\n" +
            "                                case \"/\": val1 = val1/val2;\n" +
            "                                    break;\n" +
            "                                }\n" +
            "                                e.setText(String.valueOf(val1));\n" +
            "                                val2=0;\n" +
            "                                op=\"\";\n" +
            "\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_calculator.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.NativeCalculatorFragment\">\n" +
            "\n" +
            "    <EditText\n" +
            "        android:layout_width=\"300dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:id=\"@+id/val\"/>\n" +
            "    <TableLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@+id/val\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:elevation=\"5dp\"\n" +
            "        android:shrinkColumns=\"*\"\n" +
            "        android:stretchColumns=\"*\">\n" +
            "        <TableRow\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\">\n" +
            "            <Button\n" +
            "                android:text=\"1\"\n" +
            "                android:id=\"@+id/b1\"/>\n" +
            "            <Button android:text=\"2\"\n" +
            "                android:id=\"@+id/b2\" />\n" +
            "            <Button android:text=\"3\"\n" +
            "                android:id=\"@+id/b3\"/>\n" +
            "            <Button android:text=\"4\"\n" +
            "                android:id=\"@+id/b4\"/>\n" +
            "        </TableRow>\n" +
            "        <TableRow\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\">\n" +
            "        <Button\n" +
            "            android:text=\"5\"\n" +
            "            android:id=\"@+id/b5\"/>\n" +
            "        <Button android:text=\"6\"\n" +
            "            android:id=\"@+id/b6\" />\n" +
            "        <Button android:text=\"7\"\n" +
            "            android:id=\"@+id/b7\"/>\n" +
            "        <Button android:text=\"8\"\n" +
            "            android:id=\"@+id/b8\"/>\n" +
            "        </TableRow>\n" +
            "        <TableRow\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\">\n" +
            "            <Button\n" +
            "                android:text=\"9\"\n" +
            "                android:id=\"@+id/b9\"/>\n" +
            "            <Button android:text=\"0\"\n" +
            "                android:id=\"@+id/b0\" />\n" +
            "            <Button android:text=\"=\"\n" +
            "                android:id=\"@+id/eq\"/>\n" +
            "            <Button android:text=\"C\"\n" +
            "                android:id=\"@+id/c\"/>\n" +
            "        </TableRow>\n" +
            "        <TableRow\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\">\n" +
            "            <Button\n" +
            "                android:text=\"+\"\n" +
            "                android:id=\"@+id/add\"/>\n" +
            "            <Button android:text=\"-\"\n" +
            "                android:id=\"@+id/sub\" />\n" +
            "            <Button android:text=\"*\"\n" +
            "                android:id=\"@+id/mul\"/>\n" +
            "            <Button android:text=\"/\"\n" +
            "                android:id=\"@+id/div\"/>\n" +
            "        </TableRow>\n" +
            "    </TableLayout>\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String rss="\n# RSS Feed Code\n\n### Java Code\n#### RssFeedFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "\n" +
            "import android.content.Intent;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.EditText;\n" +
            "\n" +
            "\n" +
            "/**\n" +
            " * A simple {@link Fragment} subclass.\n" +
            " */\n" +
            "public class RssFeedFragment extends Fragment {\n" +
            "    View v;\n" +
            "    EditText title,link,description;\n" +
            "    Button b1,b2;\n" +
            "    private String finalUrl=\"http://tutorialspoint.com/android/sampleXML.xml\";\n" +
            "    private HandleXML obj;\n" +
            "\n" +
            "    public RssFeedFragment() {\n" +
            "        // Required empty public constructor\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, ViewGroup container,\n" +
            "                             Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_rss_feed, container, false);\n" +
            "        title = (EditText)v.findViewById(R.id.editText);\n" +
            "        link = (EditText)v.findViewById(R.id.editText2);\n" +
            "        description = (EditText)v.findViewById(R.id.editText3);\n" +
            "        b1=(Button)v.findViewById(R.id.fetch);\n" +
            "        b2=(Button)v.findViewById(R.id.result);\n" +
            "        b1.setOnClickListener(new View.OnClickListener()\n" +
            "        {\n" +
            "            @Override\n" +
            "            public void onClick(View v)\n" +
            "            {\n" +
            "                obj = new HandleXML(finalUrl);\n" +
            "                obj.fetchXML();\n" +
            "                while(obj.parsingComplete);\n" +
            "                title.setText(obj.getTitle());\n" +
            "                link.setText(obj.getLink());\n" +
            "                description.setText(obj.getDescription());\n" +
            "            }\n" +
            "        });\n" +
            "        b2.setOnClickListener(new View.OnClickListener()\n" +
            "        {\n" +
            "            @Override\n" +
            "            public void onClick(View v)\n" +
            "            {\n" +
            "                Intent in=new Intent(getContext(),RssResult.class);\n" +
            "                startActivity(in);\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        return v;\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "\n```\n#### RssResult.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.support.v7.app.AppCompatActivity;\n" +
            "import android.os.Bundle;\n" +
            "import android.webkit.WebView;\n" +
            "\n" +
            "public class RssResult extends AppCompatActivity {\n" +
            "\n" +
            "    @Override\n" +
            "    protected void onCreate(Bundle savedInstanceState) {\n" +
            "        super.onCreate(savedInstanceState);\n" +
            "        setContentView(R.layout.activity_rss_result);\n" +
            "        WebView w1=(WebView)findViewById(R.id.webView);\n" +
            "        w1.loadUrl(\"http://tutorialspoint.com/android/sampleXML.xml\");\n" +
            "\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_rssfeed.xml\n```xml\n" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\".RssFeedFragment\">\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:textSize=\"40sp\"\n" +
            "        android:text=\"RSS Example\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:id=\"@+id/title\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/editText\"\n" +
            "        android:layout_below=\"@id/title\"\n" +
            "        android:layout_centerHorizontal=\"true\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/editText2\"\n" +
            "        android:layout_below=\"@id/editText\"\n" +
            "        android:layout_centerHorizontal=\"true\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"350dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/editText3\"\n" +
            "        android:layout_below=\"@id/editText2\"\n" +
            "        android:layout_centerHorizontal=\"true\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/editText3\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:text=\"Fetch\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:id=\"@+id/fetch\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Result\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:layout_below=\"@id/editText3\"\n" +
            "        android:layout_marginRight=\"20dp\"\n" +
            "        android:layout_alignParentRight=\"true\"\n" +
            "        android:id=\"@+id/result\"/>\n" +
            "</RelativeLayout>" +
            "\n```\n#### activity_rss_result.xml\n```xml\n" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\".RssResult\">\n" +
            "<WebView\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    android:id=\"@+id/webView\"></WebView>\n" +
            "</RelativeLayout>" +
            "\n```";

    public static String sms="\n# SMS Code\n\n### Java Code\n#### SmsFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "import android.Manifest;\n" +
            "import android.content.ContentResolver;\n" +
            "import android.content.pm.PackageManager;\n" +
            "import android.database.Cursor;\n" +
            "import android.os.Build;\n" +
            "import android.provider.ContactsContract;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.ActivityCompat;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.support.v4.content.ContextCompat;\n" +
            "import android.telephony.SmsManager;\n" +
            "import android.text.Editable;\n" +
            "import android.text.TextWatcher;\n" +
            "import android.util.Log;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.AdapterView;\n" +
            "import android.widget.ArrayAdapter;\n" +
            "import android.widget.AutoCompleteTextView;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.EditText;\n" +
            "import android.widget.TextView;\n" +
            "import android.widget.Toast;\n" +
            "\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "\n" +
            "import static android.content.ContentValues.TAG;\n" +
            "\n" +
            "public class SmsFragment extends Fragment {\n" +
            "    View v;\n" +
            "    int c = 160;\n" +
            "    String phno;\n" +
            "    boolean c1=false;\n" +
            "    List<String> phnnumbers;\n" +
            "    List<String>names;\n" +
            "    AutoCompleteTextView ac;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_sms,container,false);\n" +
            "        phno = null;\n" +
            "        ((TextView)v.findViewById(R.id.hint)).setText(c+\" characters left\");\n" +
            "        //Contacts of the phone are obtained using a Cursor\n" +
            "        ContentResolver cr = getActivity().getContentResolver();\n" +
            "        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);\n" +
            "        phnnumbers = new ArrayList<String>();\n" +
            "        names = new ArrayList<String>();\n" +
            "        if (c.getCount() > 0) {\n" +
            "            while (c.moveToNext()) {\n" +
            "                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));\n" +
            "                String phoneNumber =  c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));\n" +
            "                if(name!=null&&phoneNumber!=null) {\n" +
            "                    //The Contact is added to the list if the name and phone number are not null\n" +
            "                    names.add(name);\n" +
            "                    phnnumbers.add(phoneNumber);\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        ac = (AutoCompleteTextView)v.findViewById(R.id.contact);\n" +
            "        //Setting an array adapter using the names list\n" +
            "        ArrayAdapter<String> a = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,names);\n" +
            "        //set the adapter of AutoCompleteTextView\n" +
            "        ac.setAdapter(a);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        ac.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
            "            @Override\n" +
            "            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {\n" +
            "                //The name and phone number of contact selected are identified using this listener\n" +
            "                String name = (String)adapterView.getItemAtPosition(i);\n" +
            "                int i1 = names.indexOf(name);\n" +
            "                phno = phnnumbers.get(i1);\n" +
            "                c1 = true;\n" +
            "            }\n" +
            "        });\n" +
            "        ((EditText)v.findViewById(R.id.msg)).addTextChangedListener(new TextWatcher() {\n" +
            "            @Override\n" +
            "            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {\n" +
            "\n" +
            "            }\n" +
            "\n" +
            "            @Override\n" +
            "            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {\n" +
            "                //To show the character count\n" +
            "                c--;\n" +
            "                ((TextView)v.findViewById(R.id.hint)).setText(c+\" characters left\");\n" +
            "            }\n" +
            "\n" +
            "            @Override\n" +
            "            public void afterTextChanged(Editable editable) {\n" +
            "\n" +
            "            }\n" +
            "        });\n" +
            "        ((Button)v.findViewById(R.id.send)).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                if(c1==false){\n" +
            "                    Toast.makeText(getContext(),\"Select a Contact\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else if(((EditText)v.findViewById(R.id.msg)).getText()==null){\n" +
            "                    Toast.makeText(getContext(),\"Enter the message\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else{\n" +
            "                    //SmsManager is used to send the message. The phone number and message are obtained before itself\n" +
            "                    SmsManager s = SmsManager.getDefault();\n" +
            "                    s.sendTextMessage(phno,null,((EditText)v.findViewById(R.id.msg)).getText().toString(),null,null);\n" +
            "                    Toast.makeText(getContext(),\"Message Sent\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_sms.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.SmsFragment\">\n" +
            "\n" +
            "    <AutoCompleteTextView\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"?attr/actionBarSize\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:id=\"@+id/contact\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"300dp\"\n" +
            "        android:layout_below=\"@+id/contact\"\n" +
            "        android:layout_margin=\"10dp\"\n" +
            "        android:background=\"#70e8e8e8\"\n" +
            "        android:id=\"@+id/msg\"\n" +
            "        android:maxLength=\"160\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/msg\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/hint\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/hint\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:text=\"Send SMS\"\n" +
            "        android:id=\"@+id/send\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String sql="\n# SQL Code\n\n### Java Code\n#### SqlFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "\n" +
            "import android.content.Context;\n" +
            "import android.database.Cursor;\n" +
            "import android.database.sqlite.SQLiteDatabase;\n" +
            "import android.os.Bundle;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.EditText;\n" +
            "import android.widget.Toast;\n" +
            "public class SqlFragment extends Fragment implements View.OnClickListener{\n" +
            "    View v;\n" +
            "    int fl= 0;\n" +
            "    SQLiteDatabase db;\n" +
            "    Button insertBTN,updateBTN,deleteBTN,displayBTN,clearBTN;\n" +
            "    EditText name,phno,email,deletephno,displayphno;\n" +
            "    public SqlFragment() {\n" +
            "        // Required empty public constructor\n" +
            "    }\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, ViewGroup container,\n" +
            "                             Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_sql,container,false);\n" +
            "        //DataBase is created\n" +
            "        db=this.getActivity().openOrCreateDatabase(\"testdb\", Context.MODE_PRIVATE,null);\n" +
            "        //Table Creation\n" +
            "        db.execSQL(\"CREATE TABLE IF NOT EXISTS detail(name VARCHAR,phone VARCHAR,email VARCHAR);\");\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        name = v.findViewById(R.id.name);\n" +
            "        phno = v.findViewById(R.id.phoneno);\n" +
            "        email = v.findViewById(R.id.email);\n" +
            "        deletephno = v.findViewById(R.id.deletephno);\n" +
            "        displayphno = v.findViewById(R.id.displayphno);\n" +
            "        insertBTN = v.findViewById(R.id.insertBTN);\n" +
            "        updateBTN = v.findViewById(R.id.updateBTN);\n" +
            "        deleteBTN = v.findViewById(R.id.deleteBTN);\n" +
            "        displayBTN = v.findViewById(R.id.displayBTN);\n" +
            "        clearBTN = v.findViewById(R.id.clearBTN);\n" +
            "        //onClick listeners are set for the various Buttons(Operations)\n" +
            "        insertBTN.setOnClickListener(this);\n" +
            "        updateBTN.setOnClickListener(this);\n" +
            "        deleteBTN.setOnClickListener(this);\n" +
            "        displayBTN.setOnClickListener(this);\n" +
            "        clearBTN.setOnClickListener(this);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "    @Override\n" +
            "    public void onClick(View view) {\n" +
            "        //Insert Operation\n" +
            "        if(view==insertBTN){\n" +
            "            if(name.getText().toString().trim().length()==0&&phno.getText().toString().trim().length()==0&&email.getText().toString().trim().length()==0){\n" +
            "                Toast.makeText(getContext(),\"Enter all the details\",Toast.LENGTH_LONG).show();\n" +
            "            }\n" +
            "            else{\n" +
            "                Cursor c = db.rawQuery(\"SELECT * FROM detail;\",null);\n" +
            "                c.moveToFirst();\n" +
            "                do{\n" +
            "                    System.out.println(c.getString(1));\n" +
            "                    if(c.getString(c.getColumnIndex(\"phone\")).equals(phno.getText().toString())){\n" +
            "                        fl = 1;\n" +
            "                        break;\n" +
            "                    }\n" +
            "                }while(c.moveToNext());\n" +
            "                if(fl==0){\n" +
            "                    db.execSQL(\"INSERT INTO detail values ('\"+name.getText().toString()+\"','\"+phno.getText().toString()+\"','\"+email.getText().toString()+\"');\");\n" +
            "                    Toast.makeText(getContext(),\"Entry has been inserted\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"Entry already Exists\",Toast.LENGTH_SHORT).show();\n" +
            "                    fl=0;\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        //Update Operation\n" +
            "        if(view==updateBTN){\n" +
            "            if(name.getText().toString().trim().length()==0&&phno.getText().toString().trim().length()==0&&email.getText().toString().trim().length()==0){\n" +
            "                Toast.makeText(getContext(),\"Enter all the details\",Toast.LENGTH_LONG).show();\n" +
            "            }\n" +
            "            else{\n" +
            "                Cursor c = db.rawQuery(\"SELECT * FROM detail;\",null);\n" +
            "                c.moveToFirst();\n" +
            "                do{\n" +
            "                    System.out.println(c.getString(1));\n" +
            "                    if(c.getString(1).equals(phno.getText().toString())){\n" +
            "                        System.out.println(\"Entered\");\n" +
            "                        fl = 1;\n" +
            "                        break;\n" +
            "                    }\n" +
            "                }while(c.moveToNext());\n" +
            "                if(fl==1){\n" +
            "                    db.execSQL(\"UPDATE detail SET name='\"+name.getText().toString()+\"',email='\"+email.getText().toString()+\"' WHERE phone ='\"+phno.getText().toString()+\"';\");\n" +
            "                    Toast.makeText(getContext(),\"Entry has been updated\",Toast.LENGTH_SHORT).show();\n" +
            "                    fl=0;\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"Entry does not Exist\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        //Delete Operation\n" +
            "        if(view==deleteBTN){\n" +
            "            if(deletephno.getText().toString().trim().length()==0){\n" +
            "                Toast.makeText(getContext(),\"Enter the Phone Number\",Toast.LENGTH_SHORT);\n" +
            "            }\n" +
            "            else{\n" +
            "                Cursor c = db.rawQuery(\"SELECT * FROM detail;\",null);\n" +
            "                c.moveToFirst();\n" +
            "                do{\n" +
            "                    if(c.getString(c.getColumnIndex(\"phone\")).equals(deletephno.getText().toString())){\n" +
            "                        fl = 1;\n" +
            "                        break;\n" +
            "                    }\n" +
            "                }while(c.moveToNext());\n" +
            "                if(fl==1){\n" +
            "                    db.execSQL(\"DELETE FROM detail WHERE phone='\"+deletephno.getText().toString()+\"';\");\n" +
            "                    Toast.makeText(getContext(),\"Entry deleted successfully\",Toast.LENGTH_SHORT).show();\n" +
            "                    fl=0;\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"Entry does not Exist\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        //Select Operation\n" +
            "        if(view==displayBTN){\n" +
            "            if(displayphno.getText().toString().trim().length()==0){\n" +
            "                Toast.makeText(getContext(),\"Enter the Phone Number\",Toast.LENGTH_SHORT);\n" +
            "            }\n" +
            "            else{\n" +
            "                Cursor c = db.rawQuery(\"SELECT * FROM detail;\",null);\n" +
            "                c.moveToFirst();\n" +
            "                do{\n" +
            "                    if(c.getString(c.getColumnIndex(\"phone\")).equals(displayphno.getText().toString())){\n" +
            "                        fl = 1;\n" +
            "                        break;\n" +
            "                    }\n" +
            "                }while(c.moveToNext());\n" +
            "                if(fl==1){\n" +
            "                    Toast.makeText(getContext(),\"Name: \"+c.getString(0)+\"\\nPhone Number: \"+c.getString(1)+\"\\nE-mail: \"+c.getString(2),Toast.LENGTH_LONG).show();\n" +
            "                    fl=0;\n" +
            "                }\n" +
            "                else{\n" +
            "                    Toast.makeText(getContext(),\"Entry does not Exist\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        //Clear Fields Operation\n" +
            "        if(view==clearBTN){\n" +
            "            ((EditText)v.findViewById(R.id.name)).setText(\"\");\n" +
            "            ((EditText)v.findViewById(R.id.phoneno)).setText(\"\");\n" +
            "            ((EditText)v.findViewById(R.id.email)).setText(\"\");\n" +
            "            ((EditText)v.findViewById(R.id.deletephno)).setText(\"\");\n" +
            "            ((EditText)v.findViewById(R.id.displayphno)).setText(\"\");\n" +
            "            Toast.makeText(getContext(),\"Fields are cleared\",Toast.LENGTH_SHORT).show();\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_sql.xml\n```xml\n" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\".SqlFragment\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:text=\"Insert/Update (Update is made based on phone number)\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/ll1\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Name\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/ll1\"\n" +
            "        android:id=\"@+id/ll1s1\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"250dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_toRightOf=\"@id/ll1s1\"\n" +
            "        android:layout_below=\"@id/ll1\"\n" +
            "        android:layout_marginLeft=\"28dp\"\n" +
            "        android:layout_marginTop=\"-15dp\"\n" +
            "        android:id=\"@+id/name\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Phone No\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/ll1s1\"\n" +
            "        android:id=\"@+id/ll1s2\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"250dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_toRightOf=\"@id/ll1s1\"\n" +
            "        android:layout_below=\"@id/name\"\n" +
            "        android:layout_marginLeft=\"28dp\"\n" +
            "        android:layout_marginTop=\"-15dp\"\n" +
            "        android:id=\"@+id/phoneno\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"E-mail\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/ll1s2\"\n" +
            "        android:id=\"@+id/ll1s3\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"250dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_toRightOf=\"@id/ll1s3\"\n" +
            "        android:layout_below=\"@id/phoneno\"\n" +
            "        android:layout_marginLeft=\"25dp\"\n" +
            "        android:layout_marginTop=\"-15dp\"\n" +
            "        android:id=\"@+id/email\"/>\n" +
            "\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:text=\"Insert\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:textAllCaps=\"false\"\n" +
            "        android:layout_below=\"@id/ll1s3\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:layout_marginLeft=\"100dp\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:id=\"@+id/insertBTN\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Update\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:layout_below=\"@id/ll1s3\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_toRightOf=\"@id/insertBTN\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textAllCaps=\"false\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:id=\"@+id/updateBTN\"\n" +
            "        android:textSize=\"20sp\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Delete (Delete is performed based on Phone Number)\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/insertBTN\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/ll2\" />\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/ll2\"\n" +
            "        android:text=\"Phone Number\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/ll2s1\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"230dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/ll2\"\n" +
            "        android:layout_toRightOf=\"@id/ll2s1\"\n" +
            "        android:layout_marginTop=\"-17dp\"\n" +
            "        android:id=\"@+id/deletephno\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:text=\"Delete\"\n" +
            "        android:textAllCaps=\"false\"\n" +
            "        android:layout_below=\"@id/ll2s1\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:id=\"@+id/deleteBTN\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:text=\"Display User Details(Based on Phone Number)\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:layout_below=\"@id/deleteBTN\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/ll3\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:text=\"Phone Number\"\n" +
            "        android:layout_below=\"@id/ll3\"\n" +
            "        android:layout_marginLeft=\"20dp\"\n" +
            "        android:id=\"@+id/ll3s1\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"230dp\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/ll3\"\n" +
            "        android:layout_toRightOf=\"@+id/ll3s1\"\n" +
            "        android:layout_marginTop=\"-18dp\"\n" +
            "        android:id=\"@+id/displayphno\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:text=\"Display\"\n" +
            "        android:textAllCaps=\"false\"\n" +
            "        android:layout_below=\"@id/ll3s1\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:id=\"@+id/displayBTN\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"30sp\"\n" +
            "        android:layout_below=\"@id/displayBTN\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:textStyle=\"bold\"\n" +
            "        android:id=\"@+id/clearBTN\"\n" +
            "        android:text=\"Clear the fields\"\n" +
            "        android:textAllCaps=\"false\"/>\n" +
            "</RelativeLayout>" +
            "\n```";

    public static String tableLayout="\n# Table Layout Code\n\n### Java Code\n#### TableLayoutFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "public class TableLayoutFragment extends Fragment {\n" +
            "    View v;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_table_layout,container,false);\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_table_layout.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.TableLayoutFragment\">\n" +
            "\n" +
            "    <TableLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"match_parent\"\n" +
            "        android:shrinkColumns=\"*\"  android:stretchColumns=\"*\" android:background=\"#ffffff\">\n" +
            "        <TableRow\n" +
            "            android:gravity=\"center_horizontal\"\n" +
            "            android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\">\n" +
            "\n" +
            "            <TextView\n" +
            "                android:layout_width=\"84dp\"\n" +
            "                android:background=\"#b0b0b0\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:text=\"Row1\"\n" +
            "                android:textSize=\"30sp\"\n" +
            "                android:layout_span=\"3\" />\n" +
            "        </TableRow>\n" +
            "        <TableRow android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:background=\"#dcdcdc\">\n" +
            "            <TextView android:text=\"Row2 C1\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:textSize=\"30dp\"/>\n" +
            "            <TextView android:text=\"Row2 C2\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:textSize=\"30dp\"/>\n" +
            "            <TextView android:text=\"Row2 C3\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:textSize=\"30dp\"/>\n" +
            "        </TableRow>\n" +
            "        <TableRow android:layout_width=\"match_parent\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:gravity=\"center_horizontal\">\n" +
            "            <TextView android:text=\"Row3 C1\"\n" +
            "                android:layout_weight=\"1\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:textSize=\"30sp\"\n" +
            "                android:background=\"#b0b0b0\"/>\n" +
            "            <TextView android:text=\"Row3 C2\"\n" +
            "                android:textSize=\"30sp\"\n" +
            "                android:layout_weight=\"1\"\n" +
            "                android:fontFamily=\"@font/alegreya_sans\"\n" +
            "                android:background=\"#b0b0b0\" />\n" +
            "        </TableRow>\n" +
            "    </TableLayout>\n" +
            "\n" +
            "\n" +
            "</RelativeLayout>\n" +
            "\n```";

    public static String storage="\n# Write Storage Code\n\n### Java Code\n#### WriteStorageFragment.java\n```java\n" +
            "package com.example.sai_h.labex;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "import android.net.Uri;\n" +
            "import android.os.Bundle;\n" +
            "import android.os.Environment;\n" +
            "import android.support.annotation.Nullable;\n" +
            "import android.support.v4.app.Fragment;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.AdapterView;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.Spinner;\n" +
            "import android.widget.TextView;\n" +
            "import android.widget.Toast;\n" +
            "\n" +
            "import java.io.File;\n" +
            "import java.io.FileOutputStream;\n" +
            "import java.io.IOException;\n" +
            "\n" +
            "public class WriteStorageFragment extends Fragment {\n" +
            "    View v;\n" +
            "    TextView t1,t2,t3;\n" +
            "    Button b;\n" +
            "    String storagetype;\n" +
            "    File internal;\n" +
            "    @Nullable\n" +
            "    @Override\n" +
            "    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
            "        /*ViewGroup to be inflated into the activity\n" +
            "          This is not necessary in activity. In activity it is autogenerated\n" +
            "          super.onCreate(savedInstanceState);\n" +
            "          setContentView(R.layout.your_activity);\n" +
            "         */\n" +
            "        v = inflater.inflate(R.layout.fragment_write_storage,container,false);\n" +
            "        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v\n" +
            "        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);\n" +
            "        t1 = (TextView)v.findViewById(R.id.filecontent);\n" +
            "        t2 = (TextView)v.findViewById(R.id.fname);\n" +
            "        t3 = (TextView)v.findViewById(R.id.foname);\n" +
            "        b = (Button)v.findViewById(R.id.save);\n" +
            "        internal = Environment.getExternalStorageDirectory();\n" +
            "        return v; //Returns the Viewgroup to the activity class for inflation\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "        //Listener to write file to Internal Storage\n" +
            "        b.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                //Checking whether the fields are empty or not\n" +
            "                if(t1.getText()==null){\n" +
            "                    Toast.makeText(getContext(),\"Enter the Content for the file\",Toast.LENGTH_SHORT).show();\n" +
            "                } else if(t3.getText()==null){\n" +
            "                    Toast.makeText(getContext(),\"Enter the folder name\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else if(t2.getText()==null){\n" +
            "                    Toast.makeText(getContext(),\"Enter the File Name\",Toast.LENGTH_SHORT).show();\n" +
            "                }\n" +
            "                else{\n" +
            "\n" +
            "                    //Storing the directory as a string\n" +
            "                    String s = internal.toString()+\"/\"+t3.getText().toString();\n" +
            "                    //Directory is given for the file and the file is created\n" +
            "                    File finaldir = new File(s);\n" +
            "                    if(!finaldir.exists()){\n" +
            "                        boolean result = finaldir.mkdirs();\n" +
            "                        System.out.println(result);\n" +
            "                    }\n" +
            "                    //Textfile to be created is given in a new file\n" +
            "                    File f = new File(finaldir,t2.getText().toString()+\".txt\");\n" +
            "                    try {\n" +
            "                        //Text File Created\n" +
            "                        f.createNewFile();\n" +
            "                        byte[] data = t1.getText().toString().getBytes();\n" +
            "                        FileOutputStream fos = new FileOutputStream(f);\n" +
            "                        //Data written on the Text File\n" +
            "                        fos.write(data);\n" +
            "                        fos.flush();\n" +
            "                        fos.close();\n" +
            "                        Toast.makeText(getContext(), \"File Created\", Toast.LENGTH_SHORT).show();\n" +
            "                    } catch (IOException e) {\n" +
            "                        e.printStackTrace();\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "}\n" +
            "\n```\n\n### Xml Code\n#### fragment_write_storage.xml\n```xml\n" +
            "<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\"com.example.sai_h.labex.WriteStorageFragment\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:layout_marginRight=\"10dp\"\n" +
            "        android:text=\"Enter the Content\"\n" +
            "        android:id=\"@+id/lbl1\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_below=\"@id/lbl1\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:layout_marginRight=\"10dp\"\n" +
            "        android:id=\"@+id/filecontent\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/lbl2\"\n" +
            "        android:layout_below=\"@id/filecontent\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:layout_marginRight=\"10dp\"\n" +
            "        android:text=\"File Name\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:id=\"@+id/fname\"\n" +
            "        android:layout_below=\"@id/lbl2\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:layout_marginRight=\"10dp\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20dp\"\n" +
            "        android:text=\"Folder Name\"\n" +
            "        android:layout_below=\"@id/fname\"\n" +
            "        android:id=\"@+id/lbl3\"\n" +
            "        android:layout_marginLeft=\"10dp\"/>\n" +
            "    <EditText\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/lbl3\"\n" +
            "        android:layout_marginLeft=\"10dp\"\n" +
            "        android:id=\"@+id/foname\"\n" +
            "        android:layout_marginRight=\"10dp\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Save\"\n" +
            "        android:fontFamily=\"@font/alegreya_sans\"\n" +
            "        android:textSize=\"20sp\"\n" +
            "        android:layout_below=\"@id/foname\"\n" +
            "        android:layout_centerHorizontal=\"true\"\n" +
            "        android:layout_marginTop=\"10dp\"\n" +
            "        android:id=\"@+id/save\"/>\n" +
            "</RelativeLayout>\n" +
            "\n```";

}
