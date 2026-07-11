package org.example;

import org.example.factory.ExportStrategyFactory;
import org.example.model.ReportData;
import org.example.model.ReportExporter;
import org.example.strategy.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ExportStrategyFactory factory = new ExportStrategyFactory();

        ReportData data = new ReportData(
                "Sales Report",
                List.of("Product", "Price"),
                List.of(
                        List.of("Phone", "1000"),
                        List.of("Laptop", "2000")
                ),
                Map.of("createdBy", "Malak")
        );



         ReportExporter exporter =
                new ReportExporter(factory.createStrategy("csv"));

        System.out.println(exporter.export(data));

        exporter.setExportStrategy(factory.createStrategy("json"));
        System.out.println(exporter.export(data));

        exporter.setExportStrategy(factory.createStrategy("html"));
        System.out.println(exporter.export(data));


    }
}