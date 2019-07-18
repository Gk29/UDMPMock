package com.gk.udmp2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XMLProcessor {

    public List<List<Object>> processXML(String filepath, String endTag, String rootTag, List<XpathModel> schemaList) {
        XpathParser xpathParser = new XpathParser();
        String xml = "";
        List<Object> rowValue = new ArrayList<>();
        List<List<Object>> onexmldata = new ArrayList<>();
        File file = new File(filepath);

        try {
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter(endTag);
            while (scanner.hasNext()) {

                xml = scanner.next();


                if (!xml.contains(rootTag)) {
                    xml = xml + endTag;

                    rowValue = xpathParser.getXpathValue(xml, schemaList, "http://ns");
                } else if (xml.contains(endTag.substring(2, endTag.length() - 1)) && !xml.contains("\\" + rootTag)) {
                    xml = xml.substring(xml.indexOf(endTag.substring(2, endTag.length())) - 1, xml.length());
                    xml = xml + endTag;
                    rowValue = xpathParser.getXpathValue(xml, schemaList, "http://ns");

                }
                if (rowValue != null) {
                    onexmldata.add(rowValue);
                    rowValue = null;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return onexmldata;
    }
}
