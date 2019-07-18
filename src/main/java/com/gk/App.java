package com.gk;


import com.gk.udmp2.*;
import com.google.gson.JsonObject;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple1;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

    }

    public static void  dataframe1Tester(){
        //Get it from Config
        String filepath = "resources/demo1.xml";
        //get it from Config
        String endTag = "</trade>";

        //get it from Config
        String rootTag = "requestConfirmation";


        SchemaLoader schemaobj = new SchemaLoader();
        List<XpathModel> schemaList = schemaobj.getlistOfobj();
        // schemaList.forEach(x-> System.out.println(x.getColName()+" : "+x.getType()+" "+x.getXpath()));

        JsonSchemaLoader jschemaLoader = new JsonSchemaLoader();
        //actual File path will be provided in method
        JsonObject schemaJsonObj = jschemaLoader.getschemaObject("");
        // System.out.println(xpathObj.getColName());

        XMLProcessor xmlProcessor = new XMLProcessor();
        List<List<Object>> xmlvalue = xmlProcessor.processXML(filepath, endTag, rootTag, schemaList);
        // xmlvalue.forEach(x -> System.out.println(x.toString()));

        //create Dataframe for Spark use
        DataFrameBuilder dfb = new DataFrameBuilder();
        dfb.createdDataframe(xmlvalue, schemaJsonObj);
    }
}
