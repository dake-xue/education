package com.zhongsheng.education.entiy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Pdf {

    @Value("${PDF_path}")
    private  String PDF;

    @Value("${CODE_url}")
    private  String code;

    public static String PDF_path;

    public  static  String CODE_url;

    @PostConstruct
    public void init(){
        PDF_path = PDF;
        CODE_url = code;
    }
}
