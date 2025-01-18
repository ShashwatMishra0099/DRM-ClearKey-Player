package com.example.drmplayer;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ExoPlayerHelper playerHelper;
    private EditText urlInput, keyIdInput, keyInput;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlInput = findViewById(R.id.urlInput);
        keyIdInput = findViewById(R.id.keyIdInput);
        keyInput = findViewById(R.id.keyInput);
        playButton = findViewById(R.id.playButton);

        playerHelper = new ExoPlayerHelper(this);

        playButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            String keyId = keyIdInput.getText().toString();
            String key = keyInput.getText().toString();
            playerHelper.playStream(url, keyId, key);
        });
    }
}
