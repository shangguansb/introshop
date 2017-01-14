package com.example.IntroShop;



import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Window;
import android.widget.TextView;

import cn.volley.RequestQueue;

public class About extends Activity {

	private TextView txt=null;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      
        setContentView(R.layout.about);
        txt = (TextView) findViewById(R.id.abouttext);
        //�ı�����
        SpannableString ss = new SpannableString("感谢使用\n作者：张宁  \n版本：1.0\nqq:957666454");

        ss.setSpan(new URLSpan("http://user.qzone.qq.com/957666454//"), 23, 32,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        txt.setText(ss);

        txt.setMovementMethod(LinkMovementMethod.getInstance());
        
}
}