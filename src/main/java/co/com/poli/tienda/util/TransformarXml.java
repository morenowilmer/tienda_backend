package co.com.poli.tienda.util;

import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class TransformarXml {

    public String convertir(String xmlData, String xslTemplatePath) throws Exception {

        InputStream xslStream = getClass().getClassLoader().getResourceAsStream(xslTemplatePath);
        if (xslStream == null) {
            throw new Exception("No se encontró el archivo XSLT en: " + xslTemplatePath);
        }

        StreamSource xmlSource = new StreamSource(new StringReader(xmlData));
        StreamSource xslSource = new StreamSource(xslStream);

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);

        transformer.transform(xmlSource, result);

        return writer.toString();
    }
}
