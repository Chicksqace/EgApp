package com.example.administrator.egapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegActivity extends AppCompatActivity {
    //    用户输入框
    private EditText usernameEditText;
    //    密码输入框
    private  EditText passwordEditText;
    //    注册按钮
    private Button registrationButton;
    //    返回按钮
    private TextView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        usernameEditText= (EditText) findViewById(R.id.username);
        passwordEditText= (EditText) findViewById(R.id.passeword);
        registrationButton= (Button) findViewById(R.id.registerButton);
        backButton= (TextView) findViewById(R.id.backButton);


//        返回登录页面

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                intent.putExtra("username",usernameEditText.getText().toString());
                intent.putExtra("password",passwordEditText.getText().toString());
                startActivity(intent);//发送intent
                RegActivity.this.finish();
            }
        });
//        获取登录页面发送的intent 内容
        Intent intent=getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
//        把intent里面的内容覆盖到我们注册页面中的EdiText里面的内容
        usernameEditText.setText(username);
        passwordEditText.setText(password);

    }
}
