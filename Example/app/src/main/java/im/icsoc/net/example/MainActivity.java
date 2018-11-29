package im.icsoc.net.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.icsoc.im.core.api.ZTIMCore;
import net.icsoc.im.core.bean.ConsultSource;
import net.icsoc.im.core.bean.ZTIMUserInfo;
import net.icsoc.im.imkit.api.IMKitCore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
    }

    /**
     * Tips:
     * 当前Demo展示的用户为固定用户
     * 使用方接入的时候，ZTIMCore.setConfiguration()应该在APP用户体系对应的信息获取完成后调用
     *
     * init后面字符串，为APPKEY值，再接入处获取
     */
    private void init() {
        String tid = "4396";
        String userName = "月月";
        String avatar = "http://img.qqu.cc/uploads/allimg/150530/1-1505301S542.jpg";
        ZTIMCore.setConfiguration("aabe12d634dd99881cd0974f1d77e425", tid, avatar, userName);
    }

    private void authOperation() {
        ConsultSource source = new ConsultSource();
        source.title = "MainActivity";
        source.landingPageTitle = "MainActivity";
        IMKitCore.openServiceActivity(this, source);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                if (ZTIMCore.isInit()){
                    authOperation();
                }
                break;

            case R.id.setting:
                startActivity(new Intent(MainActivity.this, CustomSettingActivity.class));
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 对应浏览轨迹
        ZTIMCore.userTrackHistory("MainActivity", true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 对应浏览轨迹
        ZTIMCore.userTrackHistory("MainActivity", false);
    }
}
