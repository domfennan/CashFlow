package com.example.cashflow.ui.relatorio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RelatorioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RelatorioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is semana fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}