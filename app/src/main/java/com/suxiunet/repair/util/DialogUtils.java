package com.suxiunet.repair.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.suxiunet.repair.R;
import com.suxiunet.repair.view.DialogHelper;

/**
 * author : chenzhi
 * time   : 2017/12/12
 * desc   : 
 */
public class DialogUtils {
    private DialogUtils() {
        
    }
    
    private static DialogUtils mInstance;

    public static DialogUtils getInstance() {
        if (mInstance == null) {
            synchronized (DialogUtils.class) {
                if (mInstance == null) {
                    mInstance = new DialogUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 给公用Dialog设置属性
     * @param context
     * @param view
     * @return
     */
    public Dialog initCommonDialog(Activity context, View view, int gravity, boolean isCancelable, int width, int height, int animal) {
        return new DialogHelper.Builder(context)
                .setGravity(gravity)
                .setCancelable(isCancelable)
                .setContentView(view)
                .setSize(width, height)
                .setStyle(R.style.Dialog_Theme)
                .setAnimal(animal)
                .createDialog();
    }

    /**
     * 弹在中间的Dialog
     * @param activity
     * @param view
     * @param isCancelable
     * @return
     */
    public Dialog setCenterDialog(Activity activity, View view, boolean isCancelable) {
        return initCommonDialog(activity, view, Gravity.CENTER, isCancelable, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
    }

    public Dialog setBottomDialog(Activity activity, View view, boolean isCancelable,int anim) {
        return initCommonDialog(activity, view, Gravity.BOTTOM, isCancelable, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, anim);
    }
}
