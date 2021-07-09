package com.example.alartestapp.ui.data;

import com.example.alartestapp.common.BaseView;
import com.example.alartestapp.model.DataResponse;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface DataView extends BaseView {
    void showData(@NonNull List<DataResponse> data);

}
