package org.example.service;

import org.example.config.NotificationConfig;
import org.example.interfaces.Schedulable;

import org.example.interfaces.Notifier;
import org.example.interfaces.Sendable;
import org.example.interfaces.Templatable;
import org.example.model.NotificationMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class NotificationService {

    private final List<Sendable> notifiers;
    private final List<Schedulable> schedulableNotifiers;
    private final NotificationConfig config;
     public NotificationService(List<Sendable>notifiers
              ,List<Schedulable> schedulableNotifiers
               ,NotificationConfig config){
         this.notifiers = notifiers;
         this.schedulableNotifiers = schedulableNotifiers;
         this.config = config;

     }

    public void sendNotification(NotificationMessage message) {
        notifiers.stream()
                .filter(this::isEnabled)
                .forEach(notifier -> notifier.send(message));
    }

    public void scheduleNotification(NotificationMessage message, LocalDateTime dateTime) {
        schedulableNotifiers.stream()
                .filter(this::isEnabled)
                .forEach(notifier -> notifier.schedule(message, dateTime));
    }

    public void processAllScheduled(){
         schedulableNotifiers.stream()
                 .filter(this::isEnabled)
                 .forEach(Schedulable::processPending);
    }
    public void sendPasswordReset(Map<String, String> vars){
         String templateName = "password-reset";
         notifiers.stream()
                 .filter(this::isEnabled)
                 .forEach(n->{
                     String content;
                     if(n instanceof Templatable t){
                         content = t.renderTemplate(templateName,vars);
                     }else{
                         content = "Reset your password";
                     }
                     NotificationMessage message = new NotificationMessage(content);
                     n.send(message);
                 });
    }
    private boolean isEnabled(Object notifier) {
        if(notifier instanceof Notifier n){
            return config.isEnabled(n.getType());
        }
        return false;
    }



}
