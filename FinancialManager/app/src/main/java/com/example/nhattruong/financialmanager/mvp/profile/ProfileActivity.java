package com.example.nhattruong.financialmanager.mvp.profile;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.example.nhattruong.financialmanager.utils.FileUtils;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.regex.Pattern;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity implements ProfileContract.View, View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.iv_user_avatar)
    CircleImageView ivUserAvatar;

    @BindView(R.id.tv_save)
    TextView tvSave;

    @BindView(R.id.tv_select_photo)
    TextView tvSelectPhoto;

    @BindView(R.id.edt_first_name)
    EditText edtFirstName;

    @BindView(R.id.edt_last_name)
    EditText edtLastName;

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_phone)
    EditText edtPhone;

    @Override
    public ProfilePresenter getPresenter() {
        return (ProfilePresenter) super.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new ProfilePresenter());
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        getPresenter().getUserInfo();
    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {
        ivBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvSelectPhoto.setOnClickListener(this);
    }

    @Override
    public void getUserSuccess(User user) {
        if (user.getAvatarUrl() !=null){
            Glide.with(this).load(user.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(ivUserAvatar);
        }
        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtUsername.setText(user.getUserName());
        edtEmail.setText(user.getEmail());
        edtPhone.setText(user.getPhone());
    }

    @Override
    public void getUserFailed(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void onUploadImageSuccess(String imageUrl) {
        Glide.with(this).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(ivUserAvatar);
    }

    @Override
    public void updateUserFailed(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void onClick(View view) {
        if (view == ivBack) {
            onBackPressed();
        } else if (view == tvSave) {
            getPresenter().getUser().setFirstName(edtFirstName.getText().toString());
            getPresenter().getUser().setLastName(edtLastName.getText().toString());
            getPresenter().getUser().setUserName(edtUsername.getText().toString());
            getPresenter().getUser().setEmail(edtEmail.getText().toString());
            getPresenter().getUser().setPhone(edtPhone.getText().toString());
            getPresenter().updateUser();
        } else if (view == tvSelectPhoto) {
            handleOpenLibrary();
        }
    }

    public void handleOpenLibrary() {
        String s[] = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
        if (checkPermissions(s)) {
            FileUtils.startActionGetImage(
                    this,
                    AppConstants.REQUEST_READ_LIBRARY,
                    "Select photo"
            );
        } else {
            ActivityCompat.requestPermissions(this, s, AppConstants.REQUEST_PERMISSION_READ_LIBRARY);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == AppConstants.REQUEST_PERMISSION_READ_LIBRARY) {
               handleOpenLibrary();
            }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.REQUEST_READ_LIBRARY && resultCode == Activity.RESULT_OK) {

            File file = FileUtils.convertUriToFile(this, data.getData());

            String mineType = FileUtils.getMineType(this, file);
            if (mineType == null) return;
            MediaType mediaType = MediaType.parse(mineType);
            RequestBody requestFile = RequestBody.create(mediaType, file);
            MultipartBody.Part filePath = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            getPresenter().uploadImage(filePath);
        }
    }

}
