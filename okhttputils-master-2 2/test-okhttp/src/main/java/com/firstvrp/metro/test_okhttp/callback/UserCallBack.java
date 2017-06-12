package com.firstvrp.metro.test_okhttp.callback;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.firstvrp.metro.test_okhttp.MainActivity;
import com.firstvrp.metro.test_okhttp.entity.User;
import com.firstvrp.metro.test_okhttp.http.HttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by arthur on 2017/6/7.
 */

public class UserCallBack {
    private Context context;
    private List<User> mList = new ArrayList<>();
    private Map<String, String> map = new HashMap<String, String>();
    private User mUser;

    public UserCallBack(Context context) {
        this.context = context;
    }

    /**
     * get方法
     * @param listener
     */
    public void getUser(final Listener listener) {
        String url = String.format("api/PlaceType");
        HttpClient.get(url, 100, new ListUserCall() {
            @Override
            public void onError(Call call, Exception e, int id) {

               /* Log.i("Exception",id+"");
                Log.i("Exception",call.toString());
                Log.i("Exception",e.toString());*/
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(List<User> response, int id) {

               /* Log.i("response",response.toString());*/
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }

        });
    }

    /**
     * post方法 参数为Map值
     * @param user
     * @param listener
     */
    public void postUser(User user, final Listener listener) {

        String url = String.format("api/UnitsInfo");
        Gson gson = new Gson();

        Map<String, String> params = HttpClient.convert2Map(user);

        HttpClient.post(url, params, new UserCall() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(User response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }

    /**
     * post方法 参数为obj值
     * @param listener
     * @param user
     */
    public void postUser(final Listener listener, User user){
        String url = String.format("api/UnitsInfo");
        HttpClient.post(url, user, new UserCall() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(User response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }


    /**
     * postFile
     * @param file
     * @param listener
     */
    public void postFile(File file , final Listener listener){
        String url = String.format("api/UnitsInfo");
        //file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpClient.postFile(url, file, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }


    /**
     * post
     * @param str1
     * @param str2
     * @param listener
     */
    public void post(String str1,String str2,final Listener listener){
        String url = String.format("api/UnitsInfo");
        HttpClient.post(url, str1, str2, new UserCall() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(User response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });

    }

    /**
     * getImage
     * @param connTimeOut
     * @param readTimeOut
     * @param witeTimeOut
     * @param listener
     */
    public void getImage(int connTimeOut, int readTimeOut, int witeTimeOut,final Listener listener){
        String url = String.format("api/UnitsInfo");
        // 测试 url = "http://images.csdn.net/20150817/1.jpg";
        connTimeOut =20000;
        readTimeOut = 20000;
        witeTimeOut = 20000;
        HttpClient.getImage(url, context, connTimeOut, readTimeOut, witeTimeOut, new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(Bitmap response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }


    /**
     * uploadFile
     * @param name
     * @param fileName
     * @param file
     * @param params
     * @param headers
     * @param listener
     */
    public void uploadFile(String name ,String fileName,File file ,
                           Map<String,String> params,Map<String, String> headers,
                           final Listener listener){
        String url = String.format("api/UnitsInfo");
        file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        //Map<String, String> params = new HashMap<>();
        params.put("name", "admin");
        params.put("password", "123456");

        //Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        HttpClient.uploadFile(url, name, fileName, file, params, headers, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }

    /**
     * multiFileUpload
     * @param params
     * @param list
     * @param listener
     */
    public void multiFileUpload(Map<String,String> params,
                                List<PostFormBuilder.FileInput> list,final Listener listener){
        String url = String.format("api/UnitsInfo");
        HttpClient.multiFileUpload(url,context,params,list, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }


    /**
     * downloadFile
     * @param url
     * @param fileName
     * @param listener
     */
    public void downloadFile(String url,String fileName,final Listener listener){
        //url = "https://github.com/hongyangAndroid/okhttp-utils/blob/master/okhttputils-2_4_1.jar?raw=true";

        HttpClient.downloadFile(url, new FileCallBack(
                Environment.getExternalStorageDirectory().getAbsolutePath(),fileName) {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }

            @Override
            public void onResponse(File response, int id) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }
        });
    }



    /**
     * 回掉监听事件
     */
    public interface Listener {
        void onSuccess(List<User> list);
        void onFailure();
        void onSuccess(User user);
        void onSuccess(String str);
        void onSuccess(Bitmap bitmap);
        void onSuccess(File file);
    }

    /**
     * 回调抽象类
     */
    abstract class UserCall extends Callback<User> {
        @Override
        public User parseNetworkResponse(Response response, int id) throws IOException {
            String string = response.body().string();
            User user = new Gson().fromJson(string, User.class);
            return user;
        }
    }

    /**
     * 回调抽象类
     */
    abstract class ListUserCall extends Callback<List<User>> {
        @Override
        public List<User> parseNetworkResponse(Response response, int id) throws IOException {
            String string = response.body().string();
            Log.i("response", string);
            List<User> user = new Gson().fromJson(string, new TypeToken<List<User>>() {
            }.getType());

            //List<User> user = new Gson().fromJson(string, List.class);
            return user;
        }
    }


}
