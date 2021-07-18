package com.etong.pms.test;

import java.util.*;

/**
 * @ClassName Test
 * @Author wch
 * @DATE 2019/10/15 20:13
 * @return
 */
public class Test {

    public static void main(String[] args) {
        CompanyInfo c = null;

        c = Optional.ofNullable(c).orElseGet(()->new CompanyInfo());

        System.out.println(c);

    }

}
