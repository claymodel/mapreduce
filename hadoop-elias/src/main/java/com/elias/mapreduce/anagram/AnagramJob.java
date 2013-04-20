package com.elias.mapreduce.anagram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AnagramJob {

        public static void main(String[] args) throws Exception{
            Configuration conf = new Configuration();
            String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
            if (otherArgs.length != 2) {
                System.err.println("Usage: com.elias.mapreduce.anagram <in> <out>");
                System.exit(2);
            }
                Job job = new Job(conf, "anagram count");

                job.setJarByClass(AnagramJob.class);
                job.setMapperClass(AnagramMapper.class);
                job.setCombinerClass(AnagramReducer.class);
                job.setReducerClass(AnagramReducer.class);

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(Text.class);
                
                FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
                FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
                System.exit(job.waitForCompletion(true) ? 0 : 1);
                
        }

}
