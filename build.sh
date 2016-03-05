#!/usr/bin/env bash
THE_BUILDPATH=../build
PROGRAM_NAME=server.Controller.java

cd src

javac -classpath .:classes:/opt/pi4j/lib/'*' -d "${THE_BUILDPATH}" $PROGRAM_NAME

if [ $? -eq 0 ]
then
  echo "compile worked!"
fi
