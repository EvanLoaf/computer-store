:: Some QoL for archiving old logs

set year=%date:~-4%
set month=%date:~4,2%
set day=%date:~7,2%
set hour=%time:~0,2%
set minute=%time:~3,2%
set second=%time:~6,2%

if %month%==01 set monthname=Jan
if %month%==02 set monthname=Feb
if %month%==03 set monthname=Mar
if %month%==04 set monthname=Apr
if %month%==05 set monthname=May
if %month%==06 set monthname=Jun
if %month%==07 set monthname=Jul
if %month%==08 set monthname=Aug
if %month%==09 set monthname=Sep
if %month%==10 set monthname=Oct
if %month%==11 set monthname=Nov
if %month%==12 set monthname=Dec

set logs_folder=logs
set archive_folder=old

if not exist ..\%logs_folder%\%archive_folder% mkdir ..\%logs_folder%\%archive_folder%

rar a ..\%logs_folder%\%archive_folder%\logs_cleanup_%monthname%_%day%_%year%_%hour%_%minute%_%second%.gz ..\%logs_folder%\*.log

del ..\%logs_folder%\*.log