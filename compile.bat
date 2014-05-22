@echo off

SET Lib_Name=AppTag
SET Lib_Source_Dir="%CD%"
SET B4A_SLC=D:\Basic4android\SimpleLibraryCompiler\LibraryCompiler.exe

echo Library Name = %Lib_Name%
echo Library Source Directory = %Lib_Source_Dir%
echo B4A Library Compiler = %B4A_SLC%
echo.

REM ------------------------------------------
REM Compile library
REM ------------------------------------------
echo.
%B4A_SLC% %Lib_Name% %Lib_Source_Dir%

echo. 
echo Done!
echo. 

pause