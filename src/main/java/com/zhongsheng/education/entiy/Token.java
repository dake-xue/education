package com.zhongsheng.education.entiy;

import lombok.Data;

@Data
public class Token {
    private String session_key;
    private String openid;
}
