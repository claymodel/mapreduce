package com.elias.mrdemo.earthquake;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EarthQuakeAnalyzer
{
    public static void main(String[] args) throws Throwable {

        Job job = new Job();
        job.setJarByClass(EarthQuakeAnalyzer.class);
        FileInputFormat.addInputPath(job, new Path("src/main/resources/eq/input"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/eq/output"));

        job.setMapperClass(EarthQuakeMapper.class);
        job.setReducerClass(EarthQuakeReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
       }

}
