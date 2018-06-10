package com.adoph.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sql工具类
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/6/1
 */
public class SqlUtils {

    /**
     * 转换为Jpa规范的sql格式
     * 如，原始sql：
     * select * from table where a = ? and (b = ? and c = ?)
     * 转换为：
     * select * from table where a = ?0 and (b = ?1 and c = ?2)
     *
     * @param sql 自定义sql
     * @return String
     */
    public static String toJpaSql(String sql) {
        String _sql = sql;
        String pattern = "\\?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(sql);
        int count = 0;
        while (m.find()) {
            _sql = _sql.replaceFirst(pattern, "#" + count);
            count++;
        }
        return _sql.replaceAll("#", "\\?");
    }
}
