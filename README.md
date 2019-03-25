# Notification Service Application

This is a notification service that performs two activities
1. Send a welcome email template to the imaginary mail server when new user is created. Whenever a new user is created, a POST call is made to this service which fetches the template from the template-service and prints the user-enriched template onto the screen. 

Please note that printing on the screen is analogous to sending it to a mail server. 


2. Send the newsletters to the subscribed users by loading the templates from the template-service and fetching users from the user-service. Templates are enriched with user data and are printed on the screen in batches of 10

## How to run the application

To run this application, start the sbt shell and type
##### run 9002

This will start the notification service at port 9002.

## Tech stack used
Scala, Play framework, H2 database(in memory), Slick, Scalafmt for code hygiene

H2 has been specifically chosen to avoid any external DB installations.

#### Endpoints

There are two endpoints

/welcome-user :- For sending a welcome user template to the newly created user. This endpoint is called from the user-service as soon as the user is created.

/newsletters :- For sending newsletters to all the subscribed users. Fetches all the users from the user-service via HTTP call, then makes another call to the template-service to fetch the newsletter template, enriches the template with all the user data and prints it to a console.


#### Tech Debt

1) Unit test cases missing for all files

2) /newsletter to include provision for template key and template id

3) Messaging queue to be used from where notification services will be triggered instead of calling them directly from user-service.

4) Messaging queue to be used instead of printing the newsletter in batches onto the screen.

5) Making a parallel call to user service and template service and then combining the data.

6) Making the call to fetch all users using stream api of the WSClient library. This will ensure that the huge list of users does not consume all resources of the http thread pool.