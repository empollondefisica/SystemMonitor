#!/bin/bash

if [[ "$#" > "0" ]]
then
    echo "iterating"
    for FILE in $@
    do
        javac -Xlint -d bin ${FILE}
    done
else
    echo "globbing"
    javac -Xlint -d bin *.java
fi
