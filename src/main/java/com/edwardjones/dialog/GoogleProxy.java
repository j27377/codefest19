package com.edwardjones.dialog;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class GoogleProxy {

    public static final String CREDENTIAL_PATH = "/codefest/FA-Trainee-d6f0cf176313.json";

    public static String detectIntent(String utterance) throws IOException {

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIAL_PATH))
                .createScoped(new ArrayList(Collections.singleton("https://www.googleapis.com/auth/cloud-platform")));

        SessionsSettings sessionsSettings =
                SessionsSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                        .build();

        SessionsClient sessionsClient = SessionsClient.create(sessionsSettings);

        SessionName session = SessionName.of("fa-trainee", "test1");

        QueryInput queryInput = QueryInput.
                    newBuilder().
                    setText(TextInput.newBuilder().setLanguageCode("EN").setText(utterance).build()).
                    build();

        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
        String responseText = response.getQueryResult().
                getFulfillmentMessages(0).
                getText().
                toString();

        return responseText.substring(7, responseText.length() -2);
    }
}