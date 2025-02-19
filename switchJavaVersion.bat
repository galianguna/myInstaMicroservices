echo off
set /p port=Enter 17 or 8?
if %port%==8 (
echo 8 Java Version
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_271"
set PATH=%JAVA_HOME%\bin;%PATH%
) else (
echo 17 Java Version
set JAVA_HOME="C:\Program Files\Java\jdk-17"
set PATH=%JAVA_HOME%\bin;%PATH%
)
echo Try once it will change!
pause