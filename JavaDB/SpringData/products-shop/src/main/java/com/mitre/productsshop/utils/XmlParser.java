package com.mitre.productsshop.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> void exportToXML(Class<T> tClass, String filePath) throws JAXBException;

    <T> T importFromXML(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;

}
