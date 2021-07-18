package com.etong.pms.test;

import com.etong.pms.model.dto.UserInfo;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.Data;

/**
 * @ClassName CompanyInfo
 * @Author wch
 * @DATE 2019/10/11 10:29
 * @return
 */
@Data
public class CompanyInfo {

    private String companyCode ;

    private String companyName ;

    private String[] arrInfo;

    private ArrayList<UserInfo> userList;

    private HashMap<String,String> result ;

    public CompanyInfo(){
        System.out.println("========");
    }
}
