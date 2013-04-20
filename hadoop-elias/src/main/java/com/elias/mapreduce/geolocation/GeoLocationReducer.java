package com.elias.mapreduce.geolocation;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GeoLocationReducer extends Reducer<Text, Text, Text, Text> {

	private Text outputKey = new Text();
	private Text outputValue = new Text();

	public void reduce(Text geoLocationKey, Iterator<Text> geoLocationValues,
			Context context) throws IOException, InterruptedException {

		String outputText = "";
		while (geoLocationValues.hasNext()) {
			Text locationName = geoLocationValues.next();
			outputText = outputText + locationName.toString() + " ,";
		}
		outputKey.set(geoLocationKey.toString());
		outputValue.set(outputText);
		context.write(outputKey, outputValue);
	}

}
