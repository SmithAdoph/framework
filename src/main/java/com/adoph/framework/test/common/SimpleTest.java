package com.adoph.framework.test.common;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单测试
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/22
 */
public class SimpleTest {

    @Test
    public void testString() {
        StringBuffer sql = new StringBuffer();
        {
            sql.append("SELECT t1.id,t1.optOrderId,t1.CreatedBy, UNIX_TIMESTAMP(t1.CreatedTime) AS createTime, t1.account_type, t1.reg_city_code                                                                                                     ");
            sql.append("	, t1.account, t2.`name`, t1.recharge_amount, t1.gift_amount, t1.voucher                                                                                                                                     ");
            sql.append("    , t1.flowStatus");
            sql.append("	, CASE t1.account_type WHEN 0 THEN '个人' WHEN 1 THEN '企业' ELSE '其他' END AS accountTypeDes, t1.opea_type, CASE t1.opea_type WHEN 0 THEN '上账' WHEN 1 THEN '下账' ELSE '其他' END AS opeaTypeDesc, (    ");
            sql.append("		SELECT t3.name                                                                                                                                                                                          ");
            sql.append("		FROM city_mapping t3                                                                                                                                                                                    ");
            sql.append("		WHERE t3.areaCode = t1.reg_city_code                                                                                                                                                                    ");
            sql.append("		) AS regCityCodeDesc, (                                                                                                                                                                                 ");
            sql.append("		SELECT t5.accountBalance                                                                                                                                                                                ");
            sql.append("		FROM subscriber t6, subscriber_account t5                                                                                                                                                               ");
            sql.append("		WHERE t6.ID = t5.subscriberId                                                                                                                                                                           ");
            sql.append("			AND t6.mobileNo = t1.account                                                                                                                                                                        ");
            sql.append("		) AS accountBalance                                                                                                                                                                                     ");
            sql.append("	, (                                                                                                                                                                                                         ");
            sql.append("		SELECT t5.invoiceAmount                                                                                                                                                                                 ");
            sql.append("		FROM subscriber t6, subscriber_account t5                                                                                                                                                               ");
            sql.append("		WHERE t6.ID = t5.subscriberId                                                                                                                                                                           ");
            sql.append("			AND t6.mobileNo = t1.account                                                                                                                                                                        ");
            sql.append("		) AS invoiceAmount, (                                                                                                                                                                                   ");
            sql.append("		SELECT t5.occupancyExpenses                                                                                                                                                                             ");
            sql.append("		FROM subscriber t6, subscriber_account t5                                                                                                                                                               ");
            sql.append("		WHERE t6.ID = t5.subscriberId                                                                                                                                                                           ");
            sql.append("			AND t6.mobileNo = t1.account                                                                                                                                                                        ");
            sql.append("		) AS occupancyExpenses                                                                                                                                                                                  ");
            sql.append("FROM finance_account_history t1, subscriber t2                                                                                                                                                                  ");
            sql.append("WHERE t1.account = t2.mobileNo                                                                                                                                                                                  ");
        }
        String temp = "";
        temp = " and t1.reg_city_code = " + "028";
        temp = temp + " and (t1.CreatedTime <= FROM_UNIXTIME(" + 1528214400 + ",'%Y%m%d' ) and t1.CreatedTime >= FROM_UNIXTIME(" + 1514736000 + ",'%Y%m%d' ))";
        temp = temp + " and t1.account like '%" + "13608068844" + "%'";
        sql.append(temp + " ORDER BY t1.CreatedTime DESC                                                         ");
        sql.append("LIMIT 0, 1                                                                        ");
        System.out.println(sql.toString());
    }

    @Test
    public void testStringReg() {
        String sql = "select * from table";
        String _sql = sql;
        String pattern = "\\?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(sql);
        int count = 0;
        while (m.find()) {
            _sql = _sql.replaceFirst(pattern, "#" + count);
            count++;
        }
        _sql = _sql.replaceAll("#", "\\?");
        System.out.println(_sql.trim());
    }

    @Test
    public void getLoggerTest() {
        Logger logger1 = LoggerFactory.getLogger(getClass());
        Logger logger2 = LoggerFactory.getLogger(getClass());
        Assert.assertEquals(logger1, logger2);
    }

    @Test
    public void byteTest() {
        String s = "1";
        System.out.println(Arrays.toString(s.getBytes()));
    }

    @Test
    public void testLong() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE / 60 / 24 / 365);
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
        int i1 = 1470, i2 = 1982;
//        System.out.println(arithmetic(7));
//        arithmetic(i2);
        String loginId = UUID.randomUUID().toString();
        System.out.println(loginId);
    }

    public List<Integer> arithmetic(int val) {
        List<Integer> r = new ArrayList<>();
        char[] chars = Integer.toBinaryString(val).toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if (c == '1') {
                if (i == length - 1) {
                    r.add(1);
                } else {
                    r.add(2 << (length - i - 2));
                }
            }
        }
        return r;
    }
}