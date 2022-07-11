package com.linkdevelopment.newsfeedapp.ui.livechat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveChatViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LiveChatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is live chat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}