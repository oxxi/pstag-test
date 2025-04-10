package com.pstag.com.core;

import com.pstag.com.model.Car;
import com.pstag.com.model.Cars;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class XmlParser {

    public XmlParser() {
    }


    public List<Car> parseXmlToCar(InputStream xmlInputStream) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Cars.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Cars cars = (Cars) unmarshaller.unmarshal(xmlInputStream);
        if(cars == null || cars.getCarList().isEmpty()){
            throw new RuntimeException("error while parsing file");
        }
        return cars.getCarList();
    }
}
