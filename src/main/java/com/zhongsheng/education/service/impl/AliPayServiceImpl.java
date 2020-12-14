package com.zhongsheng.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.pdf.BillNumber;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.AliPayService;
import com.zhongsheng.education.service.BillService;
import com.zhongsheng.education.service.OrderService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ligen
 * @title: AliPayService
 * @projectName demo
 * @description:
 * @date 2019/6/119:11
 */
@Transactional
@Service
public class AliPayServiceImpl implements AliPayService {
    Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Value("${ali_return_url}")
    private String return_url;

    @Value("${ali_notify_url}")
    private String notify_url;

    @Value("${product_no}")
    private String product_no;

    @Value("${time_express}")
    private String time_express;

    @Value("${gatewayUrl}")
    private String gatewayUrl;

    @Value("${app_id}")
    private String app_id;

    @Value("${private_key}")
    private String private_key;

    @Value("${ali_public_key}")
    private String ali_public_key;

    @Value("${hebei_app_id}")
    private String hebei_app_id;

    @Value("${hebei_private_key}")
    private String hebei_private_key;

    @Value("${hebei_ali_public_key}")
    private String hebei_ali_public_key;

    @Value("${shanxi_app_id}")
    private String shanxi_app_id;

    @Value("${shanxi_private_key}")
    private String shanxi_private_key;

    @Value("${shanxi_ali_public_key}")
    private String shanxi_ali_public_key;

    @Value("${sign_type}")
    private String sign_type;

    @Value("${charset}")
    private String charset;

    @Value("${BILL_path}")
    private String BILL_path;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BillService billService;

    //    支付服务
    public String aliPay(Order order)throws AlipayApiException {
        if(orderService.searchByOrderNum(order.getOrder_number())==null){
            //插入订单
            order.setCreate_time(new Date());
            orderService.addOrder(order);
        }
        // 构建支付数据信息
        Map<String, String> data = new HashMap<>();
        data.put("subject",order.getGoods_name()); //订单标题
        data.put("out_trade_no",order.getOrder_number()); //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复      //此处模拟订单号为时间
        data.put("timeout_express", time_express); //该笔订单允许的最晚付款时间
        data.put("total_amount", order.getPrice()); //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        data.put("product_code", product_no); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        DefaultAlipayClient alipayRsa2Client;
        //1.河北
        if(order.getArea()==1){
            logger.info("河北。。。。。。");
            //构建客户端
            alipayRsa2Client = new DefaultAlipayClient(
                    gatewayUrl,
                    hebei_app_id,
                    hebei_private_key,
                    "json",
                    charset,
                    hebei_ali_public_key,
                    sign_type);
        }
        //2.河南
        else if(order.getArea()==2){
            logger.info("河南。。。。。。");
            //构建客户端
            alipayRsa2Client = new DefaultAlipayClient(
                    gatewayUrl,
                    app_id,
                    private_key,
                    "json",
                    charset,
                    ali_public_key,
                    sign_type);
        }
        //3.陕西
        else {
            logger.info("陕西。。。。。。");
            //构建客户端
            alipayRsa2Client = new DefaultAlipayClient(
                    gatewayUrl,
                    shanxi_app_id,
                    shanxi_private_key,
                    "json",
                    charset,
                    shanxi_ali_public_key,
                    sign_type);
        }


//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();// APP支付
        //AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 网页支付
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();  //移动h5
        request.setNotifyUrl(notify_url);
        request.setReturnUrl(return_url);
        request.setBizContent(JSON.toJSONString(data));
        logger.info("data："+ JSON.toJSONString(data));
        String response = alipayRsa2Client.pageExecute(request).getBody();
        return response;
    }

    //异步回调服务
    public String aliNotify(HttpServletRequest request) throws Exception {
        logger.info("进入异步回调。。。。。。");
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params,ali_public_key,charset,sign_type); //调用SDK验证签名
        logger.info("验证签名："+signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("交易状态："+trade_status);
            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                logger.info("执行：TRADE_FINISHED");
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //交易成功（或支付成功）
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                logger.info("用户付款成功。。。执行：TRADE_SUCCESS。。。");
                //修改订单状态
                Order order =  orderService.searchByOrderNum(out_trade_no);
                order.setStatus(1);
                int n = orderService.updateStatus(order);
                logger.info("订单表影响行数："+n);
                //判断sunm的长度是否小于11，如果小于11则是寒假班学生（寒假班学生票据中snum为手机号）
                Student student = null;
                if(order.getsNum().length()<11){
                    //修改学生状态
                    student = studentService.selectStudent(order.getsNum());
                    student.setStatus(1);
                    int i =  studentService.updateStatus(student);
                    logger.info("学生表影响行数："+i);
                }else {
                    WinterStu stu =  studentService.selectWinterStudentByPhone(order.getsNum());
                    studentService.updateWinterStatus(stu.getPhone());
                    student = new Student();
                    logger.info("金额为："+stu.getJiaofeijine());
                    if(stu.getJiaofeijine().contains("440")){
                        student.setRemarks("寒假班住宿费340+押金100");
                    }else {
                        student.setRemarks("寒假零基础英语班230+押金100");
                    }
                    student.setArea(2);
                    student.setCampusmanager(" ");
                    student.setFamilyInfo(new FamilyInfo());
                    student.setSnum(stu.getPhone());
                    student.setSname(stu.getSname());
                    student.setJiaofeijine(stu.getJiaofeijine());
                    student.setSchoolname(stu.getSchoolname());
                    student.setSex(stu.getSex());
                    student.setPhone(stu.getPhone());
                    student.setIdcard(stu.getIdcard());
                }
                //
                //获取票据随机数
                Bnumber billnumber= BillNumber.billNumber();
                //生成票据
                String s = Reader.addBill(student,student.getCampusmanager(),billnumber);

                Bill bill = new Bill();
                boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
                String ima = "";
                //根据系统判断
                if (isWin){
                    ima =  PDF2IMAGE.pdf2Image(s, System.getProperty("user.dir")+"\\src\\main\\resources\\static\\pdfToImage", 300);
                    bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.getPngName(ima));
                }else {
                    ima = PDF2IMAGE.pdf2Image(s, "/usr/img", 300);
                    bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.linuxGetPngName(ima));
                }
                logger.info("ima："+ima);
                bill.setPaymentAmount(total_amount);
                bill.setSnum(student.getSnum());
                bill.setRemark(student.getRemarks());
                bill.setArea(student.getArea());
                bill.setCampusid(student.getCampusid());
                bill.setSchoolid(student.getSchoolid());
                bill.setIntotime(new Date());
                bill.setBillnumber(billnumber.getBillnumber());
                //插入票据表
                int j =billService.addBillInfo(bill);
                logger.info("票据表影响行数："+j);
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            return "success";
        } else {//验证失败
            return "fail";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

    }

    //同步回调
    public String aliReturn(HttpServletRequest request) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,ali_public_key,charset,sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            //String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            //String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            Order order =  orderService.searchByOrderNum(out_trade_no);
            return  order.getsNum();
        }else {
            return "验签失败";
        }
    }
}
