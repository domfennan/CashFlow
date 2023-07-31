package com.example.cashflow.ui.registros;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RegistrosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dia fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}