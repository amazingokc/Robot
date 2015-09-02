package com.amazingrobot.robot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by xgj on 2015/9/2.
 */
public class setNameActivity extends Activity {

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setname);

        Button mBsetname = (Button) findViewById(R.id.Bsetname);
        final EditText mEsetname = (EditText) findViewById(R.id.Esetname);

        mBsetname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = mEsetname.getText().toString();//自定义的名字
                SharedPreferences.Editor editor = getSharedPreferences("data",
                        MODE_PRIVATE).edit();
                editor.putString("name", Name);
                editor.commit();
                Intent intent = new Intent(setNameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}