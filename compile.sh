#!/bin/bash

if [[ "$#" > "0" ]]
then
    echo "iterating"
    for FILE in $@
    do
        javac -d bin ${FILE}
    done
else
    echo "globbing"
    javac -d bin *.java
fi
