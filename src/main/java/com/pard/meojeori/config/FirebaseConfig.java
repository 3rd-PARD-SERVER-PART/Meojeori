package com.pard.meojeori.config;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        try {
        FileInputStream serviceAccount =
                new FileInputStream("firebaseConfigPath");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("gs://meojeori.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
