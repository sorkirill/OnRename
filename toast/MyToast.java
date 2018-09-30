package com.example.onrename.toast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyToast extends Toast {
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void setDuration(int duration) {
        duration = LENGTH_LONG;
        super.setDuration(duration);
    }

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToast(Context context) {
        super(context);

    }
}
