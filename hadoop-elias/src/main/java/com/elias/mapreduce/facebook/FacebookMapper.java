package com.elias.mapreduce.facebook;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FacebookMapper
        extends Mapper<Object, Text, Text, IntWritable> {

    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            // <word_acronym>;http://graph.facebook.com/Group Name
            String token = itr.nextToken();
            String[] strings = token.split(";");
            word.set(strings[0].trim());
            String json = new GetRequest().get(strings[1].trim());
            int likes = new ContentParser().getLikes(json);
            context.write(word, new IntWritable(likes));
        }
    }
}
