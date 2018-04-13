package com.example.nhattruong.financialmanager.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.dialog.DialogProgress;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.example.nhattruong.financialmanager.utils.DialogUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

abstract public class BaseFragment extends Fragment implements IBaseView {

    @Inject
    PreferManager preferManager;
    private BasePresenter mPresenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        MainApplication.getAppComponent().inject(this);
        if (mPresenter != null)
            mPresenter.onCreate(this, unbinder);

        onInitData();
        onInitListener();
    }

    protected abstract void onInitData();

    protected abstract void onInitListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }

    public BasePresenter getPresenter() {
        return mPresenter;
    }

    public void setPresenter(BasePresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    abstract protected int getLayoutId();

    // progress dialog
    private DialogProgress mProgressDialog;

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showDialogLoading() {
        dismissDialogLoading();
        if (!getActivity().isDestroyed())
            mProgressDialog = DialogUtils.showProgressDialog(getContext());
    }

    protected void dismissDialogLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            if (!getActivity().isDestroyed()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }
    // end progress dialog

    // dialog with one button
    protected void showOkDialog(String title, String message, DialogOk.IOkDialogListener listener) {
        DialogUtils.showOkDialog(getContext(), title, message, listener);
    }

    protected void showErrorDialog(String message) {
        showOkDialog(getResources().getString(R.string.error), message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
            }
        });
    }

    protected void showNoNetworkErrorDialog() {
        showErrorDialog(getString(R.string.no_internet_network));
    }

    protected void showConfirmDialog(String title, String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(getActivity(), title, message, listener);
    }

    protected void showConfirmDialog(String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(getActivity(), getString(R.string.app_name), message, listener);
    }

    protected boolean checkPermissions(String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), s)
                    != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        showDialogLoading();
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }

    protected void showRestErrorDialog(RestError error, final IRestErrorListener listener) {
        dismissDialogLoading();
        showOkDialog(getResources().getString(R.string.error), error.message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
                listener.onListener();
            }
        });
    }


    @Override
    public void showErrorNormal(String error) {
        if (getActivity().isDestroyed()) {
            return;
        }
        dismissDialogLoading();
        showErrorDialog(error);
    }

    public interface IRestErrorListener {
        void onListener();
    }

    private boolean handleSpecialCode(RestError error) {
        return error.code == AppConstants.ERROR_CODE_USER_NOT_FOUND
                || error.code == AppConstants.ERROR_CODE_TOKEN_FAILED
                || error.code == AppConstants.ERROR_CODE_RELOGIN
                || error.code == AppConstants.ERROR_CODE_IP_ACCESS;
    }
}
