package com.example.alartestapp.ui.auth;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alartestapp.R;
import com.example.alartestapp.common.PresenterFragment;
import com.example.alartestapp.model.AuthResponse;

import org.jetbrains.annotations.NotNull;


//       //https://github.com/matthiasbruns/rxandroid2-retrofit2
//        // http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/




public class AuthFragment extends PresenterFragment implements AuthView {
    private AutoCompleteTextView mUserName;
    private EditText mPassword;
    private Button mEnter;

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();

    @InjectPresenter
    AuthPresenter mPresenter;

    @ProvidePresenter
    AuthPresenter providePresenter(){
        return new AuthPresenter(this);
    }

    @Override
    protected AuthPresenter getPresenter(){
        return mPresenter;
    }


    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isPasswordValid()) {

                mPresenter.AuthResponse();



 /*               ApiUtils.getApiService().getAuthResponce(BuildConfig.USERNAME,BuildConfig.PASSWORD)
                        .map(AuthResponse::getCode)
                        .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                        .doFinally(() -> mIsLoading.postValue(false))
                        .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
                        .subscribe(code->{
                                    //здесь данные которые успешно извлечены из user после вызова API
                                    //далее действия при успехе

                                    Intent startProfileIntent = new Intent(getActivity(), DataActivity.class);
                                    startProfileIntent.putExtra(DataActivity.CODE_KEY, code);
                                    startActivity(startProfileIntent);


                                },throwable -> {
                                  //  Toast.makeText(getActivity(),R.string.auth_error, Toast.LENGTH_SHORT).show();
                                }
                        );

*/
            } else {

                showMessage(R.string.input_error);
            }
        }
    };


    private View.OnFocusChangeListener mOnUserNameFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                mUserName.showDropDown();
            }
        }
    };


    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(mPassword.getText());
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_auth, container, false);

        mUserName = v.findViewById(R.id.etLogin);
        mPassword = v.findViewById(R.id.etPassword);
        mEnter = v.findViewById(R.id.buttonEnter);
        mEnter.setOnClickListener(mOnEnterClickListener);
        mUserName.setOnFocusChangeListener(mOnUserNameFocusChangeListener);
        return v;
    }

    @Override
    public void showAuthResponse(@NonNull AuthResponse authresponse) {

    }

    @Override
    public void openDataFragment(@NotNull String code) {
        //здесь собственно передаем параметры



    }


    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {

    }
}


