package com.ruozedata.hadoop.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class fn_dim_idcard15to18 extends UDF{

    public String evaluate(String input){
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(input.substring(0, 6)).append("19").append(input.substring(6));
            sb.append(getVerifyCode(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     *  获取身份证的校验码
     * @param idCardNumber
     * @return
     * @throws Exception
     */
    public static char getVerifyCode(String idCardNumber) throws Exception {
        if (idCardNumber == null || idCardNumber.length() < 17) {
            throw new Exception("不合法的身份证号码");
        }
        char[] Ai = idCardNumber.toCharArray();
        int[] Wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] verifyCode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3',
                '2' };
        int S = 0;
        int Y;
        for (int i = 0; i < Wi.length; i++) {
            S += (Ai[i] - '0') * Wi[i];
        }
        Y = S % 11;
        return verifyCode[Y];
    }

}
