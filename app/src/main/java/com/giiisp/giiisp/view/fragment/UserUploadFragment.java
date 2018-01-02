package com.giiisp.giiisp.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.PicUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.MultipleItemQuickAdapter;
import com.giiisp.giiisp.widget.ScreenPopupWindow;
import com.giiisp.giiisp.widget.StepsView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * 资料上传
 * Created by Thinkpad on 2017/5/8.
 */

public class UserUploadFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_user_icAndpassport)
    TextView icAndpassport;
    @BindView(R.id.iv_user_icimg)
    ImageView icimg;
    @BindView(R.id.tv_user_zzAndzd)
    TextView zzAndzd;
    @BindView(R.id.iv_user_stuimg)
    ImageView stuimg;
    @BindView(R.id.iv_user_paperimg)
    ImageView paperimg;
    private Dialog dialog;
    private File file;
    private String param;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_userload;
    }

    @Override
    public void initView() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_submission, R.id.fl_user_icAndpassport, R.id.fl_user_icimg, R.id.fl_user_zzAndzd, R.id.fl_user_stuimg, R.id.fl_user_paperimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submission:
                getVerifiedActivity().getViewPagerVerified().setCurrentItem(2);
                break;
            case R.id.fl_user_icAndpassport:
                icAndpassport();
                break;
            case R.id.fl_user_icimg:
                param = "icimg";
                showDialog();
                break;
            case R.id.fl_user_zzAndzd:
                zzAndzd();
                break;
            case R.id.fl_user_stuimg:
                param = "stuimg";
                showDialog();
                break;
            case R.id.fl_user_paperimg:
                param = "paperimg";
                showDialog();
                break;
        }
    }

    private void icAndpassport() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.icAndpassport_verified)).setIcon(
                R.mipmap.ic_launcher).setNegativeButton(getString(R.string.icAndpassport_one), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                icAndpassport.setText(getString(R.string.icAndpassport_one));
                MultipartBody.Part part = MultipartBody.Part.createFormData("icAndpassport", "1");
                ((VerifiedActivity) getActivity()).parts.add(part);
            }
        });
        builder.setPositiveButton(getString(R.string.icAndpassport_two),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        icAndpassport.setText(getString(R.string.icAndpassport_two));
                        MultipartBody.Part part = MultipartBody.Part.createFormData("icAndpassport", "2");
                        ((VerifiedActivity) getActivity()).parts.add(part);
                    }
                });
        builder.show();
    }

    private void zzAndzd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.zzAndzd_verified)).setIcon(
                R.mipmap.ic_launcher).setNegativeButton(getString(R.string.zzAndzd_one), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                zzAndzd.setText(getString(R.string.zzAndzd_one));
                MultipartBody.Part part = MultipartBody.Part.createFormData("zzAndzd", "3");
                ((VerifiedActivity) getActivity()).parts.add(part);
            }
        });
        builder.setPositiveButton(getString(R.string.zzAndzd_two),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zzAndzd.setText(getString(R.string.zzAndzd_two));
                        MultipartBody.Part part = MultipartBody.Part.createFormData("zzAndzd", "4");
                        ((VerifiedActivity) getActivity()).parts.add(part);
                    }
                });
        builder.show();
    }

    private void showDialog() {
        View view = mActivity.getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
        Button butCamera = (Button) view.findViewById(R.id.but_camera);
        Button butAlbum = (Button) view.findViewById(R.id.but_album);
        Button butCancel = (Button) view.findViewById(R.id.but_cancel);
        butCamera.setOnClickListener(this);
        butAlbum.setOnClickListener(this);
        butCancel.setOnClickListener(this);
        dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        if (window != null) {
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.x = 0;
            wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
            // 以下这两句是为了保证按钮可以水平满屏
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            // 设置显示位置
            dialog.onWindowAttributesChanged(wl);
        }
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_camera:
                new RxPermissions(getActivity()).requestEach(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(@NonNull Permission permission) throws Exception {
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消照相机授权");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出照相机的授权操作,请在设置中手动开启");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.i("--->>", "onError", throwable);
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                    File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download");
                                    if (!folder.exists()) {
                                        folder.mkdirs();
                                    }
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", param + ".jpg");
                                        Uri photoUri = FileProvider.getUriForFile(context, "com.giiisp.giiisp.fileprovider", file);
                                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(takeIntent, 2);
                                    } else {
                                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", param + ".jpg");
                                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                        //指定照片存储路径
                                        //                intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                        startActivityForResult(intent2, 2);//采用ForResult打开
                                    }

                                    dialog.dismiss();
                                } else {
                                    Utils.showToast("外部储存没有挂载");
                                }
                            }
                        });

                break;
            case R.id.but_album:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
                break;
            case R.id.but_cancel:
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("--->>", "onActivityResult:requestCode: " + requestCode + "--resultCode:" + resultCode);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    file = new File(PicUtils.getRealPathFromUri(context, data.getData()));

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData(param, file.getName(), requestBody);
                    ((VerifiedActivity) getActivity()).parts.add(part);

                    imageLoad();
                } else {
                    Utils.showToast(getString(R.string.tishi_meitu));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", param + ".jpg");
                    if (file.exists()) {

                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData(param, file.getName(), requestBody);
                        ((VerifiedActivity) getActivity()).parts.add(part);

                        imageLoad();
                    } else {
                        Utils.showToast(getString(R.string.tishi_meitu));
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void imageLoad() {
        switch (param) {
            case "icimg":
                ImageLoader.getInstance().displayImage((BaseActivity) getActivity(), file, icimg);
                break;
            case "stuimg":
                ImageLoader.getInstance().displayImage((BaseActivity) getActivity(), file, stuimg);
                break;
            case "paperimg":
                ImageLoader.getInstance().displayImage((BaseActivity) getActivity(), file, paperimg);
                break;
        }
    }

}
