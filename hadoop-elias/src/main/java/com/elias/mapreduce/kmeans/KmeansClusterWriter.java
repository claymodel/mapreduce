package com.elias.mapreduce.kmeans;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.Pair;
import org.apache.mahout.common.iterator.FileLineIterable;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileIterable;
import org.apache.mahout.common.mapreduce.VectorSumReducer;

import org.apache.mahout.math.SequentialAccessSparseVector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.map.OpenIntIntHashMap;


public class KmeansClusterWriter {

	public static void main(String[] args) throws IOException {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		String output = "data/kmeans-test-cluster/part-00000";

		try (SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf, new Path(output),
				Text.class, Kluster.class);) {
			double[][] centroids = { { 1, 1 }, { 4, 4 }, { 9, 9 } };
			for (int i = 0; i < centroids.length; i++) {
				Vector vec = new RandomAccessSparseVector(centroids[i].length);
				vec.assign(centroids[i]);
				Kluster cluster = new Kluster(vec, i, new EuclideanDistanceMeasure());
				writer.append(new Text(cluster.getIdentifier()), cluster);
			}
		}
	}
}
