// cc Temperature Application to find the all temperatures per year in the weather dataset
// vv Temperature
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;

public class Temperature extends Configured implements Tool {

  public int run(String[] args) throws Exception {

    if (args.length != 2) {
      System.err.println("Usage: Temperature <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = new Job(getConf());
    job.setJarByClass(Temperature.class);
    job.setJobName("Get all temperatures per year");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(TemperatureMapper.class);
    job.setReducerClass(TemperatureReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    boolean success = job.waitForCompletion(true);
    return success ? 0 : 1;

  }

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(new Configuration(), new Temperature(), args);
    System.exit(res);
  }
}
// ^^ Temperature
