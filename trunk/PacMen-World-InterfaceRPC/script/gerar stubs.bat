@echo off

echo Acessando diretorio...
cd "C:\arquivo\furb\materias\2008-2\sd\trab-final\PacMen\PacMen-World-InterfaceRPC\src\br\pacmen\world\rpc\comm"
echo Excluindo stubs antigos...
del *.java /Q
echo Gerando novos stubs...
"C:\arquivo\furb\materias\2008-2\sd\trab-final\PacMen\PacMen-World-InterfaceRPC\script\jrpcgen" ..\..\..\..\..\PacMen.x

echo Concluido!
pause