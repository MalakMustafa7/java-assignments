package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.strategy.ExportStrategy;
@AllArgsConstructor
@Setter
@Getter
public class ReportExporter {
    private ExportStrategy exportStrategy;
    public String export(ReportData data){
        return exportStrategy.export(data);
    }
}
