package com.example.administrator.egapp;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GgcsActivity extends AppCompatActivity {
    private ListView listView;
    private TextView tvTotal1,tvTotal2,tvTotal3;
    private OkHttpClient client = new OkHttpClient();

    Handler httpHandler = new  Handler(){
        @Override
        public void handleMessage(Message msg) {
            //msg
            //what obj
            //what int 是一个标志位
            //obj Object 是要传递的数据
            switch (msg.what){
                case 0x1:
                    //处理docInfo的数据
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    try {
                        String zhuxian = jsonObject.getJSONObject("water").getString("zhuxian");
                        String yinye = jsonObject.getJSONObject("water").getString("yinye");
                        String count = jsonObject.getJSONObject("water").getString("count");
                        tvTotal1.setText(zhuxian);
                        tvTotal2.setText(yinye);
                        tvTotal3.setText(count);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0x2:
                    //处理地址的数据
                    JSONArray jsonArray = (JSONArray) msg.obj;
                    //构建了相同元素数量的字符串数组
                    String[] titles = new String[jsonArray.length()];
                    String[] names = new String[jsonArray.length()];
                    String[] address = new String[jsonArray.length()];
                    //循环迭代，将数据放入到数据中。
                    for(int i =0;i<jsonArray.length();i++){
                        try {
                            String title = jsonArray.getJSONObject(i).getString("textname");
                            String textaddress = jsonArray.getJSONObject(i).getString("textaddress");
                            String name = jsonArray.getJSONObject(i).getString("textid");
                            titles[i] = title;
                            names[i] = name;
                            address[i] = textaddress;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    listView.setAdapter(new GgcsAdapter(getApplicationContext(),titles,names,address));
                    break;
            }

        }
    };

    public void getAddress(){
        //默认okhttp缺少useragent，需要手动添加下，否则gitee返回403
        Request request = new Request.Builder()
                .removeHeader("User-Agent")
                .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                .url("https://egdw.gitee.io/egapp/address.json")
                .build();

        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求如果失败就会执行这个里面的内容
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //如果成功，则解析返回的json数据。
                JSONArray jsonObject = null;
                try {
                    jsonObject = new JSONArray(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpHandler.obtainMessage(0x2,jsonObject).sendToTarget();
            }
        });
    }

    public void getTitles(){
        //默认okhttp缺少useragent，需要手动添加下，否则gitee返回403
        Request request = new Request.Builder()
                .removeHeader("User-Agent")
                .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                .url("https://egdw.gitee.io/egapp/docInfo.json")
                .build();

        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求如果失败就会执行这个里面的内容
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //如果成功，则解析返回的json数据。
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpHandler.obtainMessage(0x1,jsonObject).sendToTarget();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggcs);
        tvTotal1 = (TextView) findViewById(R.id.tv_total1);
        tvTotal2 = (TextView) findViewById(R.id.tv_total2);
        tvTotal3 = (TextView) findViewById(R.id.tv_total3);
        listView = (ListView) findViewById(R.id.list_view);
        //simpleadapter不好用

        getTitles();
        getAddress();

    }

    class GgcsAdapter extends BaseAdapter{

        private String[] titles = {"1","2","3","4","5","6","7","8","9"};
        private String[] names = {"1","2","3","4","5","6","7","8","9"};
        private String[] address = {"1","2","3","4","5","6","7","8","9"};

        private Context context;

        public GgcsAdapter(Context context,String[] titles,String[] names,String[] address){
            this.context = context;
            this.titles = titles;
            this.names = names;
            this.address = address;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //viewholder
        //listview => recyclyView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, null);
            TextView tvAddress = (TextView) view.findViewById(R.id.list_address);
            TextView tvButton = (TextView) view.findViewById(R.id.list_danghang);

            TextView tvName = (TextView) view.findViewById(R.id.list_name);
            TextView tvTitle = (TextView) view.findViewById(R.id.list_title);

            tvAddress.setText(address[position]);
            tvName.setText(names[position]);
            tvTitle.setText(titles[position]);


            //遇到几个同学忘记。返回null就会报错的
            return view;
        }
    }
}
