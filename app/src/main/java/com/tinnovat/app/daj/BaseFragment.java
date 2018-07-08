package com.tinnovat.app.daj;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Anjali on 08-07-2018.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}
