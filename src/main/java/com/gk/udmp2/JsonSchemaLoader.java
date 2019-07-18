package com.gk.udmp2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;

public class JsonSchemaLoader {

    public JsonObject getschemaObject(String filePath) {
         filePath="resources/schema.json";
        JsonObject jobj = new JsonObject();
        try {
            //parsing file "schema.json"
            Object obj = new JsonParser().parse(new FileReader(filePath));
            jobj = (JsonObject) obj;
           // System.out.println(jobj.getAsJsonObject());
           // System.out.println(jobj);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobj;
    }
}
