package com.anychat.aiselfrecordcompontsdemo.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anychat.aiselfrecordcompontsdemo.R;
import com.anychat.aiselfrecordcompontsdemo.logic.ApiManager;
import com.anychat.aiselfrecordcompontsdemo.ui.view.BRBusinessWebView;
import com.anychat.aiselfrecordcompontsdemo.utils.AnyChatJSBridge;
import com.anychat.aiselfrecordcompontsdemo.utils.EmptyUtils;
import com.anychat.aiselfrecordcompontsdemo.utils.SharedPreferencesUtils;
import com.anychat.aiselfrecordsdk.config.BusinessData;
import com.anychat.aiselfrecordsdk.util.Base64BitmapUtil;
import com.anychat.aiselfrecordsdk.util.FileUtils;
import com.anychat.aiselfrecordsdk.util.SDKLogUtils;
import com.anychat.aiselfrecordsdk.util.business.StringUtil;
import com.anychat.imagepicker.ImagePicker;
import com.anychat.imagepicker.cardcamera.IDCardCamera;
import com.bairuitech.anychat.util.AnyChatImageUtils;
import com.bairuitech.anychat.util.json.JSONObject;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class H5Activity extends AppCompatActivity implements View.OnClickListener, AnyChatJSBridge.OcrPictureTypeOnClick,BRBusinessWebView.dealData{

    private BRBusinessWebView mBRBusinessWebView;
    public static final int REQUEST_SELECT_IMAGES_CODE = 0x01;//选择图片返回码
    private String url = "";
    AnyChatJSBridge anyChatJSBridge;
    private Toast toast;
    private String type = "face";
//   private String url = "file:///android_asset/HTML/tel.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);


        String h5Url = SharedPreferencesUtils.get(this, SharedPreferencesUtils.H5_URL,
                "");
        url = h5Url;
        Log.e("H5Activity h5Url:",url);

        //设置状态栏颜色
        ImmersionBar.with(this).statusBarDarkFont(true).statusBarDarkFont(true).statusBarColorInt(Color.WHITE).titleBarMarginTop(R.id.welcome_ly).init();

        mBRBusinessWebView = (BRBusinessWebView) findViewById(R.id.webview);
        mBRBusinessWebView.initConfig();
        mBRBusinessWebView.loadUrl(url);
        mBRBusinessWebView.setEvent(this);
        anyChatJSBridge = new AnyChatJSBridge(this, mBRBusinessWebView);
        anyChatJSBridge.setEvent(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.title_left_img_btn) {
            finish();
        }
    }
    public static Uri getImageContentUri(Context context, java.io.File imageFile) {

        String filePath = imageFile.getAbsolutePath();

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,

                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",

                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {

            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));

            Uri baseUri = Uri.parse("content://media/external/images/media");

            return Uri.withAppendedPath(baseUri, "" + id);

        } else {

            if (imageFile.exists()) {

                ContentValues values = new ContentValues();

                values.put(MediaStore.Images.Media.DATA, filePath);

                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            } else {

                return null;

            }

        }

    }

    /**
     * 选择图片
     *
     * @param cameraTakeType 设置拍照方式（自定义拍证件照还是原生拍照） 1身份证正面 2身份证反面 其他表示原生拍照
     */
    public void selectImage(int cameraTakeType) {
        ImagePicker.getInstance()
                .setTitle("选择图片")//设置标题
                .setMaxCount(1)//设置可选择数量
                .showCamera(true)//设置是否显示拍照按钮
                .setCameraTakeType(cameraTakeType)//设置拍照方式（自定义拍证件照还是原生拍照） 1身份证正面 2身份证反面 其他表示原生拍照
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .start(this, REQUEST_SELECT_IMAGES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }
            final ArrayList<String> images = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            if (images != null && !images.isEmpty()) {

                final Bitmap bitmap = BitmapFactory.decodeFile(images.get(0));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap.getByteCount() / 1024 / 1024 > 1) {
                            Bitmap bitmapPrs = AnyChatImageUtils.compressScale(bitmap);
                            int degree = PictureUtils.readPicDegree(images.get(0));
                            Bitmap finImage = PictureUtils.rotateBitmap(degree,bitmapPrs);
                            final String faceCompareImage = Base64BitmapUtil.bitmapToBase64(finImage);
//                            final String path = FileUtils.saveBitmap(FileUtils.getDiskCacheDir(getApplication()), "PassPic" + new Date().getTime(), finImage);
                            if (faceCompareImage == null) {
                                SDKLogUtils.log("faceCompareImage  == null", "文件转换失败");
                                if (finImage != null && !finImage.isRecycled()) {
                                    finImage.recycle();
                                    finImage = null;
                                    SDKLogUtils.log("compressBitmap.recycle()");
                                }
                                return;
                            } else {
                                if (finImage != null && !finImage.isRecycled()) {
                                    finImage.recycle();
                                    finImage = null;
                                    SDKLogUtils.log("compressBitmap.recycle()");
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject ocrPictureJsonObject = new JSONObject();
                                    ocrPictureJsonObject.put("type", type);
                                    ocrPictureJsonObject.put("content", faceCompareImage);
                                    anyChatJSBridge.receiveOcrPictureInfo(ocrPictureJsonObject.toString());

//                                    Uri[] results = new Uri[]{Uri.parse(path)};
//                                    results[0]=getImageContentUri(H5Activity.this,new File(path));
//                                    mBRBusinessWebView.setChoseFile(results);
                                }
                            });
                        } else {
                            int degree = PictureUtils.readPicDegree(images.get(0));
                            Bitmap finImage = PictureUtils.rotateBitmap(degree,bitmap);
                            final String faceCompareImage = Base64BitmapUtil.bitmapToBase64(finImage);
                            if (finImage != null && !finImage.isRecycled()) {
                                finImage.recycle();
                                finImage = null;
                                SDKLogUtils.log("finImage.recycle()");
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    JSONObject ocrPictureJsonObject = new JSONObject();
                                    ocrPictureJsonObject.put("type", type);
                                    ocrPictureJsonObject.put("content", faceCompareImage);
                                    anyChatJSBridge.receiveOcrPictureInfo(ocrPictureJsonObject.toString());
//                                    Uri[] results = new Uri[]{Uri.parse(images.get(0))};
//                                    results[0]=getImageContentUri(H5Activity.this,new File(images.get(0)));
//                                    mBRBusinessWebView.setChoseFile(results);
                                }
                            });
                        }

                    }
                }).start();




            }
        } else {
//            mBRBusinessWebView.onCancel();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onError() {
        showToast(getApplicationContext(),"加载失败");
        mBRBusinessWebView.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1000);

    }

    @Override
    public void onSelectImageFront() {
        type = "face";
        selectImage(IDCardCamera.TYPE_IDCARD_FRONT);//选择身份证正面
    }
    @Override
    public void onSelectImageBack() {
        type = "back";
        selectImage(IDCardCamera.TYPE_IDCARD_BACK);//选择身份证反面
    }

    @Override
    public void onSelectImageBankCard() {
        type = "bankCard";
        selectImage(IDCardCamera.TYPE_BANK_CARD);//选择银行卡
    }

    @Override
    public void back() {
       finish();
    }


    public void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if(mBRBusinessWebView!=null&&mBRBusinessWebView.canGoBack()){
                mBRBusinessWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBRBusinessWebView.removeCallbacks(null);
        anyChatJSBridge.release();
        anyChatJSBridge = null;
    }
}
