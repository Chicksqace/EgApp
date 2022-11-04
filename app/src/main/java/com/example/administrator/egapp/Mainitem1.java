package com.example.administrator.egapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class Mainitem1 extends Fragment {
    public Mainitem1() {

    }

    private GridView gridView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         return inflater.inflate(R.layout.fragment_mainitem1, container, false);
        View view= inflater.inflate(R.layout.fragment_mainitem1, container, false);
        gridView=(GridView) view.findViewById(R.id.grid_view);
        MyAdapter adapter=new MyAdapter(getActivity());
        gridView.setAdapter(adapter);
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
