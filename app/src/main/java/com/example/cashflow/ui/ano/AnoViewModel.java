package com.example.cashflow.ui.ano;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ano fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}