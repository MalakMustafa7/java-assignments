package org.example.strategy;

import org.example.model.ReportData;

public class CsvExportStrategy implements ExportStrategy{
    @Override
    public String export(ReportData data) {
        return "comma-separated";
    }

    @Override
    public String getFileExtension() {
        return "csv";
    }
}
