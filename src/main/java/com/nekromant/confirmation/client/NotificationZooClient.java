package com.nekromant.confirmation.client;

import dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationZooClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.url.notificationZoo}")
    private String url;

    private final String smsCodeUrl = url + "/api/notify";
    private final String sendEmailCodePath = smsCodeUrl + "/sendEmail";
    private final String sendSmsCodePath = smsCodeUrl + "/sendSms";

    public void sendEmail(NotificationDTO notificationDTO) {
        restTemplate.postForEntity(sendEmailCodePath, notificationDTO, NotificationDTO.class);
    }

    public void sendSms(NotificationDTO notificationDTO) {
        restTemplate.postForEntity(sendSmsCodePath, notificationDTO, NotificationDTO.class);
    }
}
