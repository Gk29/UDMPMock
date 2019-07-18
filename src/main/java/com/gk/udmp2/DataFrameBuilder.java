package com.gk.udmp2;


import com.google.gson.JsonObject;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;

import java.util.List;
import java.util.stream.Collectors;

public class DataFrameBuilder {


    //    public void createdDataframe(List<List<Object>> lists, String schema){
    public void createdDataframe(List<List<Object>> lists, JsonObject schema) {

        SparkConf conf = new SparkConf().setAppName("javasc").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();


//        List<StructField> fields = new ArrayList<>();
//        StructField tradeDateField=DataTypes.createStructField("tradeDate",DataTypes.StringType,true);
//        StructField PartRefField=DataTypes.createStructField("PartRef",DataTypes.StringType,true);
//        StructField tradeIdField=DataTypes.createStructField("tradeIdField",DataTypes.createArrayType(DataTypes.StringType),true);
//        StructField salaryField=DataTypes.createStructField("salaryField",DataTypes.createArrayType(DataTypes.StringType),true);
//        StructField roleField=DataTypes.createStructField("roleField",DataTypes.createArrayType(DataTypes.StringType),true);
//
//        fields.add(tradeDateField);
//        fields.add(PartRefField);
//        fields.add(tradeIdField);
//        fields.add(salaryField);
//        fields.add(roleField);
//StructType struct =new StructType((StructField[])fields.toArray());

        System.out.println(schema.toString());

        StructType struct = (StructType) DataType.fromJson(schema.toString());
        System.out.println(struct);

        // Convert records of the RDD  to Rows
        spark.createDataFrame(
                sc.parallelize(lists.stream()
                        .map(row -> RowFactory.create(row.toArray()))
                        .collect(Collectors.toList()))
                , struct).show(false);

        // spark.createDataFrame(rdds.rdd(),schema)


    }
}
