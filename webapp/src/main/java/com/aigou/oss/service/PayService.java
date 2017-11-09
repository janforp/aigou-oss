package com.aigou.oss.service;

import com.aigou.oss.dao.GoAppPayDAO;
import com.aigou.oss.model.GoAppPay;
import com.aigou.oss.model.form.PaySaveForm;
import com.aigou.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/1.
 *
 * 模块8 - 支付管理
 */
@Service
public class PayService {


    @Autowired
    private GoAppPayDAO goAppPayDAO;

    /**
     * 跳转到支付管理页面 并显示支付方式列表
     * @param request
     * @return
     */
    public List<GoAppPay> getPayListData (HttpServletRequest request) {

        return goAppPayDAO.getAllPay();
    }

    /**
     * 添加支付方式
     * @param paySaveForm
     * @param imgUrl
     * @return
     */
    public boolean insert (PaySaveForm paySaveForm,String imgUrl) {

        GoAppPay pay = new GoAppPay();
        pay.setName(paySaveForm.getName());
        pay.setContent(paySaveForm.getContent());
        pay.setImg(imgUrl);
        pay.setXu(paySaveForm.getXu());
        pay.setType(paySaveForm.getType());
        pay.setNo(paySaveForm.getNo());

        goAppPayDAO.insertSelective(pay);
        return true;
    }
    /**
     * 删除选定的 支付方式
     * @Param payId
     * @param request
     * @return
     */
    public String doDeletePayById (HttpServletRequest request,String  payId) {

        String[] ids = payId.split(",");
        for (String id : ids) {
            Integer shopId = Integer.parseInt(id);

            goAppPayDAO.deleteByPrimaryKey(shopId);
        }

        return JsonUtil.buildSuccess();
    }

    public GoAppPay selectById(Integer payId) {

        return goAppPayDAO.selectByPrimaryKey(payId);
    }

    /**
     * 点击修改框上的保存
     * @param request
     * @param pay
     * @return
     */
    public String updatePay (HttpServletRequest request,GoAppPay pay) {
        Integer shopId = pay.getId();
        if (shopId != null && shopId != 0) {
            GoAppPay goAppPay = goAppPayDAO.selectByPrimaryKey(shopId);
            if (goAppPay == null) {
                return JsonUtil.buildError("支付方式不存在");
            }
            goAppPayDAO.updateByPrimaryKeySelective(pay);
            return JsonUtil.buildSuccess();
        }
        return JsonUtil.buildError("支付方式不存在");
    }
}
