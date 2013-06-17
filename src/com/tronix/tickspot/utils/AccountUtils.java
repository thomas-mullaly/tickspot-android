package com.tronix.tickspot.utils;

import android.content.Context;
import android.content.Intent;
import com.tronix.tickspot.ui.LoginActivity;

public class AccountUtils {
    public static void startLoginFlow(final Context context, final Intent finishIntent) {
        Intent loginIntent = new Intent(context, LoginActivity.class);
        loginIntent.putExtra(LoginActivity.EXTRA_FINISH_INTENT, finishIntent);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(loginIntent);
    }
}
