package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgCategory;
import com.aigou.oss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/2.
 *
 * 栏目管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/category",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有栏目
     * @param request
     * @return
     */
    @RequestMapping(value = "/categoryList")
    public String gotoCategoryPage(HttpServletRequest request) {

        List<AgCategory> vos = categoryService.getAllCategory(request);

        request.setAttribute("vo",vos);

        return "category_list";
    }

    /**
     * 跳转到修改栏目页面
     * @param request
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/updateCategoryPage")
    public String gotoUpdateCategoryPage(HttpServletRequest request,
                                         @RequestParam(value = ParamConsts.cateId,required = true)Integer cateId){

        AgCategory category = categoryService.getCategoryByCateId(request,cateId);

        request.setAttribute("vo",category);

        return "update_category";
    }

    /**
     * 保存修改之后的栏目信息
     * @param request
     * @param category
     * @return
     */
    @RequestMapping(value = "/saveUpdateCategory")
    @ResponseBody
    public String saveUpdateCategory(HttpServletRequest request,AgCategory category) {
        return categoryService.doSaveUpdateCategory(request,category);
    }

    /**
     * 删除栏目
     * @param request
     * @param cateIds
     * @return
     */
    @RequestMapping(value = "/deleteCategory")
    @ResponseBody
    public String deleteCategory(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.cateIds,defaultValue = "")String cateIds){
        return categoryService.doDeleteCategory(request,cateIds);
    }

    @RequestMapping(value = "/addCategoryPage")
    public String  gotoAddSonCategoryPageWithParentId(HttpServletRequest request){

        return "add_category";
    }

    /**
     * 添加子栏目
     * @param request
     * @param category
     * @return
     */
    @RequestMapping(value = "/saveCategory")
    @ResponseBody
    public String addSonCategory(HttpServletRequest request,AgCategory category){

        return categoryService.doAddCategory(request,category);
    }
}
