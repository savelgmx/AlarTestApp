package com.example.alartestapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alartestapp.R;
import com.example.alartestapp.api.ApiUtils;
import com.example.alartestapp.ui.data.DataActivity;
import com.example.alartestapp.ui.data.DataFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


//       //https://github.com/matthiasbruns/rxandroid2-retrofit2
//        // http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/

//TODO перенести сюда запрос видимо его следует переписать согласно http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/
//TODO change androidManifest to AuthActivity as MAIN
//TODO переписать этот фрагмент согласно https://github.com/savelgmx/rxjava2albumapp.git


public class AuthFragment extends Fragment {
    private AutoCompleteTextView mUserName;
    private EditText mPassword;
    private Button mEnter;

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
                String credentials = mUserName.getText().toString()+ ":" +mPassword.getText().toString();// concatenate username/email and password with colon for authentication
                final String authHeader= "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);//form authenfication header
                ApiUtils.getApiService(mUserName.getText().toString(),mPassword.getText().toString(),true)
                        .authentication()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
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


            } else {

                showMessage(R.string.input_error);
            }
        }
    };


    private View.OnFocusChangeListener mOnEmailFocusChangeListener = new View.OnFocusChangeListener() {
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
        mUserName.setOnFocusChangeListener(mOnEmailFocusChangeListener);
        return v;
    }
}


