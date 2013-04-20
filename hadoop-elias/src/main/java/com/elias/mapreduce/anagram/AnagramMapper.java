package com.elias.mapreduce.anagram;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class AnagramMapper extends 
                Mapper<LongWritable, Text, Text, Text> {

        private Text sortedText = new Text();
        private Text orginalText = new Text();

        
        public void map(LongWritable key, Text value, Context context)
                        throws IOException, InterruptedException {

                String word = value.toString();
                char[] wordChars = word.toCharArray();
                Arrays.sort(wordChars);
                String sortedWord = new String(wordChars);
                sortedText.set(sortedWord);
                orginalText.set(word);
                context.write(sortedText, orginalText);
        }

}
