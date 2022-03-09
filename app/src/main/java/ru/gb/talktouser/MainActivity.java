package ru.gb.talktouser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void onDialogResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MessagesFragment messagesFragment = new MessagesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.messages, messagesFragment).commit();
        }
    }
}