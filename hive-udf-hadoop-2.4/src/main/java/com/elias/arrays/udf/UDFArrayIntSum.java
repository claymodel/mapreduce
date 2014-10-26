package com.elias.arrays.udf;

import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name="array_int_sum", value="_FUNC_(array<int>) - returns the sum of elements in the array")
public class UDFArrayIntSum extends UDF {
    public int evaluate(List<Integer> a) {
        if (a == null) return 0;
        int sum = 0;
        for (int i  = 0; i < a.size(); i++) {
            Integer elt = a.get(i);
            if (elt != null) sum += elt;
        }
        return sum;
    }
}
