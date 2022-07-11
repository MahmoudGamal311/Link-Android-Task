package com.linkdevelopment.newsfeedapp.ui.emagazine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class eMagazineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public eMagazineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is E-Magazine fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}