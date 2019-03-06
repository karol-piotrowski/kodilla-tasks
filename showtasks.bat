call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD.BAT finished with errors - breaking work
goto fail

:openbrowser
start /max http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo An error ocurred while opening the browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.