@echo off

rem Executar ORB:
rem orbd –ORBInitialPort 2000;
rem Executar Servidor:
rem java BoasVindas.servidor –ORBInitialPort 2000;
rem Executar Cliente:
rem java cliente –ORBInitialPort 2000 –ORBInitialHost localhost;

echo ORB inicializado...
orbd -ORBInitialPort 2000