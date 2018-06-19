// cc TemperatureReducer Reducer for finding temperatures example
// vv TemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TemperatureReducer
  extends Reducer<Text, Text, Text, Text> {
  
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
    
    String tempReadings = "";
    for (Text value : values) {
		tempReadings += value.toString() + ",";
    }
    
    context.write(key, new Text(tempReadings.substring(0, tempReadings.length() - 1)));

  }
}
// ^^ TemperatureReducer
