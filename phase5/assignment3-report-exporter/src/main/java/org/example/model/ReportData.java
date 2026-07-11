package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ReportData {
    private String title;
    private List<String> headers;
    private List<List<String>> rows;
    private Map<String, String> metadata;
}
