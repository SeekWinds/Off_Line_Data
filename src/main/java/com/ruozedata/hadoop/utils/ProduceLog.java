package com.ruozedata.hadoop.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class ProduceLog {

    public static String create_cdn(){
        String stringarray[] = {"baidu","tencent","wangyi","ali","jingdong"};
        Random random = new Random();
        String result = stringarray[random.nextInt(5)];
        return result;
    }

    public static String create_url() {
        String result = "";
        Random random = new Random();
        for (int i = 0; i <= 35; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                result += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                result += String.valueOf(random.nextInt(10));
            }
        }
        result = "http://v1.go2yd.com/user_upload/"+result;
        return result;
    }


    /*
     * 将十进制转换成IP地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    public static String create_ip() {
        // ip范围
        int[][] range = {{607649792, 608174079}, // 36.56.0.0-36.63.255.255
                {1038614528, 1039007743}, // 61.232.0.0-61.237.255.255
                {1783627776, 1784676351}, // 106.80.0.0-106.95.255.255
                {2035023872, 2035154943}, // 121.76.0.0-121.77.255.255
                {2078801920, 2079064063}, // 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497}, // 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785}, // 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137}, // 182.80.0.0-182.92.255.255
                {-770113536, -768606209}, // 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    public static String create_time() {
        String result_time = "";
        try {
            DateFormat targetFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
            DateFormat sourceFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Random rndYear = new Random();
            int year = rndYear.nextInt(2) + 2017;  //生成[2017,2018]的整数；年
            Random rndMonth = new Random();
            int int_month = rndMonth.nextInt(12) + 1;
            String month = String.format("%02d",int_month);                //生成[1,12]的整数；月
            Random rndDay = new Random();
            int int_Day = rndDay.nextInt(30) + 1;       //生成[1,30)的整数；日
            String Day = String.format("%02d",int_Day);
            Random rndHour = new Random();
            int int_hour = rndHour.nextInt(23);       //生成[0,23)的整数；小时
            String hour = String.format("%02d",int_hour);
            Random rndMinute = new Random();
            int int_minute = rndMinute.nextInt(60);   //生成分钟
            String minute = String.format("%02d",int_minute);
            Random rndSecond = new Random();
            int int_second = rndSecond.nextInt(60);   //秒
            String second = String.format("%02d",int_second);
            result_time = String.valueOf(year)+month+Day+hour+minute+second;
            result_time = targetFormat.format(sourceFormat.parse(result_time));
            result_time = "["+result_time+" +0800]";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result_time;
    }

    public static void main(String[] args) {

        for(int i = 0 ; i <= 100; i++){
            try {
                String cdn = create_cdn();
                String region = "CN";
                String level = "E";
                String time = create_time();
                String ip = create_ip();
                String domain = "v2.go2yd.com";
                String url = create_url();
                String traffic = String.valueOf((int)((Math.random()*9+1) * 100000));

                StringBuilder builder = new StringBuilder("");
                builder.append(cdn).append("\t")
                        .append(region).append("\t")
                        .append(level).append("\t")
                        .append(time).append("\t")
                        .append(ip).append("\t")
                        .append(domain).append("\t")
                        .append(url).append("\t")
                        .append(traffic);

                String logdata = builder.toString();

                FileWriter fileWriter = new FileWriter("D:\\LegainProject\\g6-train-hadoop\\g6-train-hadoop\\src\\test\\json\\log_data.log",true);
                fileWriter.write(logdata+"\n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
