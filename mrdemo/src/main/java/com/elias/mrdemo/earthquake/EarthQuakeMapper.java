package com.elias.mrdemo.earthquake;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EarthQuakeMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {

        if (key.get() > 0)
        {

            String[] parsedData = value.toString().split(",");
            String date = DateCoverter.convertDate(parsedData[0]);
            
            if(date != null)
            {
                context.write(new Text(date), new IntWritable(1));
            }
            

        }
    }

}
