package com.suxiunet.repair.view;

/**
 * Copyright (C), 2017/10/23 91账单
 * Author: chenzhi
 * Email: chenzhi@91zdan.com
 * Description:
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DialogHelper extends Dialog {
    private Activity context;

    private DialogHelper(Activity context, int style) {
        super(context, style);
        this.context = context;
    }

    private DialogHelper(Activity context, boolean cancelable,
                         OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    public void dismiss() {
        if (context != null) {
            try {
                super.dismiss();
} catch (Exception e) {

        }
        }
        }

public static class Builder {

    private Activity mContext;
    private View mContentView;
    private int style;
    private int width;
    private int height;
    private int gravity;
    private boolean isCancelable;
    private int x;
    private int y;
    private int animal;

    public Builder(Activity context) {
        this.mContext = context;
    }

    public Builder setContentView(View view) {
        this.mContentView = view;
        return this;
    }

    public Builder setStyle(int style) {
        this.style = style;
        return this;
    }


    public Builder setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Builder setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Builder setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public Builder setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        return this;
    }

    public Builder setAnimal(int animal) {
        this.animal = animal;
        return this;
    }

    public Dialog createDialog() {
        Dialog dialog = createDialog(mContext, mContentView);
        return dialog;
    }

    public Dialog createDialog(Activity context, View contextView) {
        return createDialog(context, contextView, width, height, isCancelable, style,animal);
    }

    public Dialog createDialog(Activity context, View contextView, int width, int height, boolean cancellable, int style,int animal) {
        Dialog dialog = new DialogHelper(context, style);
        dialog.setCanceledOnTouchOutside(cancellable);
        dialog.setCancelable(cancellable);
        dialog.setContentView(contextView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window
                .getAttributes();

        if (width > 0) {
            params.width = dip2px(width, context);
        } else {
            params.width = width;
        }
        if (height > 0) {
            params.height = dip2px(height, context);
        } else {
            params.height = height;
        }

        if (x != 0) {
            params.x = x;
        }

        if (y != 0) {
            params.y = y;
        }
        window.setAttributes(params);
        window.setGravity((gravity == 0) ? Gravity.CENTER : gravity);
        window.setWindowAnimations(animal);
        return dialog;
    }

}
    private static int dip2px(float dpValue, Context mContext) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}