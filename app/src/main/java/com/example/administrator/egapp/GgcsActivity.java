package com.example.administrator.egapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class GgcsActivity extends AppCompatActivity {
    ListView listView;
    List<Map<String,String>> datas=new ArrayList<>();
//    String[] titles={"杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店"};
//    String[] addresses={"杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX"};
//    String[] names={"11","22","33","44","55","66"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggcs);
//        listView= (ListView) findViewById(R.id.list_view);
//        for (int i=0;i<titles.length;i++){
//            Map<String,String> currentItem =new HashMap<>();
//            currentItem.put("title",titles[i]);
//            currentItem.put("address",addresses[i]);
//            currentItem.put("name",names[i]);
//            datas.add(currentItem);
//        }
//        SimpleAdapter adapter=new SimpleAdapter(GgcsActivity.this,datas,R.layout.list_item,
//                new String[]{"title","address","name"},
//                new int[]{R.id.list_title,R.id.list_address,R.id.list_name});
        listView= (ListView) findViewById(R.id.list_view);
        GgcsAdapter adapter=new GgcsAdapter(this);
        listView.setAdapter(adapter);
    }
    static class GgcsAdapter extends BaseAdapter{
        private Context context;
        private String[] titles={"杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店","杭州XXX酒店"};
        private String[] addresses={"杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX","杭州江干区XXX"};
        private String[] names={"11","22","33","44","55","66"};
        public GgcsAdapter(Context context){
            this.context=context;
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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            View view=layoutInflater.inflate(R.layout.list_item,null);
            TextView textTitle= (TextView) view.findViewById(R.id.list_title);
            textTitle.setText(titles[position]);
            TextView textAddress= (TextView) view.findViewById(R.id.list_address);
            textAddress.setText(titles[position]);
            TextView textName= (TextView) view.findViewById(R.id.list_name);
            textName.setText(titles[position]);
            return view;
        }
    }
}
