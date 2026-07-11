package org.example.strategy;

import org.example.model.ReportData;

public class JsonExportStrategy implements ExportStrategy{
    @Override
    public String export(ReportData data) {
        return "proper JSON with escaping";
    }

    @Override
    public String getFileExtension() {
        return "json";
    }
}
