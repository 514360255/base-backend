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
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class TencentEmailSenderMultiple {

    // 腾讯企业邮箱 SMTP 配置（常量仍可 static）
    private static final String SMTP_HOST = "smtp.qq.com";
    private static final String SMTP_PORT = "465";

    // 发件人信息（请替换为你的实际邮箱和授权码）
    private static final String FROM_EMAIL = "783986963@qq.com";
    private static final String AUTHORIZATION_CODE = "zojrtzwuzdbubffg";

    // 实例变量（不再是 static！）
    private final Properties props;
    private final Session session;

    // 构造函数：初始化邮件会话（只依赖不变的配置）
    public TencentEmailSenderMultiple() {
        // 设置邮件服务器属性
        props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // 创建会话（认证器使用静态常量，没问题）
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, AUTHORIZATION_CODE);
            }
        });
    }

    /**
     * 发送邮件到多个收件人（实例方法）
     */
    public void sendEmailToMultiple(String subject, String body, List<String> toEmails) {
        if (toEmails == null || toEmails.isEmpty()) {
            throw new BusinessException(500, "收件人邮箱不能为空");
        }

        // ✅ 每次调用都是局部变量，不会累积
        List<String> cleanedToEmails = toEmails.stream()
                .map(String::trim)
                .filter(email -> !email.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        try {
            // 创建邮件对象（每次都是新的）
            Message message = new MimeMessage(session);

            // 发件人
            message.setFrom(new InternetAddress(FROM_EMAIL));

            // 设置收件人（To）
            message.setRecipients(Message.RecipientType.TO, parseAddresses(cleanedToEmails));

            // 如果你有 Cc / Bcc，也可以作为参数传入
            // 这里为简化，只处理 To
            // message.setRecipients(Message.RecipientType.CC, ...);
            // message.setRecipients(Message.RecipientType.BCC, ...);

            // 邮件主题和内容
            message.setSubject(subject);
            message.setContent(body, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);

            System.out.println("✅ 邮件已成功发送到 " + String.join(", ", cleanedToEmails));

        } catch (MessagingException e) {
            System.err.println("❌ 邮件发送失败！");
            e.printStackTrace();
            // 建议抛出自定义异常或封装错误
            throw new RuntimeException("邮件发送失败", e);
        }
    }

    // 工具方法：将字符串列表转换为 InternetAddress[] 数组
    private InternetAddress[] parseAddresses(List<String> addresses) throws AddressException, MessagingException {
        return addresses.stream()
                .map(email -> {
                    try {
                        return new InternetAddress(email);
                    } catch (AddressException e) {
                        throw new RuntimeException("无效的邮箱地址: " + email, e);
                    }
                })
                .toArray(InternetAddress[]::new);
    }

    // 可选：支持 Cc 和 Bcc 的重载方法
    public void sendEmailToMultiple(String subject, String body, List<String> toEmails,
                                    List<String> ccEmails, List<String> bccEmails) {
        // 你可以在这里添加 Cc 和 Bcc 的逻辑
        // 为简洁起见，此处省略，可按需扩展
    }
}