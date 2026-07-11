package org.example.strategy;

import org.example.model.ReportData;

public class PlainTextExportStrategy implements ExportStrategy{
    @Override
    public String export(ReportData data) {
        return "padded columns";
    }

    @Override
    public String getFileExtension() {
        return "txt";
    }
}
