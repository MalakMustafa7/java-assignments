package org.example.strategy;

import org.example.model.ReportData;

public class HtmlExportStrategy implements ExportStrategy{
    @Override
    public String export(ReportData data) {
        return "proper HTML table with styling";
    }

    @Override
    public String getFileExtension() {
        return "html";
    }
}
