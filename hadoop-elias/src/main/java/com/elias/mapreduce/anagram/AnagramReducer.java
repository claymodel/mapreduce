package com.elias.mapreduce.anagram;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnagramReducer extends Reducer<Text, Text, Text, Text> {
        
        private Text outputKey = new Text();
        private Text outputValue = new Text();

        
        public void reduce(Text anagramKey, Iterator<Text> anagramValues,
                        Context context) throws IOException {
                String output = "";
                while(anagramValues.hasNext())
                {
                        Text anagam = anagramValues.next();
                        output = output + anagam.toString() + "~";
                }
                StringTokenizer outputTokenizer = new StringTokenizer(output,"~");
                if(outputTokenizer.countTokens()>=2)
                {
                        output = output.replace("~", ",");
                        outputKey.set(anagramKey.toString());
                        outputValue.set(output);
                        try {
							context.write(outputKey, outputValue);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
                }
        }

}
