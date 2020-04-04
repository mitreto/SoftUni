package com.mitre.productsshop.utils;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    public <T> void exportToXML(Class<T> tClass, String filePath) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(tClass, new File(filePath));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T importFromXML(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new FileReader(filePath));
    }
}
