package com.elias.arrays.udf;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name="array_count_equals", value="_FUNC_(array<type>, type needle) - Counts the number of times the needle appears in the array")
public class UDFArrayCountEquals extends UDF {
    public int evaluate(List<String> a, String needle) {
        if (a == null) return 0;
        if (needle == null) return a.size();

        int ret = 0;
        for (int i = 0; i < a.size(); i++) {
            if (needle.equals(a.get(i))) ret++;
        }
        return ret;
    }
    
    public int evaluate(List<Integer> a, int needle) {
        if (a == null) return 0;

        int ret = 0;
        for (int i = 0; i < a.size(); i++) {
            if (needle == a.get(i)) ret++;
        }
        return ret;
    }
    
    public double evaluate(List<Double> a, double needle) {
        if (a == null) return 0;

        int ret = 0;
        for (int i = 0; i < a.size(); i++) {
            if (needle == a.get(i)) ret++;
        }
        return ret;
    }
}
