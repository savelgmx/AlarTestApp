package com.example.alartestapp.common;

import android.support.v4.app.Fragment;

/**
 * @author Azret Magometov
 */
public interface RefreshOwner {
    void changeFragment(Fragment fragment);

    void setRefreshState(boolean refreshing);
}