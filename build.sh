#!/bin/bash

javac -classpath `hadoop classpath` *.java
jar cvf Temperature.jar *.class
jar cvf MaxMinTemperature.jar *.class
rm *.class
