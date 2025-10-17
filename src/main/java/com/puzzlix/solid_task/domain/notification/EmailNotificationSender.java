package com.puzzlix.solid_task.domain.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender mailSender;

    private final String fromAddress;
    private final String defaultTo;

    public EmailNotificationSender(
            JavaMailSender mailSender,
            @Value("${gmail.from:no-reply@example.com}") String fromAddress,
            @Value("${notification.default-email-to:}") String defaultTo
    ) {
        this.mailSender = mailSender;
        this.fromAddress = fromAddress;
        this.defaultTo = defaultTo;
    }

    @Override
    public void send(String message) {

        if (defaultTo == null || defaultTo.isBlank()) {
            throw new IllegalArgumentException("notification.default-email-to 가 설정되지 않았습니다.");
        }

        // 심플 텍스트 메일(본문: message)
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromAddress);
        mail.setTo(defaultTo);
        mail.setSubject("[알림] 시스템 메시지");
        mail.setText(message);

        mailSender.send(mail);
        System.out.println("[이메일 발송] to=" + defaultTo);
    }

    @Override
    public boolean supports(String type) {
        // 이메일이라는 타입의 요청을 처리할 수 있다고 선언
        return "EMAIL".equalsIgnoreCase(type);
    }
}
