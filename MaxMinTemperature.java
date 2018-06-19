   
// cc MaxMinTemperature Application to find the maximum temperature in the weather dataset
// vv Max and Min Temperature
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;

public class MaxMinTemperature extends Configured implements Tool {

  public int run(String[] args) throws Exception {

    if (args.length != 2) {
      System.err.println("Usage: MaxMinTemperature <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = new Job(getConf());
    job.setJarByClass(MaxMinTemperature.class);
    job.setJobName("Maximum and Minimum temperature and their difference");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(MaxMinTemperatureMapper.class);
    job.setReducerClass(MaxMinTemperatureReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    boolean success = job.waitForCompletion(true);
    return success ? 0 : 1;

  }

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(new Configuration(), new MaxMinTemperature(), args);
    System.exit(res);
  }
}
// ^^ MaxMinTemperature



