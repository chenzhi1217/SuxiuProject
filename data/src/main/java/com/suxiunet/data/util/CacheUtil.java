package com.suxiunet.data.util;

import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;

import com.suxiunet.data.DatasConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by 月光和我 on 2016/9/16.
 */
public class CacheUtil {
    private String saveDir;//缓存的文件夹地址
    private static File mFileDir;
    private File mCacheFile;

    public static final String USER_INFO = "user_info";//用户信息


    //单例模式
    private CacheUtil() {
        //缓存的文件夹地址)
            saveDir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator+ DatasConstant.mApplication.getPackageName()
                    + File.separator+"cache";
            mFileDir = new File(saveDir);
            if (!mFileDir.exists()) {
                mFileDir.mkdirs();//该文件为多级目录
            }
        //创建文件夹
    }

    private static CacheUtil mCacheManager = null;

    public static CacheUtil getInstance() {
        if (mCacheManager == null) {
            synchronized (CacheUtil.class) {
                if (mCacheManager == null) {
                    mCacheManager = new CacheUtil();
                }
            }
        }
        return mCacheManager;
    }


    /**
     * 将获取的网络数据序列化到本地缓存中
     * @param
     * @param method
     * @throws IOException
     */
    public void saveCacheData(Serializable bean, String method){
        mCacheFile = new File(mFileDir, method);
        ObjectOutputStream ops = null;
        try {
            ops = new ObjectOutputStream(new FileOutputStream(mCacheFile));
            ops.writeObject(bean);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从本地缓存中取出数据
     * @param method
     * @return
     * @throws Exception
     */
    public <T>  T getCacheData(String method, Class<T> clazz) {
        T bean = null;
        mCacheFile = new File(mFileDir, method);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(mCacheFile));
            bean = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return (T) bean;
    }
}
