package com.qilu.app;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.qilu.core.activities.ProxyActivity;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.ec.launcher.LauncherDelegate;
import com.qilu.ec.main.EcBottomDelegate;
import com.qilu.ec.sign.ISignListener;
import com.qilu.ec.sign.SignInDelegate;
import com.qilu.ui.launcher.ILauncherListener;
import com.qilu.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ISignListener,ILauncherListener {
    @Override
    public QiluDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.requestPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Util.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        Util.requestPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        Util.requestPermission(this, Manifest.permission.CHANGE_WIFI_STATE);
        Util.requestPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WAKE_LOCK);
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new SignInDelegate());
    }

    @Override
    public void onSignUpFailure(String response) {
        if(response.equals("4")||response.equals("5")||response.equals("6")){
            Toast.makeText(this, "重复注册，请登录！", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSignInFailure(String response) {
        switch(response){
            case "-1":
                Toast.makeText(this, "帐号不存在", Toast.LENGTH_LONG).show();
                break;
            case "1":
                Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch(tag){
            case SIGNED:
                Toast.makeText(this,"已登录",Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"未登录",Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
