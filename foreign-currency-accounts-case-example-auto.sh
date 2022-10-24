#!/bin/bash
appHost=${1:-localhost}
appPort=${2:-8080}

printf "\n///\nREGISTER\n///\n"
curl -sS -X PUT "http://$appHost:$appPort/api/v1/accounts/register" -d '{"customerInfo": {"pesel": "81021964593","name": "Tester","surname": "Testerson","birthdate": "2002-02-21" },"startingSum": 10}' -H 'Content-Type: application/json' | jq .
printf "\n///\nGET INFO/SUBACCOUNTS\n///\n"
curl -sS -X GET "http://$appHost:$appPort/api/v1/accounts/81021964593" -H  "accept: */*" | jq .
printf "\n///\nEXCHANGE 10 PLN TO USD\n///\n"
curl -sS -X POST "http://$appHost:$appPort/api/v1/accounts/81021964593/funds/exchange"  -d '{"currency": "PLN", "amount": 10, "target": "USD" }' -H  'Content-Type: application/json' | jq .
printf "\n///\nGET INFO/SUBACCOUNTS AFTER EXCHANGE\n///\n"
curl -sS -X GET "http://$appHost:$appPort/api/v1/accounts/81021964593" -H  "accept: */*" | jq .