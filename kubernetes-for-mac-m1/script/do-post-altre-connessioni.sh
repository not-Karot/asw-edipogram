#!/bin/bash

# aggiunge nuove connessioni 

curl -X POST "http://edipogram:80/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"tipo\": \"Indovinello\"}"
echo 

curl -X POST "http://edipogram:80/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Donatella\", \"tipo\": \"Anagramma\"}"
echo 
