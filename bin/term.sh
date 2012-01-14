#!/bin/sh

# export JAVA_HOME=/usr/local/java
# PATH=/usr/local/java/bin:${PATH}

# build the classpath 
for i in `ls ../lib/**/*.jar`
do
  CP=${CP}:${i}
done

CP=$CP:../classes

export CP
echo $CP

java -cp $CP org.hyperdata.scute.terminal.JConsoleExample 
