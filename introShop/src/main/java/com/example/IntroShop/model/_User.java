package com.example.IntroShop.model;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by jamase on 2016-04-11.
 */
public class _User extends BmobUser {
    private String avatarUrl;
    public BmobRelation contenttable;
    public long uid;
    public BmobFile avatar;

    public void setRelation(BmobRelation relation) {
        this.contenttable = relation;

    }

    public BmobRelation getRelation() {
        return contenttable;
    }


    public void setAvatar(String url) {
        this.avatarUrl = url;

    }

    public String getAvator() {
        return avatarUrl;
    }

    public void setUAvatar(BmobFile avatar) {
        this.avatar = avatar;

    }


    public BmobFile getUAvatar() {
        return avatar;
    }

}
