package com.javaweb.admin.controller;

//import com.javaweb.common.annotation.CheckLogin;
import com.javaweb.common.common.BaseController;
import com.javaweb.common.utils.*;
import com.javaweb.system.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/index")
    public String index() {
        System.out.print("部署成功");
        return "SUCCESS";
    }

    /**
     * 获取个人信息
     *
     * @return
     */
//    @CheckLogin
    @RequestMapping("/getMyInfo")
    public String getMyInfo() {
        return "验证通过";
    }

    /**
     * Jwt测试
     *
     * @return
     */
    @RequestMapping("/jwt")
    public String jwt() {
        String token = JwtUtil.createJWT(1);
        System.out.print("token:" + token);
        Claims data = JwtUtil.parseJWT(token);
        System.out.println(data);
        System.out.println("用户ID：" + data.get("id"));

        Boolean isOK = JwtUtil.isVerify(token, 1);
        System.out.print(isOK);
        return "OK";
    }

    /**
     * Redis缓存测试
     */
    @RequestMapping("redis")
    public String redis() {
        User user = new User();
        user.setId(1);
        user.setNickname("相约在冬季");
        redisUtils.set("user", user);
        User user1 = (User) redisUtils.get("user");
        System.out.print(user1);
        return "OK";
    }

    /**
     * 消息推送测试
     */
    @RequestMapping("pushNotice")
    public void pushNotice() {
        List<String> alias = new ArrayList<>();
        alias.add("000000004e2b47f0fffffffff073072b");
//        JPushUtils.sendToAllIos("Java通知标题", "java推送消息标题", "java推送消息内容", "https://m.baidu.com");
        JPushUtils.sendToRegistrationId(alias, "Java通知标题", "java推送消息标题", "java推送消息内容", "https://m.baidu.com");
    }

    /**
     * 发送邮件测试
     *
     * @return
     */
    @RequestMapping("sendEmail")
    public JsonResult sendEmail() {

//        // 发送简单的文本
//        mailUtils.sendSimpleMail("1051386190@qq.com", "发送邮件测试", "大家好，这是我用springboot进行发送邮件测试");
//        // 发送Html邮件
//        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件" + "</font></h3></body></html>";
//        mailUtils.sendHtmlMail("1051386190@qq.com", "发送邮件测试", content);
//        // 发送带附件的邮件
//        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有附件哦" + "</font></h3></body></html>";
//        String filePath = "C:\\Users\\Administrator\\Desktop\\university\\图片.zip";
//        mailUtils.sendAttachmentMail("1051386190@qq.com", "发送邮件测试", content, filePath);
//        // 发送带图片的邮件
//        String rscPath = System.getProperty("user.dir") + "/uploads/images/user/20191212/20191212151248667.jpeg";
//        String rscId = "001";
//        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有图片哦" + "</font></h3>"
//                + "<img src=\'cid:" + rscId + "\'></body></html>";
//        mailUtils.sendInlineResourceMail("1051386190@qq.com", "发送邮件测试", content, rscPath, rscId);

        // 发送模板邮件
        Map<String, Object> map = new HashMap<>();
        map.put("itemName", "招商银行信用贷款");
        map.put("stuName", "相约在冬季");
        map.put("updateContent", "个人房贷");
        map.put("updatePerson", "管理员");
        map.put("updateDate", "20190-12-13");
        map.put("remarks", "保密");
        mailUtils.sendTemplateMail("1051386190@qq.com", "这是一个模板文件", "email/template", map);

        return JsonResult.success();
    }

    /**
     * 文件压缩
     *
     * @return
     */
    @RequestMapping("fileZip")
    public JsonResult fileZip() {
        // 压缩文件
        try {
            ZipUtils.zip("D:\\sites\\RXThinkCMF\\university\\uploads", "D:\\打包测试.zip", false);
        } catch (Exception e) {

        }

        // 解压缩文件
        ZipUtils.unzip("D:\\打包测试.zip", "C:\\Users\\Administrator\\Desktop\\university\\test");
        return JsonResult.success();
    }

}
