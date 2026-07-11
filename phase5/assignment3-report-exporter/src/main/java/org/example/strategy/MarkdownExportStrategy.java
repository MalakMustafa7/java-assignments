package org.example.strategy;

import org.example.model.ReportData;

public class MarkdownExportStrategy implements ExportStrategy{
    @Override
    public String export(ReportData data) {
        return "pipe-table format";
    }

    @Override
    public String getFileExtension() {
        return "mark";
    }
}
