package org.unique.generator;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class Main {

    public static void main(String[] args) {
        CodeGenerator code = new CodeGenerator();
        code.generator(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/monitor?characterEncoding=utf-8&useUnicode=true&zeroDateTimeBehavior=convertToNull",
                "monitor",
                "monitor",
                "com.monitor",
                "Demon",
                "",
                "f:/code/generator"
                );
    }
}
