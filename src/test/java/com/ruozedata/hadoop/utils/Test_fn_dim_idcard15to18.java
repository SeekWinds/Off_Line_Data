package com.ruozedata.hadoop.utils;

import com.ruozedata.hadoop.hiveudf.fn_dim_idcard15to18;

public class Test_fn_dim_idcard15to18 {

    public static void main(String[] args) {
        fn_dim_idcard15to18 fn_dim_idcard15to18 = new fn_dim_idcard15to18();
        System.out.println(fn_dim_idcard15to18.evaluate("330324360802695"));
    }

}
