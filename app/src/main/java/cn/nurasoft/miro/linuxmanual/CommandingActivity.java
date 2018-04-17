package cn.nurasoft.miro.linuxmanual;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by NURA on 2017/2/13.
 **/

public class CommandingActivity extends Activity {

    DataBaseHelperClass dbhelper;
    SQLiteDatabase db;
    ImageButton _return;
    Button OK;
    EditText editText;
    TextView ComName, Description, Fame,Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.commanddisc);
        _return=(ImageButton)findViewById(R.id._return);
        OK=(Button)findViewById(R.id.query);
        editText=(EditText)findViewById(R.id.edit);
        ComName=(TextView)findViewById(R.id.ComName);
        Description =(TextView)findViewById(R.id.Description);
        Category=(TextView)findViewById(R.id.Category);
        Fame =(TextView)findViewById(R.id.AppearTime);

        dbhelper = new DataBaseHelperClass(CommandingActivity.this);
        db = dbhelper.getReadableDatabase();
        try{
            dbhelper.createDataBase();
            dbhelper.onUpgrade(db,1,2);
        }catch (IOException e){
            e.printStackTrace();
        }

        _return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CommandingActivity.this,MainActivity.class);
                CommandingActivity.this.startActivity(intent);
            }
        });

        OK.setOnClickListener(new View.OnClickListener() {
            String s = null;

            @Override
            public void onClick(View view) {
                dbhelper.openDataBase();
                s=editText.getText().toString();
                ComName.setText(dbhelper.getinfo(s)[0]);
                Description.setText(dbhelper.getinfo(s)[2]);
                Category.setText(dbhelper.getinfo(s)[1]);
                Fame.setText(dbhelper.getinfo(s)[3]);
                dbhelper.close();
            }
        });
    }
}
