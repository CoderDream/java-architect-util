@echo off

if "%1" == "h" goto begin
mshta vbscript:createobject("wscript.shell").run("%~nx0 h",0)(window.close)&&exit
:begin
::
d:
cd D:\03_Dev\pdi-ce-9.0.0.0-423\data-integration
kitchen /file:D:\04_GitHub\java-architect-util\kettle\Lesson_01\0102_Job.kjb /level:Base>>D:\04_GitHub\java-architect-util\kettle\Lesson_01\test2.log