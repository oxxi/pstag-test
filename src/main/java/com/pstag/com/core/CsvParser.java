package com.pstag.com.core;

import com.pstag.com.model.CarBrand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public CsvParser() {
    }

    public List<CarBrand> parseCsvToCarBrand(InputStream csvInputStream) throws Exception{
        List<CarBrand> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvInputStream))) {
            String line;
            boolean isFirst = true;

            while ((line = br.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                CarBrand info = new CarBrand();
                String rawBrand = parts[0].trim().replace("\"", "");
                info.setBrand(rawBrand);
                String rawDate = parts[1].trim().replace("\"", "");
              //  System.out.println("row date: "+ parts[1].trim());
               info.setReleaseDate(LocalDate.parse(rawDate, formatter));

                list.add(info);
            }
        }

        return list;
    }
}
