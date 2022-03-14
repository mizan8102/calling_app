package com.mnewspaper.callingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.secret_code);
        button=findViewById(R.id.submit_button);

        URL serverurl;
        try {
            serverurl=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions joptions = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(editText.getText().toString())
                        .setAudioMuted(false)
                        .setVideoMuted(false)
                        .setAudioOnly(false)
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(MainActivity.this,joptions);
            }
        });
    }
}