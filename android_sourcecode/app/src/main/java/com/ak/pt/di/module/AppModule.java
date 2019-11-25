package com.ak.pt.di.module;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ak.pt.App;
import com.ak.pt.bean.UserBean;
import com.ak.pt.util.IsInternet;
import com.ak.pt.util.SpSingleInstance;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/24
 */
@Module
public class AppModule {
    /**
     * 默认超时时间 单位/秒
     */
    private static final int DEFAULT_TIME_OUT = 20;

    private Context context;

    private String baseUrl;
    private UserBean userBean;

    public AppModule(App context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(com.ak.pt.http.converter.CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new CommonInterceptor())
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    public com.ak.pt.http.APIService provideAPIService() {
        return provideRetrofit().create(com.ak.pt.http.APIService.class);
    }

    private class CommonInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request oldRequest = chain.request();
            //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
            if (oldRequest.url().toString().contains("uploadFile")) {
                return chain.proceed(oldRequest);
            } else {
                FormBody body = (FormBody) oldRequest.body();
                Map<String, String> oldMap = new HashMap<>();
                FormBody.Builder newFormBody = new FormBody.Builder();
                for (int i = 0; i < body.size(); i++) {
                    oldMap.put(body.name(i), body.value(i));
                    newFormBody.addEncoded(body.encodedName(i), body.encodedValue(i));
                }
                List<Map.Entry<String, String>> infoIds = new ArrayList<>(oldMap.entrySet());
                // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
                Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                        return (o1.getKey()).toString().compareTo(o2.getKey());
                    }
                });
                // 构造签名键值对的格式
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> item : infoIds) {
                    if (item.getValue() != null && ! "".equals(item.getValue())) {
                        String key = item.getKey();
                        String val = item.getValue();
                        sb.append(key + "=" + val + "&");
                    }
                }
                String effectTime = System.currentTimeMillis() + "";
                String randomStr = (int) (Math.random() * 900 + 100) + "";
                sb.append("randomStr=" + randomStr + "&effectTime=" + effectTime + "&secretKey=8fY^E1oRdPeDWIGD");
                String md5 = md5(sb.toString());
                //请求体定制：统一添加参数
                newFormBody.addEncoded("randomStr", randomStr);
                newFormBody.addEncoded("effectTime", effectTime);
                newFormBody.addEncoded("sign", md5);
                Request build = oldRequest.newBuilder()
                        .method(oldRequest.method(), newFormBody.build())
                        .url(oldRequest.url())
                        .build();
                return chain.proceed(build);
            }

        }
    }


    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }

}
