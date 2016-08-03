package com.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


public class Record{
	/**
	* Stores the data in a LinkedHashMap with the keys as the data types and the values as the actual values
	*/
    private LinkedHashMap<Attribute, String> data;
    
	/**
	* Initializes data
	*/
    public Record(){
        data = new LinkedHashMap<>();
    }

	/**
	* adds an entry to the hashmap
	*/
    public void add(Attribute attribute, String value){
            data.put(attribute, value);
    }
	
	/**
	* Returns the hashmap so data can be retrieved from it
	*/
    public HashMap <Attribute, String> getData(){
            return data;
    }

	/**
	* Input key, get value
	*/
    public String getValue(Attribute att) {
        if(data.get(att) == null) {
            System.out.println(att);
        }
        if(att instanceof ContinuousAttribute) {
            System.out.println(att.getClass());
            ContinuousAttribute cAtt = (ContinuousAttribute) att;
            return cAtt.getBucket(data.get(att));
        }
        return data.get(att);
    }

    public void changeKeys(List<Attribute> atts) {
        Set<Attribute> keys = data.keySet();
        if(keys.size() != atts.size()) {
            throw new IllegalArgumentException("cannot change keys because new keylist is not correct length");
        }
        for(int i = 0; i < atts.size(); i++) {

        }

    }

	/**
	* Input key, get value
	*/
    public String getClassificationValue(DataSet set) {
            return data.get(set.getClassification());
    }

	/**
	* Returns the string representation of the class
	*/
    public String toString() {
        String str = "";
        for(Attribute att: data.keySet()) {
            String key = att.getName();
            String value = data.get(att);
            str += "["+key + ": " + value + "], ";
        }
        return str.substring(0, str.length()-2);
    }
}
