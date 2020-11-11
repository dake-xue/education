package com.zhongsheng.education.pdf;
import com.zhongsheng.education.entiy.Pdf;
import org.springframework.core.io.ClassPathResource;

public class QrCodeTest {
    private static final boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
    public static String erweima(String phone){
        String codeUrl = Pdf.CODE_url;
        // 存放在二维码中的内容
        String text = "";
        String destPath = "";
        String imgPath = "";
        try {
            if(isWin){

                text = "http://"+"mxxbz9pk.xiaomy.net:36569"+"/zhongsheng/student/stuToStudentDetails?phone="+phone;
                // 嵌入二维码的图片路径
                imgPath = new ClassPathResource("static/pdf/nei.png").getFile().getPath();
                // 生成的二维码的路径及名称
                destPath = new ClassPathResource("static/pdf/erweima.png").getFile().getPath();
            }else {

                text = "http://"+codeUrl+"/zhongsheng/student/stuToStudentDetails?phone="+phone;
                // 嵌入二维码的图片路径
                imgPath = "/usr/code/nei.png";
                // 生成的二维码的路径及名称
                destPath = "/usr/code/erweima.png";
            }
            //生成二维码
            QRCodeUtil.encode(text, imgPath, destPath, true);
            // 解析二维码
            QRCodeUtil.decode(destPath);
        }catch (Exception e){

        }
        //返回二维码地址
        return destPath;

    }

}
