package com.pard.meojeori.config;

import org.springframework.beans.factory.annotation.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Value("${firebase.storage.bucket}")
    private String firebaseStorageBucket;

    @PostConstruct
    public void initialize() {
        try {
            if (firebaseConfigPath == null || firebaseConfigPath.isEmpty()) {
                throw new IllegalArgumentException("firebase.config.path is not set or empty.");
            }

            System.out.println("Firebase config path: " + firebaseConfigPath);

            if (!Files.exists(Paths.get(firebaseConfigPath))) {
                throw new IllegalArgumentException("Firebase config file not found at path: " + firebaseConfigPath);
            }
        FileInputStream serviceAccount =
                new FileInputStream(firebaseConfigPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(firebaseStorageBucket)
                .build();

        FirebaseApp.initializeApp(options);
    }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
