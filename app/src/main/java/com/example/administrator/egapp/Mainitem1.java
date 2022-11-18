package com.example.administrator.egapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class Mainitem1 extends Fragment {

    private GridView gridView;
    TextView tvTotal1,tvTotal2,tvTotal3;
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
                    //处理XX的数据
                    break;
            }

        }
    };

    //定义线程，Android中要求网络请求不能发生在主线程，否则会报错。
    class HttpThread extends Thread{
        @Override
        public void run() {
            String result = NetUtil.httpGet("https://egdw.gitee.io/egapp/docInfo.json");
            try {
                JSONObject jsonObject = new JSONObject(result);
                httpHandler.obtainMessage(0x1,jsonObject).sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         return inflater.inflate(R.layout.fragment_mainitem1, container, false);
        View view= inflater.inflate(R.layout.fragment_mainitem1, container, false);
        tvTotal1= (TextView) view.findViewById(R.id.tv_total1);
        tvTotal2= (TextView) view.findViewById(R.id.tv_total2);
        tvTotal3= (TextView) view.findViewById(R.id.tv_total3);
        gridView=(GridView) view.findViewById(R.id.grid_view);

        MyAdapter adapter=new MyAdapter(getActivity());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent=new Intent(getActivity(),GgcsActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        new HttpThread().start();
        return view;

    }


    class MyAdapter extends BaseAdapter{
        private String[] names={"公共场所","公共场所数据分析","中小学校","中小学校数据分析"};
        private int[] images={R.drawable.achievement,R.drawable.appove,R.drawable.score,R.drawable.growup};
        private Context context;
        public MyAdapter(Context context){
            this.context=context;
        }
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            View view=layoutInflater.inflate(R.layout.grid_item,null);
            ImageView iv_menu= (ImageView) view.findViewById(R.id.image_menu);
            TextView tv_meny= (TextView) view.findViewById(R.id.text_menu);
            iv_menu.setImageResource(images[position]);
            tv_meny.setText(names[position]);
            return view;
        }
    }

}
