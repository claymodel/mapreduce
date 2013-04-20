package com.elias.mapreduce.inverted;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterator<Text> values, Context context)
			throws IOException, InterruptedException {

		String a = "";
		boolean first = true;

		while (values.hasNext()) {
			if (!first) {
				a += ", ";
			}

			first = false;
			a += values.next().toString();
		}
		context.write(key, new Text(a));
	}
}
