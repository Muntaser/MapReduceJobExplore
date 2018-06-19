#!/bin/bash

hadoop fs -rm -r outputFinal
hadoop fs -rm -r output
hadoop fs -rm -r input
hadoop fs -mkdir input
hadoop fs -put input/Ltemps.txt input
hadoop jar Temperature.jar Temperature input output 
hadoop fs -put output/part-r-00000 output
hadoop jar MaxMinTemperature.jar MaxMinTemperature output outputFinal 
hadoop fs -cat output/part-r-00000
hadoop fs -cat outputFinal/part-r-00000
