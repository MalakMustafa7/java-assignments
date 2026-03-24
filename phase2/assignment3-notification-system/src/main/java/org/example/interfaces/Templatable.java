package org.example.interfaces;

import java.util.Map;

public interface Templatable {

   String renderTemplate(String templateName, Map<String,String> vars);
}
