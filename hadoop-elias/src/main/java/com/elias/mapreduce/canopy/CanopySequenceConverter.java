package com.elias.mapreduce.canopy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.apache.hadoop.io.SequenceFile.CompressionType;

public class CanopySequenceConverter {

	public static void main(String[] args) throws IOException {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		String input = "data/canopy-sample-data.csv";
		String output = "data/canopy-sample-vector/vector.seq";

		try (
				BufferedReader reader = new BufferedReader(new FileReader(input));
				SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf,
						new Path(output), LongWritable.class, VectorWritable.class)) {
			String line;
			long counter = 0;
			while ((line = reader.readLine()) != null) {
				String[] c = line.split(",");
				double[] d = new double[c.length];
				for (int i = 0; i < c.length; i++)
					d[i] = Double.parseDouble(c[i]);
				Vector vec = new RandomAccessSparseVector(c.length);
				vec.assign(d);

				VectorWritable writable = new VectorWritable();
				writable.set(vec);
				writer.append(new LongWritable(counter++), writable);
			}
		}
	}

}
