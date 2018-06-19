
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxMinTemperatureMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    int maxValue = Integer.MIN_VALUE;
    int minValue = Integer.MAX_VALUE;
    String line = value.toString();
    String year = line.substring(0, 4);
    String temperatures = line.substring(5, line.length());
    String[] temperatureArray = temperatures.toString().split(",");

    for (String temperature : temperatureArray) {
      temperature = temperature.replaceAll("\\s", "");
      maxValue = Math.max(maxValue, Integer.parseInt(temperature.toString()));
      minValue = Math.min(minValue, Integer.parseInt(temperature.toString()));
    }
    
      context.write(new Text(year), new Text(String.valueOf(maxValue) + "\t" + String.valueOf(minValue) + "\t" + String.valueOf(Math.abs(maxValue - minValue))));
    }
}

