set class=com.docdata.smartdelivery.metapackmock.MetapackMockService
set clpth=./target/metapackmock-1.0-SNAPSHOT/WEB-INF/classes
set resourcedir=./src/main/webapp
set outsourcedir=./src/main/java
set outdir=./target/metapackmock-1.0-SNAPSHOT/WEB-INF/classes
"%JAVA_HOME%\bin\wsgen.exe" -cp "%clpth%" -wsdl -keep -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%" %class%