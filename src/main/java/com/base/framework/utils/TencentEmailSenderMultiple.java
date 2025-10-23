package com.base.framework.utils;

import com.base.framework.exception.BusinessException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @Author: 郭郭
 * @Create: 2025/10/10
 * @Description:
 **/
public class TencentEmailSenderMultiple {

    // 腾讯企业邮箱 SMTP 配置
    private static final String SMTP_HOST = "smtp.qq.com";
    private static final String SMTP_PORT = "465";

    // 发件人信息（请替换为你的实际邮箱和授权码）
    // 替换为你的企业邮箱
    private static final String FROM_EMAIL = "783986963@qq.com";

    // 替换为你的 SMTP 授权码
    private static final String AUTHORIZATION_CODE = "zojrtzwuzdbubffg";

    // 多个收件人（To）
    private static final List<String> TO_EMAILS = new ArrayList<>();

    // 抄送（Cc） - 可选
    private static final  List<String> CC_EMAILS = new ArrayList<>();

    // 密送（Bcc） - 可选
    private static final List<String> BCC_EMAILS = new ArrayList<>();

    public static void sendEmailToMultiple(String subject, String body, List<String> toEmails) {

        if(toEmails == null || toEmails.isEmpty()) {
            throw  new BusinessException(500, "收件人邮箱不能为空");
        }

        TO_EMAILS.addAll(
                toEmails.stream().map(String::trim)
                .filter(email -> !email.isEmpty())
                .distinct()
                .collect(Collectors.toList()));

        // 设置邮件服务器属性
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // 创建认证器（使用授权码登录）
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, AUTHORIZATION_CODE);
            }
        });

        try {
            // 创建邮件对象
            Message message = new MimeMessage(session);

            // 发件人
            message.setFrom(new InternetAddress(FROM_EMAIL));

            // 设置收件人（To）
            message.setRecipients(Message.RecipientType.TO, parseAddresses(TO_EMAILS));

            // 设置抄送（Cc） - 可选
            message.setRecipients(Message.RecipientType.CC, parseAddresses(CC_EMAILS));

            // 设置密送（Bcc） - 可选
            message.setRecipients(Message.RecipientType.BCC, parseAddresses(BCC_EMAILS));

            // 邮件主题和内容
            message.setSubject(subject);
            message.setContent(body, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);

            System.out.println("✅ 邮件已成功发送到 " + String.join(", ", TO_EMAILS));

        } catch (MessagingException e) {
            System.err.println("❌ 邮件发送失败！");
            e.printStackTrace();
        }
    }

    // 工具方法：将字符串数组转换为 InternetAddress[] 数组
    private static InternetAddress[] parseAddresses(List<String> addresses) throws AddressException {
        InternetAddress[] addressArray = new InternetAddress[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            addressArray[i] = new InternetAddress(addresses.get(i));
        }
        return addressArray;
    }
}
