package cn.nurasoft.miro.linuxmanual;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by neo on 01/05/17.
 **/


public class SettingsActivity extends Activity {
    Button about,linux,set;
    TextView Show;
    EditText co;
    LinearLayout bg;
    Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        about= findViewById(R.id.About);
        linux= findViewById(R.id.linux);
        set= findViewById(R.id.set);
        Show= findViewById(R.id.timeshow);
        co= findViewById(R.id.co);
        bg= findViewById(R.id.bg);

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        String formattedDate = df.format(c.getTime());
        Show.setText(formattedDate);


    //for every buttons event;
        linux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("|Linux Version|");
                //    指定下拉列表的显示数据
                final String[] cities ={"Ubuntu","Fedora", "ArchLinux","Mandriva Linux","Bodhi Linux",
                        "Kubuntu","Sabayon Linux",
                        "Ubuntu Kylin",
                        "HandyLinux",
                        "Asturix",
                        "Budgie",
                        "Alpine Linux",
                        "BackBox",
                        "Debian",
                        "Gentoo",
                        "MandrivaLinux",
                        "PCLinuxOS",
                        "Slackware Linux",
                        "openSUSE",
                        "Puppylinux",
                        "Mint",
                        "CentOS",
                        "Red Hat", };

                //    设置一个下拉的列表选择项
                builder.setItems(cities, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast toast;
                       //.setText("");
                toast=Toast.makeText(getApplicationContext(),"Developed BY:Miro.For Linux Users!",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            String s=null ;
            @Override
            public void onClick(View v) {
                s="#"+co.getText().toString();
                bg.setBackgroundColor(Color.parseColor(s));
                Toast toast;
                toast=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

}
