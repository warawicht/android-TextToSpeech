package com.example.toon.texttospeech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TTSManagerChina {
    private TextToSpeech mTts = null;
    private boolean isLoaded = false;

    public void init(Context context) {
        try {
            mTts = new TextToSpeech(context, onInitListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = mTts.setLanguage(Locale.CHINESE);
                isLoaded = true;
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("Error", "This Languge is not Supported");
                }
            } else {
                Log.e("Error", "Init Failed");
            }
        }
    };

    public void shutDown() {
        mTts.shutdown();
    }

    public void addQueue(String text) {
        if (isLoaded) {
            mTts.speak(text, TextToSpeech.QUEUE_ADD, null);
        } else {
            Log.e("Error", "TTS not Init");
        }
    }

    public void initQueue(String text) {
        if (isLoaded) {
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            Log.e("Error", "TTS not Init");
        }
    }
}
