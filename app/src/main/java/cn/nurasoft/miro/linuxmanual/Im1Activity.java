package cn.nurasoft.miro.linuxmanual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOError;

/**
 * Created by miro on 09/02/17 ~.~
 **/

public class Im1Activity  extends Activity {
    ImageButton _return;
    ImageView Show;
    Button query;
    TextView editText;
    LinearLayout xt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.general);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.setTitle("Error").setMessage(
                "Did not find this").create();

        final AutoCloseDialog d = new AutoCloseDialog(dialog);
        _return=(ImageButton)findViewById(R.id._return);
        Show=(ImageView)findViewById(R.id.shower);
        query=(Button)findViewById(R.id.query);
        editText=(EditText)findViewById(R.id.edit);
        xt=(LinearLayout)findViewById(R.id.xt);

        _return.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(Im1Activity.this,MainActivity.class);
                Im1Activity.this.startActivity(intent);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            String name =null;
            @Override
            public void onClick(View view) {
            name=editText.getText().toString();
                try {
                    int d = getResources().getIdentifier(name, "drawable", getPackageName());
                    Show.setBackgroundResource(d);
                    xt.setBackgroundColor(Color.parseColor("#ffffff"));
                }catch(IOError error){
                   error.printStackTrace();
                    xt.setBackgroundColor(Color.parseColor("#2b2b2b"));
                    d.show(3000);
                }
                if (Show.getBackground()==null){
                    xt.setBackgroundColor(Color.parseColor("#123456"));
                }
            }
        });
    }
}


