package com.example.administrator.egapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
//    用户输入框
    private EditText usernameEditText;
//    密码输入框
    private  EditText passwordEditText;
//    记住密码Checkbox
    private CheckBox rememberCheckbox;
//    注册按钮
    private TextView registrationButton;
//    登录按钮
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        在Java中绑定layout中的组件
        usernameEditText= (EditText) findViewById(R.id.username);
        passwordEditText= (EditText) findViewById(R.id.passeword);
        registrationButton= (TextView) findViewById(R.id.registerButton);
        loginButton= (Button) findViewById(R.id.loginButton);
        rememberCheckbox= (CheckBox) findViewById(R.id.remenberPasswordCheckbox);
        Intent intent=getIntent();
        String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");
//        点击注册按钮跳转到注册页面  监听器
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                一旦注册按钮被点击之后，就会执行这里面的代码
//                创建打开窗口意图
                Intent starIntent=new Intent(LoginActivity.this,RegActivity.class);
//                将用户名和密码输入框的内容填充到Intent中
                starIntent.putExtra("username",usernameEditText.getText().toString());
                starIntent.putExtra("password",passwordEditText.getText().toString());
                startActivity(starIntent);
//                销毁当前的窗体
                LoginActivity.this.finish();
            }
        });
//        设置监听记住密码勾选时间的监听器
        rememberCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                一旦记住密码控件勾选发生改变，就会执行这里面的代码
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
        Intent starintent=getIntent();
        String username1 = starintent.getStringExtra("username");
        String password1 = starintent.getStringExtra("password");
//        把intent里面的内容覆盖到我们注册页面中的EdiText里面的内容
        usernameEditText.setText(username1);
        passwordEditText.setText(password1);
    }

//    跳转到主窗口



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
