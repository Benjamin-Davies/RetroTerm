@echo off
del build /s /q
mkdir build

javac -Xlint:deprecation -d build\ src\retroterm\*.java

cd build
jar cvfe ..\RetroTerm.jar retroterm.MainWindow .\*
cd ..

echo running
java -jar RetroTerm.jar
