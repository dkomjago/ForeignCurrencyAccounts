#!/bin/bash
applicationPort=${1:-8080}
./gradlew clean build && java -Dserver.port="$applicationPort" -jar build/libs/ForeignCurrencyAccounts-0.0.1-SNAPSHOT.jar &