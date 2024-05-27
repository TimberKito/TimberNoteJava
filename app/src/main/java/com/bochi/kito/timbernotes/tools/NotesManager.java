package com.bochi.kito.timbernotes.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class NotesManager {
    public static Boolean spGetFirstNote(Context context) {
        SharedPreferences data = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return data.getBoolean(AppConstString.IS_FIRST_NOTE, true);
    }

    public static void spPutFirstNote(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        edit.putBoolean(AppConstString.IS_FIRST_NOTE, false);
        edit.apply();
    }
}
