#/bin/bash

if [ ! -f asg.cliche-110413.jar ]
then
    curl -O https://cliche.googlecode.com/files/asg.cliche-110413.jar 
fi

mvn install:install-file -Dfile=asg.cliche-110413.jar -DgroupId=asg.cliche -DartifactId=cliche -Dversion=110413 -Dpackaging=jar
