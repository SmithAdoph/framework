package com.adoph.framework.test.design.pattern.observer.v3;

import com.adoph.framework.util.PropertiesUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/21
 */
public class Test {

    public static void main(String[] args) {
        Child c = new Child();
        String observers = PropertiesUtils.loadProperties("test/observer.properties").getProperty("observers");
        String[] observerClassFullNames = observers.split(";");
        for (String observerClassFullName : observerClassFullNames) {
            try {
                c.addListener((EventListener) Class.forName(observerClassFullName).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new Thread(c).start();
    }

}
