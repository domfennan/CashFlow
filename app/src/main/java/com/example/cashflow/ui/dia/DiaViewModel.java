package com.example.cashflow.ui.dia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DiaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dia fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}