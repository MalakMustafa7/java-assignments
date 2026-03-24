package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class NotificationConfig {
    private final Map<String,Boolean> enabledChannels ;

    public boolean isEnabled(String type){
        return enabledChannels.getOrDefault(type,false) ;
    }
}
