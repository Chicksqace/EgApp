package com.example.administrator.egapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2022/9/30.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EGAPP";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_sql = "CREATE TABLE USER(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL UNIQUE,PASSWORD TEXT NOT NULL)";
        db.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入用户信息到sqlite数据库
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public long insertUser(String username,String password){
        //获得可写数据库
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //填充需要插入到表中的内容
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        //调用insert插入数据到user表中
        long result = writableDatabase.insert("user", null, contentValues);
        //释放资源
        writableDatabase.close();
        return result;
    }

    /**
     * 用sqlite中查询用户和密码
     * @param username 用户
     * @param password 密码
     * @return
     */
    public boolean searchUser(String username,String password){
        //定义查询sql语句。其中？代表占位符号，会被后面的parmas中得数据里面填充的数据替换掉。
        String searchSql = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
        //得到一个可读的数据库
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //替换sql语句中得占位符
        String[] params = new String[]{username,password};
        //得到游标对象，这里面存放着查询出来的数据
        Cursor cursor = readableDatabase.rawQuery(searchSql, params);
        if (cursor.getCount()>0){
            //代表查询到了至少一条数据
            //销毁资源
            cursor.close();
            readableDatabase.close();
            return true;
        }
        //销毁资源
        cursor.close();
        readableDatabase.close();
        return false;
    }
}
