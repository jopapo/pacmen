@echo off
echo Acessando diretorio...
cd "C:\arquivo\furb\materias\2008-2\sd\trab-final\PacMen\PacMen-Ghost\src"
echo Excluindo stubs antigos...
del comm\*.java /Q
echo Gerando novos stubs...
idlj -fall Ghost.idl

echo Concluido!
pause