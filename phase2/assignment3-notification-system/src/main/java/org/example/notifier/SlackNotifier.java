package org.example.notifier;

import lombok.extern.slf4j.Slf4j;
import org.example.enums.DeliveryStatus;
import org.example.interfaces.Notifier;
import org.example.interfaces.Sendable;
import org.example.interfaces.Templatable;
import org.example.model.NotificationMessage;
import org.example.repositoy.NotificationMessageRepository;

import java.util.Map;
@Slf4j
public class SlackNotifier implements Sendable, Templatable, Notifier {
    private final NotificationMessageRepository repository;

    public SlackNotifier(NotificationMessageRepository repository){
        this.repository = repository;
    }
    @Override
    public void send(NotificationMessage message) {
        log.info("Sending Slack: {}", message.getContent());
        message.setDeliveryStatus(DeliveryStatus.SENT);
        repository.saveMessageStatus(message.getMessageId(), DeliveryStatus.SENT);

    }

    @Override
   public String renderTemplate(String templateName, Map<String,String> vars) {
        StringBuilder rendered = new StringBuilder("Template: " + templateName);
        for (Map.Entry<String, String> entry : vars.entrySet()) {
            rendered.append(" | ").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return rendered.toString();
    }

    @Override
    public String getType() {
        return "slack";
    }
}
