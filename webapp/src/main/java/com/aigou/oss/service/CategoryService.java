package com.aigou.oss.service;

import com.aigou.oss.dao.AgCategoryDAO;
import com.aigou.oss.dao.GoCategoryDAO;
import com.aigou.oss.model.AgCategory;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/2.
 * 栏目管理
 */
@Service
public class CategoryService {

    @Autowired
    private GoCategoryDAO goCategoryDAO;
    @Autowired
    private AgCategoryDAO agCategoryDAO;

    /**
     * 获取所有栏目
     * @param request
     * @return
     */
    public List<AgCategory> getAllCategory(HttpServletRequest request){
        return agCategoryDAO.getAllCategory();
    }


    /**
     * 获取要修改之前的数据
     * @param request
     * @param cateId
     * @return
     */
    public AgCategory getCategoryByCateId(HttpServletRequest request,Integer cateId) {

        return agCategoryDAO.selectByPrimaryKey(cateId);
    }

    /**
     * 保存修改之后的栏目信息
     * @param request
     * @param category
     * @return
     */
    public String doSaveUpdateCategory(HttpServletRequest request,AgCategory category){

        agCategoryDAO.updateByPrimaryKeySelective(category);
        return JsonUtil.buildSuccess();
    }

    /**
     * 删除栏目
     * @param request
     * @param cateIds
     * @return
     */
    public String doDeleteCategory(HttpServletRequest request,String cateIds) {

        if (StringUtil.isEmpty(cateIds)) {
            return JsonUtil.buildError("请选择栏目");
        }
        String[] cates = cateIds.split("&");
        for (int i =0 ;i<cates.length ;i++){
            Integer cateId = Integer.valueOf(cates[i]);
            agCategoryDAO.deleteByPrimaryKey(cateId);
        }
        return JsonUtil.buildSuccess("成功删除"+cates.length+"条栏目");
    }

    /**
     * 添加子栏目
     * @param request
     * @param category
     * @return
     */
    public String doAddCategory (HttpServletRequest request,AgCategory category) {


        agCategoryDAO.insertSelective(category);

        return JsonUtil.buildSuccess();
    }

}
