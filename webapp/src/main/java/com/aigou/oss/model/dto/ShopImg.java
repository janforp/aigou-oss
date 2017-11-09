package com.aigou.oss.model.dto;

import org.apache.commons.lang.StringUtils;
import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;
import org.phprpc.util.PHPSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/7/8.
 *
 * 图片的php序列化与反序列化
 */
public class ShopImg {

    public static String getSerialize(Object obj) throws Exception {

        PHPSerializer p = new PHPSerializer();

        return new String(p.serialize(obj), "UTF-8");

    }

    public static List<String> getUnserializeList(String content) throws Exception {
        List<String> list = new ArrayList<>();
        PHPSerializer p = new PHPSerializer();
        if (StringUtils.isEmpty(content))
            return list;
        AssocArray array = (AssocArray) p.unserialize(content.getBytes("UTF-8"));
        for (int i = 0; i < array.size(); i++) {
            String t = (String )Cast.cast(array.get(i), String.class);
            list.add(t);
        }
        return list;
    }
}
