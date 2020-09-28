package com.zhongsheng.education.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.util.UrlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Reader {

    public static String addBill(Student student, String name) {
        String fi=null;
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
        String m=   String.valueOf(student.getBill().getPaymentAmount());
        //金额转换中文
      String result=ConvertUpMoney.toChinese(m);
                System.out.println(result+"=resultresultresultresultresultresult");
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
        paraMap.put("Text10",student.getBill().getPaymentAmount());
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
        paraMap.put("Text21", "码");
        paraMap.put("Text22", name);

        //组合文件名
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(student.getSname());
        stringBuffer.append(random);
        String r= stringBuffer.toString();
        try {

//2 读入pdf表单
            PdfReader reader = new PdfReader(UrlUtil.getUrl()+"\\src\\main\\java\\com\\zhongsheng\\education\\pdf\\electronicBills.pdf");
//3 根据表单生成一个新的pdf
            File file= new File("D:\\pdf\\"+r+".pdf");
            fi="D:\\pdf\\"+r+".pdf";
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
return fi;
    }

}
