THE_BUILDPATH=../build
PROGRAM_NAME=Controller.java
cd src

javac -d "${THE_BUILDPATH}" $PROGRAM_NAME

if [ $? -eq 0 ]
then
  echo "compile worked!"
fi