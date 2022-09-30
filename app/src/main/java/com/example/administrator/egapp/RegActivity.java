package com.example.administrator.egapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    //创建dbHelper
    private DbHelper dbHelper;

    //用户名输入框
    private EditText usernameEditText;
    //密码输入框
    private EditText passwordEditText;
    //注册按钮
    private Button registrationButton;
    //返回按钮
    private TextView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //初始化dbhelper
        dbHelper = new DbHelper(this);
        //java中绑定layout中的组件
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        registrationButton = (Button) findViewById(R.id.registerButton);
        backButton = (TextView) findViewById(R.id.backButton);

        //返回到登录页面
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                intent.putExtra("username",usernameEditText.getText().toString());
                intent.putExtra("password",passwordEditText.getText().toString());
                startActivity(intent);
                RegActivity.this.finish();
            }
        });

        //注册按钮的触发事件
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                //数据库插入操作
                long result = dbHelper.insertUser(username, password);
                if (result!=1){
                    //失败
                    Toast.makeText(RegActivity.this,"注册失败。已经有存在的用户了！",Toast.LENGTH_SHORT).show();
                    //清空输入框
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    //使焦点聚焦到username的输入框上
                    usernameEditText.requestFocus();
                }else{
                    //成功
                    Toast.makeText(RegActivity.this,"注册成功。",Toast.LENGTH_SHORT).show();
                    //注册成功后，跳转到登录页面
                    Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                    intent.putExtra("username",usernameEditText.getText().toString());
                    intent.putExtra("password",passwordEditText.getText().toString());
                    startActivity(intent);
                    RegActivity.this.finish();
                }
            }
        });


        //获取登录页面传递过来的Intent的内容
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        //把intent里面的内容覆盖到我们注册页面中的EditText里面的内容
        usernameEditText.setText(username);
        passwordEditText.setText(password);
    }
}
