# Notification

Define the next properties:
azure.eventhubs.connection-string=your_connection_string
azure.eventhubs.eventhub-name=your_eventhub_name

#Curl
curl --location 'http://localhost:8080/api/v1.0/send' --header 'Content-Type: application/json' --data '"Hello"'
