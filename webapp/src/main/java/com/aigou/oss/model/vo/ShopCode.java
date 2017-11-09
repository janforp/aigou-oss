package com.aigou.oss.model.vo;

import com.aigou.oss.util.StringUtil;
import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;
import org.phprpc.util.PHPSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/5/18.
 */
public class ShopCode {

    public static List<Integer> getUnserializeList(String content) throws Exception {

        List<Integer> list = new ArrayList<>();
        PHPSerializer p = new PHPSerializer();
        if (StringUtil.isEmpty(content))
            return list;
        AssocArray array = (AssocArray) p.unserialize(content.getBytes("UTF-8"));

        if(array != null) {
            for (int i = 0; i < array.size(); i++) {
                Integer t = (Integer) Cast.cast(array.get(i), Integer.class);
                list.add(t);
            }
        }

        return list;
    }

    public static String getSerialize(List<Integer> list) throws Exception {

        PHPSerializer p = new PHPSerializer();

        return new String(p.serialize(list), "UTF-8");

    }

}
