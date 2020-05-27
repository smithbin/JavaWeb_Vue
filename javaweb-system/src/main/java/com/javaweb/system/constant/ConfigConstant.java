package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

public class ConfigConstant {

    /**
     * 菜单类型
     */
    public static Map<Integer, String> MENU_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "目录");
            put(2, "菜单");
            put(3, "按钮");
        }
    };

    /**
     * 站点类型
     */
    public static Map<Integer, String> ITEM_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "普通站点");
        }
    };

    /**
     * 友链类型
     */
    public static Map<Integer, String> LINK_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "友情链接");
            put(2, "合作伙伴");
        }
    };

    /**
     * 友链所属平台
     */
    public static Map<Integer, String> LINK_PLATFORM_LIST = new HashMap<Integer, String>() {
        {
            put(1, "PC网站");
            put(2, "WAP站");
            put(3, "小程序");
            put(4, "APP应用");
        }
    };

    /**
     * 推荐类型
     */
    public static Map<Integer, String> LAYOUT_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "早读新闻");
            put(2, "今日头条");
            put(3, "最新热门");
            put(4, "今日推荐");
        }
    };

    /**
     * 配置类型
     */
    public static Map<String, String> CONFIG_TYPE_LIST = new HashMap<String, String>() {
        {
            put("readonly", "只读文本");
            put("number", "数字");
            put("text", "单行文本");
            put("textarea", "多行文本");
            put("array", "数组");
            put("password", "密码");
            put("radio", "单选框");
            put("checkbox", "复选框");
            put("select", "下拉框");
            put("icon", "字体图标");
            put("date", "日期");
            put("datetime", "时间");
            put("image", "单张图片");
            put("images", "多张图片");
            put("file", "单个文件");
            put("files", "多个文件");
            put("ueditor", "富文本编辑器");
            put("json", "JSON");
        }
    };

    /**
     * 会员设备类型
     */
    public static Map<Integer, String> USER_DEVICE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "苹果");
            put(2, "安卓");
            put(3, "WAP站");
            put(4, "PC站");
            put(5, "马甲");
        }
    };

    /**
     * 通知来源
     */
    public static Map<Integer, String> NOTICE_SOURCE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "云平台");
        }
    };

    /**
     * 消息模板类型
     */
    public static Map<Integer, String> MESSAGE_TEMPLATE_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "系统模板");
            put(2, "短信模板");
            put(3, "邮件模板");
            put(4, "微信模板");
        }
    };

}
