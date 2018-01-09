package com.giiisp.giiisp.view.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.PicUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.ProgressPopupWindow;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.giiisp.giiisp.base.BaseActivity.emailauthen;
import static com.giiisp.giiisp.base.BaseActivity.uid;

import static com.giiisp.giiisp.base.BaseActivity.isVip;


/**
 * 邮箱认证
 * Created by Thinkpad on 2017/8/18.
 */

public class MailboxAuthenticationFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_enter_email)
    EditText edEnterEmail;
    @BindView(R.id.iv_email_tu)
    ImageView emailTu;
    private Dialog dialog;
    private File file;
    private MultipartBody.Part part;
    ProgressPopupWindow progressPopupWindow;

    @Override
    public int getLayoutId() {
        return R.layout.layout_mailbox_authentication;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.mailbox_authentication);
        progressPopupWindow = new ProgressPopupWindow((BaseActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.fl_email_tu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getActivity().finish();
                break;
            case R.id.tv_submit:
                String email = edEnterEmail.getText() + "";
                String trim = email.trim();
                if (!Utils.checkEmail(trim)) {
                    Utils.showToast(R.string.email_not_correct);
                } else if (part == null) {
                    Utils.showToast(R.string.email_not_tu);
                } else {
                    ArrayMap<String, Object> map = new ArrayMap<>();
                    map.put("email", trim);
                    map.put("uid", uid);
                    presenter.getAuthenUserlData(trim, uid, part);
                    progressPopupWindow.showPopupWindow();
                }
                break;
            case R.id.fl_email_tu:
                showDialog();
                break;
        }
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
    public void onSuccess(BaseEntity entity) {
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
        if (entity.getResult() == 1) {
            initDialog(entity.getInfo(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (getActivity() != null)
                        getActivity().finish();
                }
            });

        } else {
            //            Utils.showToast(entity.getInfo(),false);
            initDialog(entity.getInfo(), null);
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        super.onFailure(msg, ex);
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
    }

    private void initDialog(String string, DialogInterface.OnClickListener listener) {
        AlertDialog dialog = new AlertDialog.Builder(getContext()).setMessage("  ").setPositiveButton(R.string.confirm, listener).show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextIsSelectable(true);
            textView.setTextSize(13);
            textView.setText(string);
            dialog.setCancelable(false);
            dialog.create();
        }
     /*   AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton(R.string.confirm,
               null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setMessage(string);
        TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
        textView.setTextIsSelectable(true);
        textView.setTextSize(12);
        builder.setCancelable(false);
        builder.show();*/
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", "emailImage.jpg");
                                        Uri photoUri = FileProvider.getUriForFile(context, "com.giiisp.giiisp.fileprovider", file);
                                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(takeIntent, 2);
                                    } else {
                                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", "emailImage.jpg");
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
                    part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                    ImageLoader.getInstance().displayImage((BaseActivity) getActivity(), file, emailTu);
                } else {
                    Utils.showToast(getString(R.string.tishi_meitu));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download", "emailImage.jpg");
                    if (file.exists()) {
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                        part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                        ImageLoader.getInstance().displayImage((BaseActivity) getActivity(), file, emailTu);
                    } else {
                        Utils.showToast(getString(R.string.tishi_meitu));
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
