#!/bin/bash

rm -r outputFinal
rm -rf output
hadoop jar Temperature.jar Temperature -fs file:/// -jt local input output
hadoop jar Temperature.jar Temperature -fs file:/// -jt local output outputFinal
cat output/part-r-00000
cat outputFinal/part-r-00000


