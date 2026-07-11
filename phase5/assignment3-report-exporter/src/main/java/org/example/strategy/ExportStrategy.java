package org.example.strategy;

import org.example.model.ReportData;

public interface ExportStrategy {
    String export(ReportData data);
    String getFileExtension();
}
