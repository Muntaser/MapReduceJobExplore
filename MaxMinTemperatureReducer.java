// cc MaxMinTemperatureReducer Reducer for maximum and minimum temperature 
// vv MaxMinTemperatureReducer
import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxMinTemperatureReducer
  extends Reducer<Text, Text, Text, Text> {
  
  //@Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
   
    String maxMinDifference = "";
    for (Text value : values) {
	  maxMinDifference = value.toString();
    } 
    context.write(key, new Text(maxMinDifference));
  }

}
// ^^ MaxMinTemperatureReducer
