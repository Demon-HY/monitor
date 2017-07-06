//package com.monitor.web.common.filter;
//
//import com.monitor.baseservice.common.config.GlobalConfig;
//import com.monitor.baseservice.utils.DESEncryptionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//public class SecurityFilter implements Filter {
//
//    protected Logger logger = Logger.getLogger(this.getClass());
//    private static Set<String> GreenUrlSet = new HashSet<String>();
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        // 不需要权限验证的接口
//        GreenUrlSet.add("/");
//        GreenUrlSet.add("/login");
//        GreenUrlSet.add("/register");
//        GreenUrlSet.add("/index");
//    }
//
//    @Override
//    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        // TODO Auto-generated method stub
//        HttpServletRequest request = (HttpServletRequest) srequest;
//        HttpServletResponse response = (HttpServletResponse) sresponse;
//        String uri = request.getRequestURI();
//        if (request.getSession().getAttribute(GlobalConfig.LOGIN_SESSION_KEY) == null) {
//            Cookie[] cookies = request.getCookies();
//            if (containsSuffix(uri) || GreenUrlSet.contains(uri) || containsKey(uri)) {
//                logger.debug("don't check  url , " + request.getRequestURI());
//                filterChain.doFilter(srequest, sresponse);
//                return;
//            }else if (cookies!=null) {
//                boolean flag = true;
//                for (int i = 0; i < cookies.length; i++) {
//                    Cookie cookie = cookies[i];
//                    if (cookie.getName().equals(GlobalConfig.LOGIN_SESSION_KEY)) {
//                        if(StringUtils.isNotBlank(cookie.getValue())){
//                            flag = false;
//                        }else{
//                            break;
//                        }
//                        String value = getUserId(cookie.getValue());
//                        if(StringUtils.isNotBlank(value)) {
//                            String html;
//                            String referer = this.getRef(request);
//                            html = "<script type=\"text/javascript\">window.location.href=\"_BP_\"</script>";
//                            html = html.replace("_BP_", GlobalConfig.BASE_PATH);
//                            sresponse.getWriter().write(html);
//                        }
//                    }
//                }
//                if(flag){
//                    // 跳转到登陆页面
//                    jumpLoginURL(request, response);
//                }
//            }else{
//                // 跳转到登陆页面
//                jumpLoginURL(request, response);
//            }
//        }else{
//            filterChain.doFilter(srequest, sresponse);
//        }
//    }
//
//    /**
//     * 跳转到登陆页面
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    private void jumpLoginURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //跳转到登陆页面
//        String referer = this.getRef(request);
//        logger.debug("security filter, deney, " + request.getRequestURI());
//        String html = "";
//        if(referer.contains("/collect?") || referer.contains("/lookAround/standard/")
//                || referer.contains("/lookAround/simple/")){
//            html = "<script type=\"text/javascript\">window.location.href=\"_BP_login\"</script>";
//        }else{
//            html = "<script type=\"text/javascript\">window.location.href=\"_BP_index\"</script>";
//        }
//        html = html.replace("_BP_", GlobalConfig.BASE_PATH);
//        response.getWriter().write(html);
//    }
//
//
//
//    /**
//     * @param url
//     * @return
//     * @author neo
//     * @date 2016-5-4
//     */
//    private boolean containsSuffix(String url) {
//        if (url.endsWith(".js")
//                || url.endsWith(".css")
//                || url.endsWith(".jpg")
//                || url.endsWith(".gif")
//                || url.endsWith(".png")
//                || url.endsWith(".html")
//                || url.endsWith(".eot")
//                || url.endsWith(".svg")
//                || url.endsWith(".ttf")
//                || url.endsWith(".woff")
//                || url.endsWith(".ico")
//                || url.endsWith(".woff2")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * @param url
//     * @return
//     * @author neo
//     * @date 2016-5-4
//     */
//    private boolean containsKey(String url) {
//        if (url.contains("/login")
//                ||url.contains("/user/login")
//                || url.contains("/register")
//                ||url.contains("/user/regist")
//                ||url.contains("/index")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    public  String codeToString(String str) {
//        String strString = str;
//        try {
//            byte tempB[] = strString.getBytes("ISO-8859-1");
//            strString = new String(tempB);
//            return strString;
//        } catch (Exception e) {
//            return strString;
//        }
//    }
//
//    public String getRef(HttpServletRequest request){
//        String referer = "";
//        String param = this.codeToString(request.getQueryString());
//        if(StringUtils.isNotBlank(request.getContextPath())){
//            referer = referer + request.getContextPath();
//        }
//        if(StringUtils.isNotBlank(request.getServletPath())){
//            referer = referer + request.getServletPath();
//        }
//        if(StringUtils.isNotBlank(param)){
//            referer = referer + "?" + param;
//        }
//        request.getSession().setAttribute(GlobalConfig.LAST_REFERER, referer);
//        return referer;
//    }
//
//    public String getUserId(String value){
//        try {
//            String userId = DESEncryptionUtils.decryptToString(value.getBytes(), GlobalConfig.DES3_KEY.getBytes());
//            assert userId != null;
//            userId = userId.substring(0,userId.indexOf(GlobalConfig.PASSWORD_KEY));
//            return userId;
//        }catch (Exception e){
//            logger.error("解析cookie异常：",e);
//        }
//        return null;
//    }
//}