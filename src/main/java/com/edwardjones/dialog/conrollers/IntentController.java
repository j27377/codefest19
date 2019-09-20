package com.edwardjones.dialog.conrollers;

import com.edwardjones.dialog.GoogleProxy;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class IntentController {

    @PostMapping("/intent")
    public String detectIntent(@RequestBody String utterance) throws IOException {
        GoogleProxy proxy = new GoogleProxy();
        String intent = proxy.detectIntent(utterance);
        System.out.println(utterance);
        System.out.println(intent);
        return intent;
    }
}