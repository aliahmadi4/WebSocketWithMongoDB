# WebSocketWithMongoDB
Spring Boot project using websocket and mongoDB


Uses web socket for updating the dashboard for showing number of records in MongoDB database (user collection).
Spring boot project will query mongo every 1 sec for counting the collection size and will send it to UI.
UI uses the sockjs.js and stomp for listening and subscribing to the topic.
