package com.zhongsheng.education.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Reader {

    public static void main(String[] args) {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHH");
        // 转换为字符串
        String formatDate = format.format(new Date());
        String formatDate1 = format1.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        StringBuffer sr=new StringBuffer();
        sr.append(formatDate1);
        sr.append(random);
        sr.toString();
        //1 准备要填充的数据
        Map paraMap = new HashMap();
        paraMap.put("Text1", "易烊千玺");
        paraMap.put("Text2", "男");
        paraMap.put("Text3", "河南省贸易职业技术学院");
        paraMap.put("Text4", "院系");
        paraMap.put("Text5", "班级");
        paraMap.put("Text6", "2020");
        paraMap.put("Text7", "2021");
        paraMap.put("Text8", "1");
        paraMap.put("Text9", "4222222222222222");
        paraMap.put("Text10", "3000");
        paraMap.put("Text11", "叁仟圆");
        paraMap.put("Text12", "那部分v和技术v安康均可点击支付");
        paraMap.put("Text13", formatDate);
        paraMap.put("Text14", sr);
        paraMap.put("Text15", "15333333333");
        paraMap.put("Text16", "英语");
        paraMap.put("Text17", "英语");
        paraMap.put("Text18", "英语");
        paraMap.put("Text19", "龙子湖");
        paraMap.put("Text20", "12345678909");
        paraMap.put("Text21", "码");
        paraMap.put("Text22", "胖虎");

        try {

//2 读入pdf表单
            PdfReader reader = new PdfReader("D:\\workspace\\education\\src\\main\\java\\com\\zhongsheng\\education\\pdf\\electronicBills.pdf");
//3 根据表单生成一个新的pdf
            File file= new File("D:\\pdf\\bi.pdf");
            if(file.exists()){
                file.delete();
                file.createNewFile();
            }else{
                file.createNewFile();
            };
            PdfStamper ps = new PdfStamper(reader, new FileOutputStream(file));
 //4 获取pdf表单
            AcroFields s = ps.getAcroFields();
//5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            s.addSubstitutionFont(bf);
//6遍历pdf表单表格，同时给表格赋值
            Map fieldMap = s.getFields();
            System.out.println(fieldMap.toString());

            Set set = fieldMap.entrySet();
            System.out.println(set.toString());

            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {

                Entry entry = (Entry) iterator.next();

                String key = (String) entry.getKey();
                System.out.println(key);
                if (paraMap.get(key) != null) {

                    // s.setField(key, ""+paraMap.get(key.toUpperCase()));
                    s.setField(key, "" + paraMap.get(key));

                }

            }

            ps.setFormFlattening(true); // 这句不能少

            ps.close();

            reader.close();

        } catch (IOException e) {

// TODO 自动生成的 catch 块

            e.printStackTrace();

        } catch (DocumentException e) {

// TODO 自动生成的 catch 块

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
