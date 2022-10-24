///BUILD and RUN
1. run 'foreign currency accounts app build and run'.sh (with no arguments for running on port '8080' otherwise write port as argument)
!!!WARNING!!! requires java 8+ and internet connection

///RUN DEMO AUTOMATIC
1. install jq
2. run 'foreign currency accounts use case example auto'.sh (with no arguments for 'localhost:8080' otherwise arguments are {1}host {2}port)
!!!WARNING!!! registers 81021964593 PESEL and converts 10 PLN to USD

///RUN DEMO MANUAL
1. run 'foreign currency accounts app use case example manual'.sh (with no arguments for 'localhost:8080' otherwise arguments are {1}host {2}port)
2. enter values as prompted by console

for easier api testing visit swagger ui endpoint /swagger-ui.html

///ADDITIONAL ASSUMPTIONS MADE
1.Gradle build tool
2.Kotlin language
3.polish characters are unicode compliant
4.all time values are local
5.database is in-memory H2
6.Liquibase db management tool
7.Starting currencies limited to PLN and USD
