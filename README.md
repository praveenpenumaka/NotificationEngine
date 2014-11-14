NotificationEngine
==================

Generic Notification Engine for marketing/campaign purposes.

Java library for sending notifications to any channels including email, SMS, push etc.

### JSON request
```
[
	{
		"channel" : "email",
		"receivers": [{
			"email":"abc@abc.com",
			"fullname":"Abc For"
		},
		{ 
			"email":"jon@doe.com",
			"fullname":"jon doe"}
		],
		"templateId": "",
		"templateData":{}
		"message": "Hello, %once%fullname%once%",
		"subject":"",
		"smtp":"smtp.gmail.com",
		"from":"admin@notificationengine.com"
	},
	{
		"channel" : "sms",
		"receivers": [{"phone":"9944993322","name":"jon"},{"phone":"4455334322","name":"doe"}],
		"templateId": "",
		"templateData": {},
		"message": "Hello, %once%name%once%"
	}
]
```
### Usage

```
NotificationRequest notificationRequest = new NotificationRequest();
notificationRequest.initializeFromJson(json);
Notification notification = new Notification(notificationRequest);
notification.sendMessage();
```

