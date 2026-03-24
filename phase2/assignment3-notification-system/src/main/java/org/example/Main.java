package org.example;

import org.example.config.NotificationConfig;
import org.example.interfaces.Schedulable;
import org.example.interfaces.Sendable;
import org.example.model.NotificationMessage;
import org.example.notifier.EmailNotifier;
import org.example.notifier.PushNotifier;
import org.example.notifier.SlackNotifier;
import org.example.notifier.SmsNotifier;

import org.example.repositoy.NotificationMessageRepository;
import org.example.repositoy.impl.NotificationMessageRepositoryImpl;
import org.example.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        NotificationMessageRepository repo = new NotificationMessageRepositoryImpl();

        EmailNotifier email = new EmailNotifier(repo);
        SmsNotifier sms = new SmsNotifier(repo);
        PushNotifier push = new PushNotifier(repo);
        SlackNotifier slack = new SlackNotifier(repo);

        NotificationConfig config = new NotificationConfig(Map.of(
                "email", true,
                "sms", true,
                "push", false,
                "slack", true
        ));
        List<Sendable> sendables = List.of(
                 email,
                 sms,
                 push,
                 slack
        );
        List<Schedulable> schedulables = List.of(
                 email,
                 push
        );

        NotificationService service = new NotificationService(
              sendables,
                schedulables,
                config
        );

        service.sendPasswordReset(Map.of(
                "user", "Malak",
                "link", "reset-link"
        ));
        NotificationMessage msg = new NotificationMessage("Hi");
        service.scheduleNotification(msg, LocalDateTime.now().minusHours(1));
        service.processAllScheduled();
    }
}