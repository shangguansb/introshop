package com.example.IntroShop.model;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by jamase on 2016-04-15.
 */
public class action extends BmobObject {

    BmobPointer user;
    String actionType;
    BmobPointer actionContent;
    long actionID;
    long contentID;
    String username;

    public void setcontentObject(BmobPointer content) {
        this.actionContent = content;

    }

    public BmobPointer getcontentObject() {
        return actionContent;
    }

    public long getContentID() {
        return contentID;
    }
}
