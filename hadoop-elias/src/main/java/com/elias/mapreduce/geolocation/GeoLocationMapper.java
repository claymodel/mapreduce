package com.elias.mapreduce.geolocation;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GeoLocationMapper extends Mapper<LongWritable, Text, Text, Text> {

	public static String GEO_RSS_URI = "http://url";

	private Text geoLocationKey = new Text();
	private Text geoLocationName = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException {

		String dataRow = value.toString();

		StringTokenizer dataTokenizer = new StringTokenizer(dataRow, "\t");
		String articleName = dataTokenizer.nextToken();
		String pointType = dataTokenizer.nextToken();
		String geoPoint = dataTokenizer.nextToken();

		if (GEO_RSS_URI.equals(pointType)) {

			StringTokenizer st = new StringTokenizer(geoPoint, " ");
			String strLat = st.nextToken();
			String strLong = st.nextToken();
			double lat = Double.parseDouble(strLat);
			double lang = Double.parseDouble(strLong);
			long roundedLat = Math.round(lat);
			long roundedLong = Math.round(lang);
			String locationKey = "(" + String.valueOf(roundedLat) + ","
					+ String.valueOf(roundedLong) + ")";
			String locationName = URLDecoder.decode(articleName, "UTF-8");
			locationName = locationName.replace("_", " ");
			locationName = locationName + ":(" + lat + "," + lang + ")";
			geoLocationKey.set(locationKey);
			geoLocationName.set(locationName);
			try {
				context.write(geoLocationKey, geoLocationName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
