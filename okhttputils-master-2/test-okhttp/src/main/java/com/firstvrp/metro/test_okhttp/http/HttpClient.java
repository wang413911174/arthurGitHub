package com.firstvrp.metro.test_okhttp.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firstvrp.metro.test_okhttp.MainActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by arthur on 2017/6/4.
 */

public class HttpClient {
    private static final String BASE_URL = "http://101.200.173.175:10004/";


    /**
     * get方法传递url，id==100 代表http；id=101 代表https
     * @param url
     * @param id
     * @param callback
     */
    public static void get(String url, int id, Callback callback) {
        //String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        //url = "http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        OkHttpUtils
                .get()
                .url(getAbsoluteUrl(url))
                .id(id)
                .build()
                .execute(callback);
    }

    /**
     * 通过传递params
     * @param url
     * @param params
     * @param callback
     */
    public static void post(String url, Map<String, String> params, Callback callback) {
        OkHttpUtils
                .post()//
                .url(getAbsoluteUrl(url))//
                //.addParams("username", "hyman")//
                //.addParams("password", "123")//
                .params(params)
                .build()//
                .execute(callback);
    }

    /**
     * 通过传递类
     * @param url
     * @param obj
     * @param callback
     */
    public static void post(String url, Object obj, Callback callback) {
        OkHttpUtils
                .postString()
                .url(getAbsoluteUrl(url))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(obj))
                .build()
                .execute(callback);

    }

    /**
     * PostFile
     * @param url
     * @param file
     * @param callback
     */
    public static void postFile(String url, File file, Callback callback) {
       /* file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            //Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        url = "user!postFile";*/
        OkHttpUtils
                .postFile()
                .url(getAbsoluteUrl(url))
                .file(file)
                .build()
                .execute(callback);


    }

    /**
     * post
     * @param url
     * @param str1
     * @param str2
     * @param callback
     */
    public static void post(String url, String str1, String str2, Callback callback) {
        OkHttpUtils//
                .post()//
                .url(getAbsoluteUrl(url))
                .addParams(str1, str2)////
                .build()//
                .execute(callback);
    }


    /**
     * getImage
     * @param url
     * @param context
     * @param connTimeOut
     * @param readTimeOut
     * @param witeTimeOut
     * @param callback
     */
    public static void getImage(String url, Context context, int connTimeOut, int readTimeOut, int witeTimeOut, Callback callback) {
        OkHttpUtils
                .get()
                .url(getAbsoluteUrl(url))
                .tag(context)
                .build()
                .connTimeOut(connTimeOut)
                .readTimeOut(readTimeOut)
                .writeTimeOut(witeTimeOut)
                .execute(callback);
    }

    /**
     * uploadFile
     * @param url
     * @param name
     * @param filename
     * @param file
     * @param params
     * @param headers
     * @param callback
     */
    public static void uploadFile(String url, String name, String filename,
                           File file, Map<String, String> params, Map<String, String> headers,
                           Callback callback) {
        OkHttpUtils.post()//
                .addFile(name, filename, file)//
                .url(getAbsoluteUrl(url))//
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(callback);
    }


    /**
     * multiFileUpload
     * @param url
     * @param params
     * @param list
     * @param callback
     */
    public static void multiFileUpload(String url,Context context, Map<String, String> params,
                                       List<PostFormBuilder.FileInput> list, Callback callback) {

        PostFormBuilder post = OkHttpUtils.post();

        for (PostFormBuilder.FileInput fileInput:
                list) {
            post.addFile(fileInput.key,fileInput.filename,fileInput.file);
            if (!fileInput.file.exists()) {
                Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        post.url(getAbsoluteUrl(url))
                .params(params)
                .build()
                .execute(callback);
    }


    /**
     * downloadFile
     * @param url
     * @param callback
     */
    public static void downloadFile(String url, Callback callback) {
         OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(callback);
    }


    /**
     * put
     * @param url
     * @param callback
     */
    public static void put(String url ,Callback callback){
        OkHttpUtils.put().
                url(url).
                requestBody("may be something").
                build().
                execute(callback);
    }

    /**
     * head
     * @param url
     * @param params
     * @param callback
     */
    public static  void head(String url,Map<String,String> params,Callback callback){
        OkHttpUtils.head()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     * 拼接url地址
     * @param relativeUrl
     * @return
     */
    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    /**
     * 清除Cookie
     */
    public static void clearSession()
    {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl)
        {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }


    /**
     * 获取obj中的所有方法
     * @param obj
     * @return
     */
    public static List<Method> getAllMethods(Object obj){
        List<Method> methods = new ArrayList<Method>();
        Class<?> clazz = obj.getClass();
        while(!clazz.getName().equals("java.lang.Object")){
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }
    /**
     * 将一个类用属性名为Key，值为Value的方式存入map
     * @param obj
     * @return
     */
    public static Map<String,String> convert2Map(Object obj){
        Map<String,String> map = new HashMap<String,String>();
        List<Method> methods = getAllMethods(obj);
        for(Method m:methods){
            String methodName = m.getName();
            if(methodName.startsWith("get")){
                //获取属性名  但是这里有逻辑问题，要求属性名必须规范驼峰标识，不然会错误。
                String propertyName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
                //propertyName  = propertyName.substring(0,1).toLowerCase()+propertyName.substring(1);
                try {
                    map.put(propertyName, (String) m.invoke(obj));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
