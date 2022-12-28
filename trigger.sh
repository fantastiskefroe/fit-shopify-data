cat example_webhook.http \
  | tail -n 1 \
  | curl -vs  -X POST  -H 'Content-Type: application/json' -d "$(</dev/stdin)\n\n" http://localhost:8080/webhook/order-updated \
  | jq 
