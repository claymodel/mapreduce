package com.elias.arrays.udf;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.UDF;

public class UDFArrayCountDistinct extends UDF {
    Set<String> stringSet = new HashSet<String>();

    public int evaluate(List<String> a) {
        if (a == null) {
            return 0;
        }
        stringSet.clear();
        stringSet.addAll(a);
        return stringSet.size();
    }
}
