package com.example.demo.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimeFileHandler {

    public void generateFile(String fileName, List<String> timeRanges) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String range : timeRanges) {
                writer.write(range);
                writer.newLine();
            }
        }
    }

    public List<String> readFile(String fileName) throws IOException {
        List<String> timeRanges = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                timeRanges.add(line.trim());
            }
        }
        return timeRanges;
    }

}
