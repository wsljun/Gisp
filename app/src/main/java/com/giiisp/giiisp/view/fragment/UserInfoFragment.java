package com.giiisp.giiisp.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.SettingActivity;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.ProgressPopupWindow;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 用户的详细信息
 * Created by Thinkpad on 2017/5/4.
 */

public class UserInfoFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.tv_user_mechanism)
    TextView tvUserMechanism;
    @BindView(R.id.tv_user_position)
    TextView tvUserPosition;
    @BindView(R.id.tv_user_resume)
    TextView tvUserResume;
    @BindView(R.id.tv_contact_information)
    TextView tvContactInformation;
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_professional)
    TextView tvUserProfessional;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_web)
    TextView tvUserWeb;
    @BindView(R.id.fl_user_email)
    FrameLayout flUserEmail;

    @BindView(R.id.fl_user_phone)
    FrameLayout flUserPhone;
    //    Unbinder unbinder;
    private Dialog dialog;
    private File file;
    private Uri imageUri;
    private int type;
    private UploadManager uploadManager = new UploadManager();
    private String qNtoken;
    private String sex;
    private String changeSex;
    private String changeName;
    private String name;
    ProgressPopupWindow progressPopupWindow;
    private String imagUrl = "";
    private String PageType;

    public static UserInfoFragment newInstance(String param1, String param2) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (tvUserName == null)
            return;

