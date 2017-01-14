package com.example.IntroShop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * Created by jamase on 2016-04-15.
 */
public class contenttable extends BmobObject {
    String contentTitle;
    String mainContent;
    long contentID;
    long collection;
    long commen;
    long read;
    String imgUrl;
    String username;
    BmobPointer user;

    public String getContentTitle() {
        return contentTitle;
    }

    public String getMainContent() {
        return mainContent;
    }

    public long getcontentID() {
        return contentID;
    }

    public String getImgUrl() {
        return contentTitle;
    }

}
