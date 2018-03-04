package com.tomlockapps.userbrowser.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by tomlo on 3/4/2018.
 */

public class ProcessUtil {

    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getProcessName(context));
    }

    public static String getProcessName(Context context) {
        int mypid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == mypid) {
                return info.processName;
            }
        }
        // may never return null
        return null;
    }

}
