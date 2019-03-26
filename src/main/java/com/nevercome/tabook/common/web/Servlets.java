package com.nevercome.tabook.common.web;

import com.nevercome.tabook.common.config.Global;
import com.nevercome.tabook.common.utils.StringUtils;
import com.nevercome.tabook.modules.sys.security.SystemAuthorizingRealm;
import com.nevercome.tabook.modules.sys.utils.UserUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Http与Servlet工具类.
 * @author calvin/thinkgem
 * @version 2014-8-19
 */
public class Servlets {

    // 静态文件后缀
    private final static String[] staticFiles = StringUtils.split(Global.getConfig("web.staticFile"), ",");

    // 动态映射URL后缀
    private final static String urlSuffix = Global.getUrlSuffix();

    /**
     * 是否是Ajax异步请求
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.indexOf("application/json") != -1
                || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
                || (principal != null && principal.isMobileLogin())));
    }

    /**
     * 获取当前请求对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (staticFiles == null) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                        + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//		if ((StringUtils.startsWith(uri, "/static/") || StringUtils.endsWithAny(uri, sfs))
//				&& !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")){
//			return true;
//		}
        if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, urlSuffix)
                && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")) {
            return true;
        }
        return false;
    }
}
