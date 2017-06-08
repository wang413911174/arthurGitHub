package com.firstvrp.metro.test_okhttp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstvrp.metro.test_okhttp.callback.UserCallBack;
import com.firstvrp.metro.test_okhttp.entity.User;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.text_okhttp);
        mTextView = (TextView) findViewById(R.id.textview);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        UserCallBack mUserCallBack = new UserCallBack(MainActivity.this);

        //get
       /* mUserCallBack.getUser(new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {
                Log.i("list",list.toString());
                mTextView.setText(list.toString());
            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }
        });*/


        //post
        /*User user = new User("admin","password");

        mUserCallBack.postUser(user, new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }

            @Override
            public void onSuccess(User user) {
                mTextView.setText(user.toString());
            }
        });*/


        //post
        /*User user = new User("admin","password");

        mUserCallBack.postUser(new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }

            @Override
            public void onSuccess(User user) {
                mTextView.setText(user.toString());
            }

            @Override
            public void onSuccess(String str) {

            }
        },user);*/

        //postfile
        /*File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");

        mUserCallBack.postFile(file, new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onSuccess(String str) {
                mTextView.setText(str);
            }
        });*/

        //post
       /* User user = new User("admin","password");
        mUserCallBack.post(user.getName(),user.getPassword(), new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }

            @Override
            public void onSuccess(User user) {
                mTextView.setText(user.toString());
            }

            @Override
            public void onSuccess(String str) {

            }

            @Override
            public void onSuccess(Bitmap bitmap) {

            }
        });*/

        //getImage
       /* mUserCallBack.getImage(20000,20000,20000, new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }

            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onSuccess(String str) {

            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        });*/

        //downloadFile
        String url = "http://downmobile.kugou.com/Android/KugouPlayer/7840/KugouPlayer_219_V7.8.4.apk";
        String fileName = "kugou.apk";
        mUserCallBack.downloadFile(url,fileName,new UserCallBack.Listener() {
            @Override
            public void onSuccess(List<User> list) {

            }

            @Override
            public void onFailure() {
                mTextView.setText("onFailure");
            }

            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onSuccess(String str) {

            }

            @Override
            public void onSuccess(Bitmap bitmap) {

            }

            @Override
            public void onSuccess(File file) {
                mTextView.setText(file.getName());
            }
        });

    }
}