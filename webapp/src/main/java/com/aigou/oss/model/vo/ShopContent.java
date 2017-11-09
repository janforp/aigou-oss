package com.aigou.oss.model.vo;

import com.aigou.oss.util.StringUtil;
import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;
import org.phprpc.util.PHPSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/5/18.
 */
public class ShopContent implements Serializable {

    private String time;
    private String username;
    private String uid;
    private String shopid;
    private String shopname;
    private String shopqishu;
    private String gonumber;
    private String time_add;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopqishu() {
        return shopqishu;
    }

    public void setShopqishu(String shopqishu) {
        this.shopqishu = shopqishu;
    }

    public String getGonumber() {
        return gonumber;
    }

    public void setGonumber(String gonumber) {
        this.gonumber = gonumber;
    }

    public String getTime_add() {
        return time_add;
    }

    public void setTime_add(String time_add) {
        this.time_add = time_add;
    }

    public static ShopContent getUnserialize(String content) throws Exception {
        PHPSerializer p = new PHPSerializer();
        if (StringUtil.isEmpty(content))
            return null;
        ShopContent t =  (ShopContent) p.unserialize(content.getBytes("UTF-8"),ShopContent.class);
        return t;
    }

    public static List<ShopContent> getUnserializeList(String content) throws Exception {
        List<ShopContent> list = new ArrayList<>();
        PHPSerializer p = new PHPSerializer();
        if (StringUtil.isEmpty(content))
            return null;
        AssocArray array = (AssocArray) p.unserialize(content.getBytes("UTF-8"));
        for (int i = 0; i < array.size(); i++) {
            ShopContent t = (ShopContent) Cast.cast(array.get(i), ShopContent.class);
            list.add(t);
        }
        return list;
    }

    public static String getSerialize(List<ShopContent> list) throws Exception {

        PHPSerializer p = new PHPSerializer();

        return new String(p.serialize(list), "UTF-8");

    }

}
