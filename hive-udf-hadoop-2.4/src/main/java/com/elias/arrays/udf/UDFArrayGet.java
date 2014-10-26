package com.elias.arrays.udf;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name="array_get", value="_FUNC_(array<text>, int) - returns the nth object in the array")
public class UDFArrayGet extends UDF {
    private Text out = new Text();
    
    public Text evaluate(List<String> a, int offset) {
        if (a == null) return null;
        out.set(a.get(offset));
        return out;
    }
}
