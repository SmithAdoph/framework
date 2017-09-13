package com.adoph.framework.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
  public static final void debug(String text)
  {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = format.format(new Date());
    System.out.println("[debug] " + now + " " + text);
  }

  public static final void info(String text)
  {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = format.format(new Date());
    System.out.println("[info] " + now + " " + text);
  }

  public static final void error(String text)
  {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = format.format(new Date());
    System.err.println("[error] " + now + " " + text);
  }

  public static final void error(String text, Exception e)
  {
    StringWriter writer = new StringWriter();
    e.printStackTrace(new PrintWriter(writer));
    error(text + "\n" + writer.toString());
  }

  public static void main(String[] args) {
    info("...........");
    error("...........");
  }
}