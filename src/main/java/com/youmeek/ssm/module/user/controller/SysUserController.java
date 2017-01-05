package com.youmeek.ssm.module.user.controller;

import com.youmeek.ssm.dst.until.Constant;
import com.youmeek.ssm.dst.until.HttpUtil;
import com.youmeek.ssm.module.user.pojo.SysUser;
import com.youmeek.ssm.module.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/showUserToJspById/{userId}")
    public String showUser(Model model, @PathVariable("userId") Long userId) {
        SysUser user = this.sysUserService.getById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }

    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        return user;
    }


    @RequestMapping("/test-logback")
    @ResponseBody
    public Date testLogback() {
        LOG.trace("-----------------------------------trace");
        LOG.debug("-----------------------------------debug");
        LOG.info("-----------------------------------info");
        LOG.warn("-----------------------------------warn");
        LOG.error("-----------------------------------error");
        return new Date();
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    @ResponseBody
    public SysUser saveUser(SysUser sysUser) {
        if (sysUser != null) {
            sysUser.setSysUserRegisterDatetime(new Date());
            return sysUserService.saveAndUpdateSysUser(sysUser);
        }
        return null;
    }

    @RequestMapping("/test-ehcache")
    @ResponseBody
    public List<SysUser> findEhcache() {
        //使用缓存之后，调用这个方法，第一次调用的时候控制台有输出 sql 语句，后面都没有了
        return sysUserService.findIsNotDeleteUserListToTestEhCache("N");
    }

    @RequestMapping("/test-no-ehcache/{userId}")
    @ResponseBody
    public SysUser findNoEhcache(@PathVariable("userId") Long userId) {
        //没有使用缓存，无论何时调用这个方法，控制台都会输出 sql 语句
        SysUser user = sysUserService.findBySysUserId(userId);
        return user;
    }


    @RequestMapping(value = "/getToken", produces = "text/html;charset=UTF-8")

    public void getTabJson(HttpServletRequest request, HttpServletResponse response) {


        Map params = new HashMap();
        params.put("userId", request.getParameter("userId"));
        params.put("name", request.getParameter("name"));
        params.put("portraitUri", request.getParameter("portraitUri"));


        String json = HttpUtil.post(Constant.Rong_IP + "/user/getToken.json", params, 3000, 3000, "UTF-8");


        System.out.println("json===" + json);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }






    }


            /**
             * 目标用户通过账号密码登陆返回用户id,和token
             * 格式：{"code":200,"result":{"id":"ZiBd1Tecz","token":"GtgiMlW+3B3IDO/bYwzkva+YsUIoF3ojin3K277sfOnWSAQuAg6chOcy7JX4fElCeXaqBaOIGIakBzQTe5rR/KtdpZUyLdaH"}}
             *
             */


            @RequestMapping(value = "/getToken", produces = "text/html;charset=UTF-8")


            public void getUserId_UserToken_Json(HttpServletRequest request, HttpServletResponse response) {


                Map params = new HashMap();
                params.put("userId", request.getParameter("userId"));
                params.put("name", request.getParameter("name"));
                params.put("portraitUri", request.getParameter("portraitUri"));


                String json = HttpUtil.post(Constant.Rong_IP + "/user/getToken.json", params, 3000, 3000, "UTF-8");


                System.out.println("json===" + json);

                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                try {
                    response.getWriter().write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }


}
