package com.monitor.baseservice.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by demon on 2017/7/1 0001.
 */
public class HtmlUtils {

    private static Logger logger = Logger.getLogger(HtmlUtils.class);

    /**
     * get web information
     * @param url
     * @return Map<String, String>, format: {
     *      charset=xxx,
     *      description=xxx,
     *      title=xxx,
     *      url=xxx
     * }
     */
    public static Map<String, String> getCollectFromUrl(String url){
        Map<String, String> result = new HashMap<String, String>();
        try {
            result.put("url", url);
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            String title = doc.title();
            if(StringUtils.isNotBlank(title)){
                result.put("title", title);
            }
            String charset = doc.charset().name();
            if(StringUtils.isBlank(charset)){
                Elements eles = doc.select("meta[http-equiv=Content-Type]");
                Iterator<Element> itor = eles.iterator();
                while (itor.hasNext()){
                    charset = matchCharset(itor.next().toString().toUpperCase());
                }
            }
            if(StringUtils.isNoneBlank(charset)){
                result.put("charset", charset);
            }
            Elements metas = doc.head().select("meta");
            for (Element meta : metas) {
                String content = meta.attr("content");
                if ("description".equalsIgnoreCase(meta.attr("name"))) {
                    result.put("description", content);
                }
            }
        } catch (Exception e) {
            logger.error("文章解析出错：",e);
        }
        return result;
    }

    /**
     * get webpage charset
     * @param content content text
     * @return charset
     */
    public static String matchCharset(String content) {
        Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");
        Matcher m = p.matcher(content);
        if (m.find())
            return m.group();
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getCollectFromUrl("https://github.com/Demon-HY/DemonUtils/blob/master/utils/src/main/java/com/demon/utils/TimeUtils.java"));
    }
}
