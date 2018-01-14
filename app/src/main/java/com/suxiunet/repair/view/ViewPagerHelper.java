package com.suxiunet.repair.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.suxiunet.data.util.DensityUtil;
import com.suxiunet.repair.R;
import com.suxiunet.repair.base.baseui.SuXiuApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 月光和我 on 2017/3/24.
 */
public class ViewPagerHelper extends FrameLayout {
    //图片资源
    List<String> images = new ArrayList<>();
    //轮播点点的颜色
    private int selPointColor;
    private int nomalPointColor;
    
    //轮播点的背景
    private int pointBg;
    
    //决定是否开启轮播，默认是开启的
    private boolean isAuto = true;
    private Timer mTimer = new Timer();
    private TimerTask mTask;
    //通过Handler设置轮播图的自动轮播
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 500:
                    int currentItem = mVp.getCurrentItem();
                    mVp.setCurrentItem(currentItem + 1,true);
                    break;
            }
        }
    };
    private LinearLayout mPointLayout;
    private CustomViewPager mVp;
    private int mNewPosition;

    public ViewPagerHelper(Context context) {
        this(context,null);
    }

    public ViewPagerHelper(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewPagerHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        //拿到属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.carousel);
        selPointColor = array.getResourceId(R.styleable.carousel_selPoiColor, 0);
        nomalPointColor = array.getResourceId(R.styleable.carousel_nomPoiColor, 0);
        pointBg = array.getResourceId(R.styleable.carousel_pointBg, 0);
        array.recycle();
    }

    /**
     * 开启轮播图
     */
    public void startAuto() {
        isAuto = true;
    }

    /**
     * 关闭轮播图
     */
    public void stopAuto() {
        isAuto = false;
    }

    public void hindPoint() {
        if (mPointLayout != null) {
            mPointLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化图片资源
     * @param images
     */
    public void setImageResource(List<String> images) {
        this.images.clear();
        this.images.addAll(images);
    }

    /**
     * 设置被选中轮播点的背景
     * @param selColor
     */
    public void setSelPointColor(int selColor) {
        selPointColor = selColor;
    }

    /**
     * 设置普通轮播点的背景
     * @param nomalPointCol
     */
    public void setNomPointColor(int nomalPointCol) {
        nomalPointColor = nomalPointCol;
    }

    /**
     * 轮播图的入口
     */
    public void show() {
        if (images.size() > 0) {
            init();
        }
    }

    /**
     * 释放Handler
     */
    public void releast() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (mTask != null) {
            mTask.cancel();
        }
    }
    
    private void init() {
        //初始化ViewPager
        initViewPager();
        //初始化轮播点容器
//        if (images.size() > 1) {
            initLayout();
//        }
        //开启自动轮播
        if (mTask == null) {//防止开启多个任务
            mTask = new TimerTask() {
                @Override
                public void run() {
                    if (isAuto) {
                        if (mHandler != null) {
                            mHandler.sendEmptyMessage(500);
                        }
                    }
                }
            };
            //一秒后开启轮播图
            mTimer.schedule(mTask,1000,5000);
        }
    }

    /**
     * 初始化轮播图的容器
     */
    private void initLayout() {
        mPointLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getContext(), 20));
        params.gravity = Gravity.BOTTOM;
        mPointLayout.setLayoutParams(params);
        addView(mPointLayout);
        mPointLayout.setBackgroundResource(pointBg);
        initPoint(mPointLayout);
    }

    /**
     * 初始化轮播点
     * @param layout
     */
    private void initPoint(LinearLayout layout) {
        layout.setGravity(Gravity.CENTER);
        //先清空父容器中的point
        layout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(getContext(), 6), DensityUtil.dip2px(getContext(), 6));
        for (int i = 0,j = images.size();i < j; i ++) {
            ImageView iv = new ImageView(getContext());
            params.leftMargin = DensityUtil.dip2px(getContext(), 6);
            if (i != 0) {
                iv.setImageResource(nomalPointColor);
            } else {
                iv.setImageResource(selPointColor);
            }
            
            iv.setLayoutParams(params);
            layout.addView(iv);
        }
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
            mVp = new CustomViewPager(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mVp.setLayoutParams(params);

            //给viewPager设置适配器
            mVp.setAdapter(adapter);
            //设置viewpager的初始位置
            int value = Integer.MAX_VALUE / 2;
            mVp.setCurrentItem(value - (value % images.size()));
            //给viewPager设置滑动监听
            mVp.addOnPageChangeListener(listener);
            //将viewPager添加到父容器中去
            addView(mVp);
        
    }
    //ViewPager的适配器
    PagerAdapter adapter = new PagerAdapter() {
       
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % images.size();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView iv = new ImageView(SuXiuApplication.appContext);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            ImageUtil.getInstance().setViewPagerImage(container.getContext(),images.get(newPosition),iv);
            Glide.with(container.getContext()).load(images.get(newPosition)).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    //viewPager的滑动监听
    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            mNewPosition = position % images.size();
            for (int i = 0; i < images.size(); i ++) {
                ((ImageView)mPointLayout.getChildAt(mNewPosition)).setImageResource(selPointColor);
                if (i != mNewPosition) {
                    ((ImageView)mPointLayout.getChildAt(i)).setImageResource(nomalPointColor);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            
        }
    };
    
    //自定义ViewPager ，设置其事件分发
    public class CustomViewPager extends ViewPager {

        public CustomViewPager(Context context) {
            super(context);
        }

        public CustomViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        int flag = -1;
        float mDownX;
        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    flag = 0;
                    mDownX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveY = ev.getX();
                    if (Math.abs(moveY - mDownX) > 5) {
                        flag = 1;
                    }
                    stopAuto();
                    break;
                case MotionEvent.ACTION_UP:
                    startAuto();
                    if (flag == 0) {//证明是点击事件
                        if (mListener != null) {
                            mListener.onViewPagerClick(mNewPosition);
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    startAuto();
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                    startAuto();
                    break;
            }
            return super.onTouchEvent(ev);
        }
    }
    
    //点击事件的回掉接口
    private OnViewPagerClickListener mListener;
    public void setOnViewPagerClickListener(OnViewPagerClickListener listener) {
        mListener = listener;
    }
    public interface OnViewPagerClickListener{
        void onViewPagerClick(int index);
    }
}
