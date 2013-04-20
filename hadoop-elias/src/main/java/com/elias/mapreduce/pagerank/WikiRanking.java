package com.elias.mapreduce.pagerank;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WikiRanking {

	private static NumberFormat nf = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		WikiRanking pageRanking = new WikiRanking();

		pageRanking.runXmlParsing("wiki/in", "wiki/ranking/iter00");

		int runs = 0;
		for (; runs < 5; runs++) {
			pageRanking.runRankCalculation(
					"wiki/ranking/iter" + nf.format(runs), "wiki/ranking/iter"
							+ nf.format(runs + 1));
		}

		pageRanking.runRankOrdering("wiki/ranking/iter" + nf.format(runs),
				"wiki/result");

	}

	public void runXmlParsing(String inputPath, String outputPath)
			throws IOException {
		JobConf conf = new JobConf(WikiRanking.class);

		conf.set(InputProcessing.START_TAG_KEY, "<page>");
		conf.set(InputProcessing.END_TAG_KEY, "</page>");

		// Input / Mapper
		FileInputFormat.setInputPaths(conf, new Path(inputPath));
		conf.setInputFormat(InputProcessing.class);
		conf.setMapperClass(WikiEdgesMapper.class);

		// Output / Reducer
		FileOutputFormat.setOutputPath(conf, new Path(outputPath));
		conf.setOutputFormat(TextOutputFormat.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setReducerClass(WikiEdgesReducer.class);

		JobClient.runJob(conf);
	}

	private void runRankCalculation(String inputPath, String outputPath)
			throws IOException {
		JobConf conf = new JobConf(WikiRanking.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(inputPath));
		FileOutputFormat.setOutputPath(conf, new Path(outputPath));

		conf.setMapperClass(PageRankMapper.class);
		conf.setReducerClass(PageRankReducer.class);

		JobClient.runJob(conf);
	}

	private void runRankOrdering(String inputPath, String outputPath)
			throws IOException {
		JobConf conf = new JobConf(WikiRanking.class);

		conf.setOutputKeyClass(FloatWritable.class);
		conf.setOutputValueClass(Text.class);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(inputPath));
		FileOutputFormat.setOutputPath(conf, new Path(outputPath));

		conf.setMapperClass(RankingMapper.class);

		JobClient.runJob(conf);
	}

}
