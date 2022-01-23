# WebSocketWithMongoDB
Spring Boot project using websocket and mongoDB

Technologies used:
- Websocket
- MongoDB with auth && encryption configured
- Thymeleaf


What you can do:
- Register new user and save it into Mongodb
- In Dashboard you can see realtime number of users saved database
- You can message to all users who are in Messaging tab (if connected by a name)

Description:
UI uses the sockjs.js and Stomp for listening and subscribing to topics.

We have two topics in this app which can be subscribed:
- /topic/usersCount: which will return count of users immediately after a user added
- /topic/messages: which will return chats once a user send a chat

We have one Stomp endpoint which will consume chats and then sends to subscribers
- /app/chat: which will get message object {from: username, text: messageBody}

Enable Authentication on mongodb:
using linux commands:
1-start db without access control
	mongo
2-create a new user in admin db (administrator with required roles)
	use admin
	db.createUser({user:"xxx",pwd:"xxx", roles:["readWriteAnyDatabase", "userAdminAnyDatabase"]})
3-shutdown mongo
	db.adminCommand( { shutdown: 1 } )
4-strt the mongod with --auth
	in windows add this to the mongod.cfg
		security: 
			authorization:enabled
	in linux run the following command
		mongod --auth --port 27017 --dbpath /var/lib/mongodb
		
5-authenticate
	db.auth({user:"xxx",pwd:"xxx"})
Note: alternative way: mongo --port 27017  --authenticationDatabase "admin" -u "myUserAdmin" -p

Enable SSL on MongoDB:
1- install openssh
2- check if installed
	dpkg --get-selections | grep ssh
3- generate certificates
	openssl req -newkey rsa:2048 -new -x509 -days 365 -nodes -out mongodb-cert.crt -keyout mongodb-cert.key
	//for common name write 127.0.0.1
4- you can check if the files is generated (crt and key)
	ls -alias mongo*
5- combine them
	cat mongodb-cert.key mongodb-cert.crt > mongodb.pem
6- copy to your folder
	cp mongodb.pem /etc/ssl
	ls -alis /etc/ssl/
7-change config
	vi /etc/mongod.conf
		net: ...
			ssl: 
			    mode: requireSSL
			    PEMKeyFile: /etc/ssl/mongodb.pem
	:wq
8-restart mongo
	systemctl restart mongod

9-connect to db by this command
//for linux change tls to ssl
mongo -u "username" --ssl --sslCAFile /etc/ssl/mongodb.pem --sslPEMKeyFile /etc/ssl/mongodb.pem --host "127.0.0.1" 
//for windows
mongo -u "username" --tls --tlsCAFile C:/temp/mongodb.pem --tlsCertificateKeyFile C:/temp/mongodb.pem --host "127.0.0.1" 

Create Truststore for spring boot
keytool -import -alias "MongoDB-cert" -file "mongodb-cert.crt" -keystore truststore.jks -noprompt -storepass "password"

To connect to Mongodb through SSL encryption, using truststore, we need to configure mongodb connection




