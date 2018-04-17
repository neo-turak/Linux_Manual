package cn.nurasoft.miro.linuxmanual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ScrollView;

/**
 * Created by miro on 11/02/17~.~
 **/

public class WelcomeActivity extends Activity {
    private Handler handler  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);


        handler =  new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1)
                    setContentView(R.layout.activity_main) ;

                Intent intent =new Intent();
                intent.setClass(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.startActivity(intent);
            }
        };

        new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(2000) ;
                } catch (Exception e) {
                    // TODO: handle exception
                }
                handler.sendEmptyMessage(1) ;
            }

        }).start() ;

    }
}
