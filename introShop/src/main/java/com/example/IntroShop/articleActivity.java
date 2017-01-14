package com.example.IntroShop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.IntroShop.model.contenttable;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class articleActivity extends Activity {

    private TextView maintext = null;
    private TextView textTitle = null;
    public double contentId;
    private Button readmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.article_activity);
        contentId = getIntent().getLongExtra("contentID", 0);
        maintext = (TextView) findViewById(R.id.maintext);
        textTitle = (TextView) findViewById(R.id.articleTitle);
        readmore = (Button) findViewById(R.id.read_more_button);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleActivity.this, commentsActivity.class);
                startActivity(intent);
            }
        });
        if (contentId != 0) {
            BmobQuery<contenttable> query = new BmobQuery<contenttable>();
            query.addWhereEqualTo("contentID", contentId);
            query.findObjects(this, new FindListener<contenttable>() {
                @Override
                public void onSuccess(List<contenttable> list) {
                    if (list.size() != 0) {
                        textTitle.setText(list.get(0).getContentTitle());
                        maintext.setText(list.get(0).getMainContent());
                    }
                }

                @Override
                public void onError(int i, String s) {

                }

            });
        }

    }
}