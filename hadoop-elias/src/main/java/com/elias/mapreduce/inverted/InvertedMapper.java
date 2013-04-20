package com.elias.mapreduce.inverted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

public class InvertedMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line, " <>=\"\'&\\,;");
		ArrayList<String> b = new ArrayList<String>();
		String a = "";

		while (tokenizer.hasMoreTokens()) {
			String buffer = tokenizer.nextToken();
			if (buffer.equalsIgnoreCase("href")) {
				a = tokenizer.nextToken();
			} else {
				b.add(buffer);
			}
		}

		for (int i = 0; i < b.size(); i++) {
			output.collect(new Text(b.get(i)), new Text(a));
		}
	}
}