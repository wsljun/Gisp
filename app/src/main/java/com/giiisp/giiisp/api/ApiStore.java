package com.giiisp.giiisp.api;

import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.giiisp.giiisp.BuildConfig;
import com.giiisp.giiisp.base.BaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiStore {
    private static volatile ApiStore instance = null;
    private static Retrofit retrofit;

    private ApiStore() {

    }

    public static ApiStore getInstance() {
        if (instance == null) {
            synchronized (ApiStore.class) {
                if (instance == null) {
                    instance = new ApiStore();
                }
            }
        }
        return instance;

    }

    public ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

    public ApiService getApiServiceString() {
        return getRetrofitString().create(ApiService.class);
    }

    public ApiService getApiServiceURl() {
        return getRetrofitURL().create(ApiService.class);
    }

    public OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
//                builder.addInterceptor(new RequestInterceptor());
            }
            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApp.app));
            builder.cookieJar(cookieJar);
            OkHttpClient okHttpClient = builder.build();
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /*    public void setCookies(OkHttpClient.Builder builder) {
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            builder.cookieJar(new JavaNetCookieJar(cookieManager));
        }*/
    public Retrofit getRetrofit() {
/*        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApp.app));
        builder.cookieJar(cookieJar);*/
 /*       builder .certificatePinner(new CertificatePinner.Builder()
                .add("sbbic.com", "sha1/C8xoaOSEzPC6BgGmxAt/EAcsajw=")
                .add("closedevice.com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
                .build());
*/

        //        builder.interceptors().add(new ReceivedCookiesInterceptor(BaseApp.app));
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(getUnsafeOkHttpClient())
                    .baseUrl(UrlConstants.RequestUrl.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //可以接收自定义的Gson，当然也可以不传
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public Retrofit getRetrofitString() {
        return new Retrofit.Builder().client(getUnsafeOkHttpClient())
                .baseUrl(UrlConstants.RequestUrl.BASE_URL)
                .build();
    }

    public Retrofit getRetrofitURL() {
        return new Retrofit.Builder().client(getUnsafeOkHttpClient())
                .baseUrl(UrlConstants.RequestUrl.MP3_URL)
                .build();
    }


    /**
     * java bean 转化json数据  序列化
     * 解析反序列化
     *
     * @return
     **/

    public Gson getGson() {

        return new GsonBuilder()
                .serializeNulls()//允许序列化反序列化为null
                .create();
    }

    /**
     * 请求拦截器，修改请求header
     */
    private class RequestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
//                    .addHeader("Content-Type", "text/html; charset=UTF-8")
////                    .addHeader("Accept-Encoding", "*")
//                    .addHeader("Connection", "keep-alive")
//                    .addHeader("Accept", "*/*")
//                    .addHeader("Access-Control-Allow-Origin", "*")
//                    .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
//                    .addHeader("Vary", "Accept-Encoding")
//                    .addHeader("Cookie", "add cookies here")
                    .addHeader("Connection","close")
                    .build();

            Log.e("Presenter", "request:" + request.toString());
            Log.e("Presenter", "request headers:" + request.headers().toString());

            return chain.proceed(request);
        }
    }


}
