package org.example.factory;

import org.example.strategy.*;
import org.example.utility.ErrorMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ExportStrategyFactory {
    private final Map<String, Supplier<ExportStrategy>> registry=new HashMap<>();
    public ExportStrategyFactory(){
        register("csv", CsvExportStrategy::new);
        register("html", HtmlExportStrategy::new);
        register("txt", PlainTextExportStrategy::new);
        register("mark", MarkdownExportStrategy::new);
        register("json", JsonExportStrategy::new);

    }
    public void register(String fileExtension, Supplier<ExportStrategy> strategySupplier){
        registry.put(fileExtension.toLowerCase(),strategySupplier);
    }
   public ExportStrategy createStrategy(String fileExtension){
        Supplier<ExportStrategy> strategySupplier = registry.get(fileExtension);
        if(strategySupplier == null){
            throw new IllegalArgumentException(ErrorMessages.UNKNOWN_EXTENSION);
        }
        return strategySupplier.get();
    }
}
