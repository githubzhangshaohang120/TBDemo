package com.example.tbdemo.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.tbdemo.inter.IBase;
import com.example.tbdemo.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView {

    @Inject
    protected T mPresenter;
    private Unbinder bind;
    private ProgressDialog mLoadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        bind = ButterKnife.bind(this);

        inject();
        if (mPresenter !=null){
            mPresenter.attchView(this);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.datechView();
        }
        if (bind!=null){
            bind.unbind();
            bind = null;
        }

        //System.gc();
    }
    @Override
    public void intent(Class mActivity){
        Intent intent = new Intent(this,mActivity);
        startActivity(intent);
    }

    @Override
    public void intent(Class mActivity, Bundle bundle){
        Intent intent = new Intent(this,mActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }



    /**
     * 正则表达式
     *
     * @param mobileNums
     * @return
     */

    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    /**
     *初始化加载框
     */
    @Override
    public void showLoad() {
        // 加载框
        mLoadDialog = new ProgressDialog(this);
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.show();
        mLoadDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (mLoadDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK) {
                    cancelLoadDialog();//加载消失的同时
                    mLoadDialog.cancel();
                }
                return false;
            }
        });
    }

    public void cancelLoadDialog(){

    }

    public void dismissLoding(){
        mLoadDialog.dismiss();
        mLoadDialog.cancel();

    }

}
