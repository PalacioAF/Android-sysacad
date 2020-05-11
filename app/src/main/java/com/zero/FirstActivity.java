package com.zero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.login.LoginActivity;

public class FirstActivity extends AppCompatActivity {

    ImageView img_logo;
    TextView txt_logo;
    Animation splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        img_logo=(ImageView) findViewById(R.id.first_img_logo);
        txt_logo=(TextView) findViewById(R.id.first_txt_logo);
        splash = AnimationUtils.loadAnimation(this,R.anim.splash);
        img_logo.setAnimation(splash);
        txt_logo.setAnimation(splash);

        //Ocultar ActionBar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                sleep(3000);
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                }
                catch (Exception e){
                e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
