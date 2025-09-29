package com.example.trabajopractico3.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LiveData<String> getText() {
        if (mText == null) {
            mText = new MutableLiveData<>();
            mText.setValue("Salir de la aplicación");
        }
        return mText;
    }
}

