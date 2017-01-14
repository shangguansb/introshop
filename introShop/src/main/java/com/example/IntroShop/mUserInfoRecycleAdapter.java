package com.example.IntroShop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.IntroShop.customView.DividerItemDecoration;
import com.example.IntroShop.model.action;
import com.example.IntroShop.model.contenttable;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by jamase on 2016-04-09.
 */
public class mUserInfoRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public String[] str;
    public List<action> mlist;

    public ArrayList contentID = new ArrayList<>();


    public mUserInfoRecycleAdapter(String[] datas) {
        str = datas;
    }

    public mUserInfoRecycleAdapter(List<action> list) {
        mlist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_active_item, parent, false);
        RecyclerView.ViewHolder hoder = new MyViewHolder1(v);
        return hoder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final MyViewHolder1 mholder = (MyViewHolder1) holder;
        BmobQuery<contenttable> query = new BmobQuery<contenttable>();
        int i = (int) mlist.get(position).getContentID();
        query.addWhereEqualTo("contentID", (int) mlist.get(position).getContentID());
        query.findObjects(mholder.text.getContext(), new FindListener<contenttable>() {

            @Override
            public void onSuccess(List<contenttable> object) {

                // TODO Auto-generated method stub
//                Toast.makeText(mholder.text.getContext(), "查询文章成功！---" + object.size(), Toast.LENGTH_LONG).show();
                if (object.size() != 0) {
                    contentID.add(object.get(0).getcontentID());
                    mholder.text_title.setText(object.get(0).getContentTitle());
                    mholder.text_title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mholder.text_title.getContext(), articleActivity.class);
                            intent.putExtra("contentID", (long) mlist.get(position).getContentID());
                            mholder.text_title.getContext().startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(mholder.text.getContext(), "查询文章失败！---" + msg, Toast.LENGTH_LONG).show();
            }
        });
        mholder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mholder.text.setTextColor(v.getResources().getColor(R.color.colorAccent));
                Log.e("user", "---------------" + position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView text_title;
        public View view;

        public MyViewHolder1(View itemView) {
            super(itemView);
            view = itemView;
            text = (TextView) itemView.findViewById(R.id.userinfo_text);
            text_title = (TextView) itemView.findViewById(R.id.userinfo_text_title);
//            text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    text.setTextColor(v.getResources().getColor(R.color.colorAccent));
//                }
//            });
        }
    }


}
