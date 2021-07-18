package com.example.alartestapp.ui.data;



import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.common.BasePresenter;
import com.example.alartestapp.model.Data;
import com.example.alartestapp.model.DataResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
@InjectViewState
public class DataPresenter extends BasePresenter<DataView> {
    private final DataView mView;
    private String mCode;

    public DataPresenter(DataView view){
        mView = view;
    };

    //https://github.com/matthiasbruns/rxandroid2-retrofit2

    //https://github.com/sarimk80/SimpleDagger
    //https://www.youtube.com/watch?v=8plk_opFf1M

    /*
        public void getDataResponse(String code){
            this.mCode = code;
            //здесь должен быть запрос GET http://www.alarstudios.com/test/data.cgi
            mCompositeDisposable.add(ApiUtils.getApiService().getDataResponse(this.mCode,"10")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getViewState().showRefresh())
                    .doFinally(() ->getViewState().hideRefresh())
                    .subscribe(
                            response -> {
                                getViewState().showData(response.getData());

                            },
                            throwable -> {
                             getViewState().showError();
                            }
                    )
            );

        }
    */
    public void getDataResponse(String code){
        this.mCode = code;

        ApiUtils.getApiService().getDataResponse(code,"10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<DataResponse, List<Data.Datum>>() {

                    @Override
                    public List<Data.Datum> apply(@NonNull DataResponse dataResponse) throws Exception {
                        return dataResponse.mData;
                    }
                })
                .subscribe(new Consumer<List<Data.Datum>>() {
                    @Override
                    public void accept(List<Data.Datum> data) throws Exception {


                        //displayData method call here
                        displayData(data);

                    }
                });


    }

    private void displayData(@NonNull final List<Data.Datum> datumList){
        // Cheap way to display a list of Strings - I was too lazy to implement a RecyclerView

        final StringBuilder output = new StringBuilder();
        for (final Data.Datum data : datumList) {
            output.append(data.getName()).append("\n");
        }

        Log.d("OkHttpd",output.toString());
    }

}
