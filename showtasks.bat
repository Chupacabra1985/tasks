call runcrud.bat
if "%ERRORLEVEL%" == "0" goto website
echo.
echo RUN RUNCRUD has errors - breaking work
goto fail

:website
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors. Work finished.

:end
echo.
echo Work is finished.