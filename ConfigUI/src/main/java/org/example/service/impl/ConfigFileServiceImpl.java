package org.example.service.impl;

import org.example.service.IConfigFileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigFileServiceImpl implements IConfigFileService {

    private static final String CONFIG_FILE_PATH = "C:\\Users\\mi\\Downloads\\Telegram Desktop\\config.sh";


    public Map<String, String> readConfigFile() {
        Map<String, String> configData = new HashMap<>();

        try {
            Path configFilePath = Paths.get(CONFIG_FILE_PATH);
            checkConfigFile(configFilePath);

            Files.lines(configFilePath).forEach(line -> {
                if (line.contains("=")) {
                    String[] split = line.split("=");
                    if (split.length == 2) {
                        String key = split[0].trim();
                        String value = split[1].replace("'", "").trim();
                        configData.put(key, value);
                    }
                }
            });


        } catch (Exception e) {
        }

        return configData;
    }

    public void updateConfigFile(String field, String value) {
        Path configFilePath = Paths.get(CONFIG_FILE_PATH);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(configFilePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> modifiedLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("=", 2); // Разделяем строку на ключ и значение
            if (parts.length >= 1) {
                String currentKey = parts[0].trim();
                if (currentKey.equals(field)) {
                    // Формируем новую строку с экранированием кавычек при необходимости
                    String newLine = field + "='" + value.replace("'", "\\'") + "'";
                    modifiedLines.add(newLine);
                    continue;
                }
            }
            modifiedLines.add(line); // Сохраняем оригинальную строку, если не наш ключ
        }

        try {
            Files.write(configFilePath, modifiedLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void updateConfigFile(Map<String, String> configData, String key, String value){
//
//        configData.put(key, value);
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
//            for (Map.Entry<String, String> entry : configData.entrySet()) {
//                writer.write(entry.getKey() + "='" + entry.getValue() + "'");
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void checkConfigFile(Path path) throws FileNotFoundException {

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Config.sh не найден");

        }
    }


}
