#!/usr/bin/env bash
BUILDPATH=../build
PROGRAM_NAME=server/*.java

cd src

javac -classpath .:classes:/opt/pi4j/lib/'*' -d "${BUILDPATH}" $PROGRAM_NAME

javac -classpath .:classes:/opt/pi4j/lib/'*' -d "${BUILDPATH}" server/filter/*.java

if [ $? -eq 0 ]
then
  echo "compile worked!"
fi
