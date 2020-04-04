package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> void exportToXML(Class<T> tClass, String filePath) throws JAXBException;

    <T> T importFromXml(Class<T> objectClass, String filePath) throws JAXBException, FileNotFoundException;
}
