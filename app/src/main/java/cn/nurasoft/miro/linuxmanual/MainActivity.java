package cn.nurasoft.miro.linuxmanual;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView im1,im2,im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im1= findViewById(R.id.image1);
        im2= findViewById(R.id.image2);
        im3= findViewById(R.id.image3);
        im4= findViewById(R.id.image4);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,CommandingActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,ExpandableActivity.class);
                MainActivity.this.startActivity(intent);
                

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,Im1Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent =new Intent();
                intent.setClass(MainActivity.this,SettingsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

}
