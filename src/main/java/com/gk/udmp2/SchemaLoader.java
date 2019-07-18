package com.gk.udmp2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SchemaLoader {

    public List<XpathModel> getlistOfobj() {
        List<XpathModel> schemaList = new ArrayList<XpathModel>();


        try {
            JsonArray jsonArray = new JsonArray();
            Object obj = new JsonParser().parse(new FileReader("resources/schema.json"));
            System.out.println(obj);
            JsonObject jobj = (JsonObject) obj;
           // System.out.println(jobj.get("name"));
            JsonElement jsonele = jobj.get("fields");
            //System.out.println(jsonele);
            jsonArray=jsonele.getAsJsonArray();
           // System.out.println(jsonArray);
            jsonArray.forEach(jsonobj -> {
                //System.out.println("-------------------------------------------------");
                JsonObject jobj2 = (JsonObject) jsonobj;
               //  System.out.println(jobj2);
                //System.out.println("=====================");
                String colName = jobj2.get("name").toString();
                String xpath = ((JsonObject) jobj2.get("metadata")).get("xpath").toString();
                Object type = jobj2.get("type");

                XpathModel xpathobj = new XpathModel();
                xpathobj.setColName(colName);
                xpathobj.setXpath(xpath);

                if (type.toString().equals("\"string\"")) {
                    xpathobj.setType("string");
                } else {
                    String type_1 = ((JsonObject) type).get("type").toString();
                    if (type_1.toString().equals("\"array\"")) {
                        xpathobj.setType("type_1");
                    } else {
                        String type_2 = ((JsonObject) ((JsonObject) type).get("type")).get("type").toString();
                        xpathobj.setType("type_2");
                    }
                }
                schemaList.add(xpathobj);
            });
            return schemaList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schemaList;
    }
}

