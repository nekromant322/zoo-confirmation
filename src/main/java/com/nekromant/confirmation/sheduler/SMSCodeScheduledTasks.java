package com.nekromant.confirmation.sheduler;

import com.nekromant.confirmation.dao.SMSCodeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SMSCodeScheduledTasks {

    @Autowired
    private SMSCodeDAO smsCodeDAO;

    /**
     * Job по удалению смс кодов с истекшим сроком действия
     */
    @Scheduled(fixedRateString = "${app.const.smsCodeRemoveSchedulerDelay}")
    private void clearExpiredCodes() {
        smsCodeDAO.findAll().forEach(item -> {
            if (item.getExpiredDate().isBefore(LocalDateTime.now()))
                smsCodeDAO.delete(item);
        });
    }
}
