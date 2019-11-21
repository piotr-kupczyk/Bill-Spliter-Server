#!/bin/zsh

curl --request POST -H "Content-Type: application/json" -d '{"name":"Weronika", "password":"test"}' https://bill-splitter.azurewebsites.net/user &&
curl --request POST -H "Content-Type: application/json" -d '{"name":"Piotr", "password":"test"}' https://bill-splitter.azurewebsites.net/user &&
curl --request POST -H "Content-Type: application/json" -d '{"name":"Bartosz", "password":"test"}' https://bill-splitter.azurewebsites.net/user &&
curl --request POST -H "Content-Type: application/json" -d '{"name":"Aleksander", "password":"test"}' https://bill-splitter.azurewebsites.net/user &&
curl --request POST -H "Content-Type: application/json" -d '{"name":"Dominik", "password":"test"}' https://bill-splitter.azurewebsites.net/user

#curl --request POST -sL \
#    --header "Content-Type: application/json"\
#     --url 'http://localhost:8080/user'\
#     --data '{"name":"Weronika Relich"}'
#
#curl --request POST -sL \
#    --header "Content-Type: application/json"\
#     --url 'http://localhost:8080/group'\
#     --data '{"name":"Trip to Spain","imageURL":"https://www.countryflags.io/es/flat/64.png","membersIds":["04d62151-874c-4d5f-a252-24e2f40f7065"]}'
#
#

exit 0