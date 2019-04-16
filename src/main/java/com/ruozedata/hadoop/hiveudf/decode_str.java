package com.ruozedata.hadoop.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class decode_str extends UDF {

        public String evaluate(Object... args) {
            if (args.length % 2 != 0) {
                System.out.println("输入的参数个数错误，应为偶数");
            }
            int number = args.length;
            Object result = null;
            int flag = number - 1;

            int i = 1;

            if (args[0] instanceof Integer) {
                args[0] = Double.valueOf(Integer.valueOf(args[0].toString()));
            }
            while (i < flag) {

                if (args[i] instanceof Integer) {
                    args[i] = Double.valueOf(Integer.valueOf(args[i].toString()));
                }

                if (String.valueOf(args[i]).equals(String.valueOf(args[0]))) {
                    result = args[i + 1];
                    break;
                } else {
                    i += 2;
                }
            }
            if (result == null)
                result = args[flag];

            return String.valueOf(result);
        }

}
