#!/bin/bash
appHost=${1:-localhost}
appPort=${2:-8080}

printf "\n///\nREGISTER\n///\n"
printf "\n!!!Please enter pesel!!!\n"
read -r pesel
printf "\n!!!Please name!!!\n"
read -r name
printf "\n!!!Please surname!!!\n"
read -r surname
printf "\n!!!Please birthdate (ISO format)!!!\n"
read -r birthdate
printf "\n!!!Starting sum PLN!!!\n"
read -r startingSum
curl -sS -X PUT "http://$appHost:$appPort/api/v1/accounts/register" -d "{\"customerInfo\": {\"pesel\": \"$pesel\",\"name\": \"$name\",\"surname\": \"$surname\",\"birthdate\": \"$birthdate\" },\"startingSum\": $startingSum}" -H 'Content-Type: application/json' | jq .
printf "\n///\nGET INFO/SUBACCOUNTS\n///\n"
curl -sS -X GET "http://$appHost:$appPort/api/v1/accounts/$pesel" -H  "accept: */*" | jq .
printf "\n///\nEXCHANGE PLN TO USD\n///\n"
printf "\n!!!Please enter starting currency!!!\n"
read -r startingCurr
printf "\n!!!Please enter amount!!!\n"
read -r amount
printf "\n!!!Please enter target currency!!!\n"
read -r targetCurr
curl -sS -X POST "http://$appHost:$appPort/api/v1/accounts/$pesel/funds/exchange"  -d "{\"currency\": \"$startingCurr\", \"amount\": $amount, \"target\": \"$targetCurr\" }" -H  'Content-Type: application/json' | jq .
printf "\n///\nGET INFO/SUBACCOUNTS AFTER EXCHANGE\n///\n"
curl -sS -X GET "http://$appHost:$appPort/api/v1/accounts/$pesel" -H  "accept: */*" | jq .