package com.zhongsheng.education.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.zhongsheng.education.entiy.Pdf;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.service.impl.AliPayServiceImpl;
import com.zhongsheng.education.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Reader {

    private static final boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");

    public static String addBill(Student student, String name) {
        Logger logger = LoggerFactory.getLogger(Reader.class);
        String fi = null;
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHH");
        // 转换为字符串
        String formatDate = format.format(new Date());
        String formatDate1 = format1.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        StringBuffer sr = new StringBuffer();
        sr.append(formatDate1);
        sr.append(random);
        sr.toString();
        String m = String.valueOf(student.getJiaofeijine());
        //金额转换中文
        String result = ConvertUpMoney.toChinese(m);
        //1 准备要填充的数据
        Map paraMap = new HashMap();
        paraMap.put("Text1", student.getSname());
        paraMap.put("Text2", student.getSex());
        paraMap.put("Text3", student.getSchoolname());
        paraMap.put("Text4", student.getDepartment());
        paraMap.put("Text5", student.getGrade());
        paraMap.put("Text6", student.getAdmissiontime());
        paraMap.put("Text7", student.getExaminationtime());
        paraMap.put("Text8", student.getClasses());
        paraMap.put("Text9", student.getIdcard());
        paraMap.put("Text10", student.getJiaofeijine());
        paraMap.put("Text11", result);
        paraMap.put("Text12", student.getRemarks());
        paraMap.put("Text13", formatDate);
        paraMap.put("Text14", sr);
        paraMap.put("Text15", student.getPhone());
        paraMap.put("Text16", student.getMajor());
        paraMap.put("Text17", student.getIntentionmajor());
        paraMap.put("Text18", student.getSubject());
        paraMap.put("Text19", student.getCampus());
        paraMap.put("Text20", student.getFamilyInfo().getFphone());
       // paraMap.put("Text21", erweima);
        paraMap.put("Text22", name);

        //组合文件名
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(student.getSname());
        stringBuffer.append(random);
        String r = stringBuffer.toString();
        PdfReader reader = null;
        logger.info(student.toString());
        try {
            //判断省市区
            if (student.getArea() == 1 && student.getArea()!=null) {
                //2 读入pdf表单
                ClassPathResource classPathResource = new ClassPathResource("/static/pdf/electronicBillsH.pdf");
                byte[]  keywordsData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
                reader = new PdfReader(keywordsData);
            } else if (student.getArea() == 2 &&  student.getArea()!=null) {
                //2 读入pdf表单
                ClassPathResource classPathResource = new ClassPathResource("/static/pdf/electronicBills.pdf");
                byte[]  keywordsData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
                reader = new PdfReader(keywordsData);
            } else if (student.getArea() == 3 &&  student.getArea()!=null) {
                //2 读入pdf表单
                ClassPathResource classPathResource = new ClassPathResource("/static/pdf/electronicBillsS.pdf");
                byte[]  keywordsData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
                reader = new PdfReader(keywordsData);
            }
             //3 根据表单生成一个新的pdf
            File file = new File(Pdf.PDF_path+ r + ".pdf");
            fi = Pdf.PDF_path + r + ".pdf";
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
            PdfStamper ps = new PdfStamper(reader, new FileOutputStream(file));
            //4 获取pdf表单
            AcroFields s = ps.getAcroFields();
            //5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            BaseFont bf = null;
                if(isWin){
                    bf =  BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                }else {
                    bf =  BaseFont.createFont("/usr/share/fonts/chinese/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                }

            s.addSubstitutionFont(bf);

            //插入二维码
            // 书签名
            String fieldName = "Text21";
            // 通过域名获取所在页和坐标，左下角为起点
            int pageNo = s.getFieldPositions(fieldName).get(0).page;
            Rectangle signRect = s.getFieldPositions(fieldName).get(0).position;
            float x = signRect.getLeft();
            float y = signRect.getBottom();

            //获取二维码路径
            String erweima = QrCodeTest.erweima(student.getPhone());
            // 读图片
            Image image = Image.getInstance(erweima);
            // 获取操作的页面
            PdfContentByte under = ps.getOverContent(pageNo);
            // 根据域的大小缩放图片
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            // 添加图片
            image.setAbsolutePosition(x, y);
            under.addImage(image);


//6遍历pdf表单表格，同时给表格赋值
            Map fieldMap = s.getFields();

            Set set = fieldMap.entrySet();

            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {

                Entry entry = (Entry) iterator.next();

                String key = (String) entry.getKey();
                if (paraMap.get(key) != null) {

                    // s.setField(key, ""+paraMap.get(key.toUpperCase()));
                    s.setField(key, "" + paraMap.get(key));

                }

            }

            ps.setFormFlattening(true); // 这句不能少
            ps.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fi;
    }

}
