package com.pstag.com;

import com.pstag.com.core.CsvParser;
import com.pstag.com.core.XmlParser;
import com.pstag.com.model.Car;
import com.pstag.com.model.CarBrand;
import com.pstag.com.service.CarFilterService;
import com.pstag.com.service.CarsDataService;
import com.pstag.com.service.CarsFormatterOutService;
import com.pstag.com.service.CarsSortService;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        String filterArg = "--filter=date<2023-01-01";
//        String filterValue = filterArg.replace("--filter=", "");
//
//        String sortArg = "--sort=price_desc";
//        String sortValue = sortArg.replace("--sort=", "");
//
//        String outArg = "--output=table";
//        String outValue = outArg.replace("--output=", "");
//        boolean useCurrencyByType = false;

        String filterValue = getOptionValue(args, "filter"); //--filter=
        String sortValue = getOptionValue(args, "sort");      // --sort=
        String outValue = getOptionValue(args, "output");     // --output=
        boolean useCurrencyByType = hasFlag(args, "currency=type"); // --currency=type


       try{

           //parse XML
           XmlParser xmlParser = new XmlParser();

           List<Car> cars = xmlParser.parseXmlToCar(getPath("carsType.xml"));
           //parse CSV
           CsvParser csvParser = new CsvParser();
           List<CarBrand> carBrands = csvParser.parseCsvToCarBrand(getPath("CarsBrand.csv"));
           //merge info
           CarsDataService service = new CarsDataService();
           service.mergeData(cars, carBrands);
           // display price by Type
           if(useCurrencyByType){
               service.displayPriceByType(cars);
           }

           //apply filters
           CarFilterService filterService = new CarFilterService();
           List<Car> filteredCars = filterService.applyFilters(cars, filterValue);
           // apply sort
           CarsSortService sortService = new CarsSortService();
           sortService.sort(filteredCars, sortValue);
           // output format
           CarsFormatterOutService outService = new CarsFormatterOutService();
           outService.print(filteredCars, outValue);
          // System.out.println(filteredCars);



       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    public static InputStream getPath(String fileName) {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new RuntimeException("Resource not found: " + fileName);
        }
        return stream;
    }


    public static String getOptionValue(String[] args, String optionName) {
        for (String arg : args) {
            if (arg.startsWith("--" + optionName + "=")) {
                return arg.substring(("--" + optionName + "=").length()).trim();
            }
        }
        return null;
    }

    public static boolean hasFlag(String[] args, String flagName) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--" + flagName)) {
                return true;
            }
        }
        return false;
    }
}