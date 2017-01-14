package com.example.IntroShop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamase on 2016-03-31.
 */
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TestBean> bean = new ArrayList<TestBean>();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;


    public RecyclerViewAdapter(Context context, List data) {
        this.context = context;
        this.bean = data;
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    @Override
    public int getItemViewType(int position) {
        return bean.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View v = null;
        switch (viewType) {
            case 1:
                v = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
                holder = new MyViewHolder1(v);
                break;
            case 2:
                v = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
                holder = new MyViewHolder2(v);
                break;
            case 4:
                v = LayoutInflater.from(context).inflate(R.layout.item4, parent, false);
                holder = new MyViewHolder2(v);
                break;
            case 3:
                v = LayoutInflater.from(context).inflate(R.layout.loadmore, parent, false);
                holder = new MyViewHolder3(v);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        private LinearLayout ll;
        public String mBoundString;
        public Button lableButton;
        public TextView likeNumber;
        public TextView text_title;
        public View mView;
        public ImageView avater;
        public Button likeheart;
        public TextView Textname;

        public MyViewHolder1(View view) {
            super(view);
            mView = view;
            lableButton = (Button) mView.findViewById(R.id.lable_button);
            avater = (ImageView) mView.findViewById(R.id.avater);
            likeheart = (Button) mView.findViewById(R.id.likeheart);
            likeNumber = (TextView) mView.findViewById(R.id.like_number);
            Textname = (TextView) mView.findViewById(R.id.textname);
            text_title = (TextView) mView.findViewById(R.id.text_title1);
            text_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mView.getContext(), articleActivity.class);
                    mView.getContext().startActivity(intent);
                }
            });
            avater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mView.getContext(), UserSocialInfoActivity.class);
                    Log.e("RecyclerViewAdapter1", "onclick");
                    mView.getContext().startActivity(intent);
                }
            });
            likeheart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeheart.setBackgroundResource(R.drawable.direct_heart);
                }
            });
            likeNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeheart.setBackgroundResource(R.drawable.direct_heart);
                }
            });
            Textname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mView.getContext(), UserSocialInfoActivity.class);
                    mView.getContext().startActivity(intent);
                }
            });
        }

    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        private LinearLayout ll;
        public String mBoundString;
        public Button lableButton;
        public TextView likeNumber;
        public View mView;
        public ImageView avater;
        public Button likeheart;
        public TextView Textname;
        public TextView text_title;

        public MyViewHolder2(View view) {
            super(view);
            mView = view;
            text_title = (TextView) mView.findViewById(R.id.text_title2);
            lableButton = (Button) mView.findViewById(R.id.lable_button);
            avater = (ImageView) mView.findViewById(R.id.avater);
            likeheart = (Button) mView.findViewById(R.id.likeheart);
            likeNumber = (TextView) mView.findViewById(R.id.like_number);
            Textname = (TextView) mView.findViewById(R.id.textname);
            text_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mView.getContext(), articleActivity.class);
                    mView.getContext().startActivity(intent);
                }
            });
            avater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("RecyclerViewAdapter", "onclick");
                    Intent intent = new Intent(mView.getContext(), UserSocialInfoActivity.class);
                    mView.getContext().startActivity(intent);
                }
            });
            likeheart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeheart.setBackgroundResource(R.drawable.direct_heart);
                }
            });
            likeNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeheart.setBackgroundResource(R.drawable.direct_heart);
                }
            });
            Textname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mView.getContext(), UserSocialInfoActivity.class);
                    mView.getContext().startActivity(intent);
                }
            });
        }

    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {
        public MyViewHolder3(View view) {
            super(view);
        }

    }
}