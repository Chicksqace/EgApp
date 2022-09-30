package com.example.administrator.egapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //创建dbHelper
    private DbHelper dbHelper;
    //用户名输入框
    private EditText usernameEditText;
    //密码输入框
    private EditText passwordEditText;
    //记住密码Checkbox
    private CheckBox rememberCheckbox;
    //注册按钮
    private TextView registrationButton;
    //登录按钮
    private Button loginButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化dbhelper
        dbHelper = new DbHelper(this);

        //定义sharedPreferences与对应的编辑器
        sharedPreferences = getSharedPreferences("EGAPP", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        //java中绑定layout中的组件
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        registrationButton = (TextView) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        rememberCheckbox = (CheckBox) findViewById(R.id.rememberPasswordCheckbox);

        //获取注册页面传递过来的Intent的内容
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        //把intent里面的内容覆盖到我们注册页面中的EditText里面的内容
        usernameEditText.setText(username);
        passwordEditText.setText(password);

        //启动窗口后，读取存储的账号和密码
        // 第一个参数是key，第二个参数是默认返回值
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);
        if (isRemember){
            //将存储的账号和密码填充到editText当中。
            usernameEditText.setText(sharedPreferences.getString("username", ""));
            passwordEditText.setText(sharedPreferences.getString("password",""));
            //把记住密码设置为勾选状态
            rememberCheckbox.setChecked(true);
        }


        //点击注册按钮跳转到注册页面
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //一旦注册按钮被点击之后，就会执行这里面的代码
                //创建打开注册窗口意图
                Intent startIntent = new Intent(LoginActivity.this,RegActivity.class);
                //将用户名和密码输入框中的内容填充到Intent中。
                startIntent.putExtra("username",usernameEditText.getText().toString());
                startIntent.putExtra("password",passwordEditText.getText().toString());
                startActivity(startIntent);
                //销毁当前的窗体
                LoginActivity.this.finish();
            }
        });

        //设置记住密码控件勾选事件的监听器
        rememberCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //一旦记住密码控件勾选状态发生改变，就会执行这里面的代码
                if (isChecked == false){
                    editor.putString("username","");
                    editor.putString("password","");
                }
                editor.putBoolean("isRemember",isChecked);
                editor.commit();
            }
        });


        //跳转到主页面去
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                //数据库查询操作
                boolean exist = dbHelper.selectUser(username, password);
                if (exist){
                    //判断是否记住密码
                    if(rememberCheckbox.isChecked()){
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.commit();
                    }

                    //找到用户了
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    //登录成功后跳转到主页面
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    //没有找到用户
                    Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

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
