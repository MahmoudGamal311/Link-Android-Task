package com.linkdevelopment.newsfeedapp.ui.wishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WishListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WishListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is wish list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}