//        if (!TextUtils.isEmpty(entity.getToken())) {
//            qNtoken = entity.getToken();
//            Log.i("--->>", "onSuccess: " + qNtoken);
//        } else
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
        if (entity.getResult() == 1) {
            Log.i("--->>", "onSuccess: " + type);
            switch (type) {
                case 1:
                    if (getActivity() != null && getActivity() instanceof SettingActivity) {
                        ((SettingActivity) getActivity()).initView();
                    }
                    if (getActivity() != null && getActivity() instanceof FragmentActivity) {
                        Utils.showToast(entity.getInfo());
                        getActivity().setResult(203);
                        getActivity().finish();
                    }
                    break;
                case 2:
                    if (getActivity() != null && getActivity() instanceof SettingActivity) {
                        ((SettingActivity) getActivity()).initView();
                    }
                    ImageLoader.getInstance().displayCricleImage((BaseActivity) getActivity(), file.getAbsoluteFile(), ivUserIcon);
                    break;
                case 3:
                    if (entity instanceof UserInfoEntity) {
                        UserInfoEntity userInfoEntity = (UserInfoEntity) entity;
                        if (userInfoEntity.getUserInfo() != null) {
                            onMessageUserInfo(userInfoEntity);
                        }
                    }
                    break;
            }
        } else {
            Utils.showToast(entity.getInfo());
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_edituserinfo;
    }

    @Override
    public void initView() {
        tvRight.setText(R.string.save);
        tvTitle.setText(R.string.set_data);
        name = tvUserName.getText() + "";
        sex = tvUserSex.getText() + "";
    }

    @Override
    public void initData() {
        super.initData();

        PageType = getArguments().getString("1005");
        if (PageType == null) {
            PageType = "";
        }
        String string = getArguments().getString("1006");
        switch (PageType) {
            case "verified_edit_info":
                type = 3;
                ArrayMap<String, Object> userMap = new ArrayMap<>();
                userMap.put("uid", uid);
                if (presenter != null)
                    presenter.getUserInfoData(userMap);
                break;
            case "setting_edit_info":
                break;
        }
//        presenter.getQNTokenData(uid);
        progressPopupWindow = new ProgressPopupWindow((BaseActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_back, R.id.fl_user_icon, R.id.tv_right, R.id.fl_user_professional, R.id.fl_user_name, R.id.fl_user_email, R.id.fl_user_phone, R.id.fl_user_sex, R.id.fl_user_mechanism, R.id.fl_user_position, R.id.fl_user_resume,R.id.fl_user_web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                switch (PageType) {
                    case "verified_edit_info":
                        getActivity().setResult(201);
                        getActivity().finish();
                        break;
                    case "setting_edit_info":
                        getSettingActivity().getVpLogin().setCurrentItem(0, false);
                        break;
                }
                break;
            case R.id.fl_user_icon:
                showDialog();
                break;
            case R.id.tv_right:
                type = 1;
                changeName = tvUserName.getText() + "";
                changeSex = tvUserSex.getText() + "";

                ArrayMap<String, Object> map = new ArrayMap<>();
                if (!changeSex.equals(sex)) {
                    map.put("sex", changeSex.equals(getString(R.string.man)) ? 1 : 2);
                }
                if (!changeName.equals(name)) {
                    map.put("realName", changeName);
                }

                String phone = tvUserPhone.getText().toString();
                String email = tvUserEmail.getText().toString();
                String organization = tvUserMechanism.getText().toString();
                String position = tvUserPosition.getText().toString();
                String department = tvUserResume.getText().toString();
                String web =tvUserWeb.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    map.put("phone", phone);
                }else{
                    Utils.showToast(R.string.phone_verified);
                    return;
                }
                if (!TextUtils.isEmpty(email)) {
                    map.put("email", email);
                }
                if(!TextUtils.isEmpty(organization)){
                    map.put("organization", organization);
                }
                if(!TextUtils.isEmpty(position)){
                    map.put("position", position);
                }
                if(!TextUtils.isEmpty(department)){
                    map.put("department", department);
                }
                if(!TextUtils.isEmpty(web)){
                    map.put("web", web);
                }
                if (map.size() > 0) {
                    map.put("id", uid);
                    progressPopupWindow.showPopupWindow();
                    presenter.getUpdateUserInfoData(map);
                }

                break;
            case R.id.fl_user_sex:
                inputSexDialog();
                break;
            case R.id.fl_user_name:
                inputTitleDialog(tvUserName, getString(R.string.real_name));
                break;
            case R.id.fl_user_mechanism:
                inputTitleDialog(tvUserMechanism, getString(R.string.subordinate_institution));
                break;
            case R.id.fl_user_position:
                inputTitleDialog(tvUserPosition, getString(R.string.user_position));
                break;
            case R.id.fl_user_resume:
                inputTitleDialog(tvUserResume, getString(R.string.user_resume));
                break;
            case R.id.fl_user_phone:
                inputTitleDialog(tvUserPhone, getString(R.string.enter_phone));
                break;
            case R.id.fl_user_email:
                inputTitleDialog(tvUserEmail, getString(R.string.email));
                break;
            case R.id.fl_user_professional:
                Utils.showToast(R.string.web_editing_data);
                break;
            case R.id.fl_user_web:
                inputTitleDialog(tvUserWeb, getString(R.string.edit_user_web));
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageUserInfo(UserInfoEntity userInfoEntity) {
        if (tvUserName == null && userInfoEntity == null)
            return;
        if (userInfoEntity.getUserInfo() == null) {
            return;
        }
        Log.i("--->>", "onMessageUserInfo: " + userInfoEntity);
        String avatar = userInfoEntity.getUserInfo().getAvatar();
        if (!imagUrl.equals(avatar))
            ImageLoader.getInstance().displayCricleImage((BaseActivity) getActivity(), avatar, ivUserIcon);
        imagUrl = avatar + "";
        tvUserName.setText(userInfoEntity.getUserInfo().getRealName());
        tvUserSex.setText(userInfoEntity.getUserInfo().getSex()==1 ? getString(R.string.man) : userInfoEntity.getUserInfo().getSex()==2 ? getString(R.string.gril) : getString(R.string.unknown));
        if (Utils.checkMobileNumber(userInfoEntity.getUserInfo().getMobile())) {
            tvUserPhone.setText(userInfoEntity.getUserInfo().getMobile());
        }
        if (Utils.checkEmail(userInfoEntity.getUserInfo().getEmail())) {
            tvUserEmail.setText(userInfoEntity.getUserInfo().getEmail().trim());
        }
        if (userInfoEntity.getAuthen()== null) {
            return;
        }
        if(TextUtils.isEmpty(userInfoEntity.getAuthen().getOrganization())){
            tvUserMechanism.setText(userInfoEntity.getAuthen().getOrganization());
        }
        if(TextUtils.isEmpty(userInfoEntity.getAuthen().getOrganization())){
            tvUserPosition.setText(userInfoEntity.getAuthen().getPosition());
        }

        if(TextUtils.isEmpty(userInfoEntity.getAuthen().getOrganization())){
            tvUserResume.setText(userInfoEntity.getAuthen().getDepartment());
        }
        if(TextUtils.isEmpty(userInfoEntity.getAuthen().getOrganization())){
            tvUserWeb.setText(userInfoEntity.getUserInfo().getWeb());
        }
    }

    private void inputTitleDialog(final TextView view, final String name) {

        final EditText inputServer = new EditText(getContext());
        inputServer.setPadding(50, 50, 50, 50);
        inputServer.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.input) + name).setIcon(
                R.mipmap.ic_launcher).setView(inputServer).setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        switch (name) {
                            case "手机号码":
                                if (Utils.checkMobileNumber(inputName)) {
                                    view.setText(inputName);
                                } else {
                                    Utils.showToast("请输入正确的手机号码");
                                }

                                break;
                            case "邮箱":
                                if (Utils.checkEmail(inputName)) {
                                    view.setText(inputName);
                                } else {
                                    Utils.showToast("请输入正确的邮箱");
                                }
                                break;
                            case "真实姓名":
                                view.setText(inputName);
                                break;
                            case "所属机构":
                                view.setText(inputName);
                                break;
                            case "职称":
                                view.setText(inputName);
                                break;
                            case "履历":
                                view.setText(inputName);
                                break;
                            case "个人网址":
                                view.setText(inputName);
                                break;
                        }


                    }
                });
        builder.show();
    }

    private void inputSexDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.sex_info)).setIcon(
                R.mipmap.ic_launcher).setNegativeButton(getString(R.string.man), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvUserSex.setText(getString(R.string.man));
            }
        });
        builder.setPositiveButton(getString(R.string.gril),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tvUserSex.setText(getString(R.string.gril));
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("--->>", "onActivityResult:requestCode: " + requestCode + "--resultCode:" + resultCode);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download",
                            "GiiispHead.jpg");
                    if (temp.exists()) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            cropPhoto(FileProvider.getUriForFile(context, "com.giiisp.giiisp.fileprovider", temp));//裁剪图片
                        } else {

                            cropPhoto(Uri.fromFile(temp));//裁剪图片
                        }
                    } else {
                        Utils.showToast("图片可能已经移位或删除");
                    }
                }
                break;
            case 3:
                if (file == null)
                    return;
                if (file.exists()) {
                    postPic(file);
                } else {
                    Utils.showToast("图片可能已经移位或删除");
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传头像
     *
     * @param file
     */
    public void postPic(File file) {
        if (TextUtils.isEmpty(file.getAbsolutePath()))
            return;
        progressPopupWindow.showPopupWindow();
//        String simpe = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        String upkey = UUID.randomUUID().toString() + simpe + "_" + Utils.fileName(file.getAbsolutePath());
//        uploadManager.put(file, upkey, qNtoken, new UpCompletionHandler() {
//            public void complete(String key1, ResponseInfo rinfo, JSONObject response) {
//                Log.i("qiniu", key1 + ",\r\n " + rinfo + ",\r\n " + response);
//
//                if (response == null) {
//                    progressPopupWindow.dismiss();
//                    return;
//                }
//                String key = response.optString("key");
//                if (TextUtils.isEmpty(key)) {
//                    progressPopupWindow.dismiss();
//                    return;
//                }
//                type = 2;
//                ArrayMap<String, Object> map = new ArrayMap<>();
//                map.put("path", UrlConstants.RequestUrl.QN_ADDRESS + key);
////                map.put("token", token);
//                map.put("uid", uid);
//                presenter.getUpdatePortraitData(map);
//                Log.i("--->>", "complete: " + key);
//            }
//
//        }, null);
        type = 2;
//        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("uid", uid);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("image", file.getName(), imageBody);

        presenter.getUpdatePortraitData(uid, imageBodyPart);

    }


    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download",
                "GiiispCropHead.jpg");

        imageUri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //不启用人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 3);
    }

    /**
     * 设置图片保存路径
     *
     * @return
     */
    private File getImageStoragePath(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            long sTime = System.currentTimeMillis();
            SimpleDateFormat simpe = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String format = simpe.format(new Date());
            return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), format + sTime + "GiiispHead.jpg");
        }
        return null;
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
                                        Uri photoUri = FileProvider.getUriForFile(context, "com.giiisp.giiisp.fileprovider", new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download",
                                                "GiiispHead.jpg"));
                                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(takeIntent, 2);
                                    } else {
                                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download",
                                                "GiiispHead.jpg")));
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
    public void onFailure(String msg, Exception ex) {
        super.onFailure(msg, ex);
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
    }

}
