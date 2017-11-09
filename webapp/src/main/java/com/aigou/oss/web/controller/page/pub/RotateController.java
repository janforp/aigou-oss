package com.aigou.oss.web.controller.page.pub;

import com.aigou.oss.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/7/12.
 */
@Controller
@RequestMapping(value = "/pub/rotate",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class RotateController {

    @RequestMapping(value = "/rotate")
    public String gotoGoodsManagePage (HttpServletRequest request) {
        return "rotate";
    }

}
