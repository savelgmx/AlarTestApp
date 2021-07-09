package com.example.alartestapp.ui.auth;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alartestapp.common.PresenterFragment;


//       //https://github.com/matthiasbruns/rxandroid2-retrofit2
//        // http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/

//TODO перенести сюда запрос видимо его следует переписать согласно http://javaway.info/ispolzovanie-retrofit-2-v-prilozheniyah-android/
//TODO change androidManifest to AuthActivity as MAIN
//TODO переписать этот фрагмент согласно https://github.com/savelgmx/rxjava2albumapp.git


public class AuthFragment extends PresenterFragment {

    @InjectPresenter
    AuthPresenter mAuthPresenter;

    @ProvidePresenter
    AuthPresenter providePresenter() {
        return new AuthPresenter();
    }

    protected AuthPresenter getPresenter() {
        return mAuthPresenter;
    }





    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

}
