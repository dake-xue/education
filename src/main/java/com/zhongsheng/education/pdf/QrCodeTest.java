package com.zhongsheng.education.pdf;

import com.zhongsheng.education.util.UrlUtil;

public class QrCodeTest {

    public static String erweima(String phone){
        // 存放在二维码中的内容
        String text = "http://localhost:8080/zhongsheng/student/stuToStudentDetails?phone="+phone;
        // 嵌入二维码的图片路径
        String imgPath = UrlUtil.getUrl()+"\\src\\main\\java\\com\\zhongsheng\\education\\pdf\\nei.png";
        // 生成的二维码的路径及名称
        String destPath = UrlUtil.getUrl()+"\\src\\main\\java\\com\\zhongsheng\\education\\pdf\\erweima.png";
        try {
            //生成二维码
            QRCodeUtil.encode(text, imgPath, destPath, true);
            // 解析二维码
            String str = QRCodeUtil.decode(destPath);
            // 打印出解析出的内容
            System.out.println(str);
        }catch (Exception e){

        }
        //返回二维码地址
        return destPath;

    }

}
