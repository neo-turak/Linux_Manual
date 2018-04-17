package cn.nurasoft.miro.linuxmanual;

import android.app.AlertDialog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by miro on 11/02/17~.~
 **/

public class AutoCloseDialog {
    private AlertDialog dialog;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public AutoCloseDialog(AlertDialog dialog){
        this.dialog = dialog;
    }

    public void show(long duration){
        //创建自动关闭任务
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        };
        //新建调度任务
        executor.schedule(runner, duration, TimeUnit.MILLISECONDS);
        dialog.show();
    }
}
