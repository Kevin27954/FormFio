Notes
---
The controller is basically the routing and APIs of the application.
Everything thing here is a singleton with that design in mind.

I should never put the database as a dependency injection in here since usually the service would perform
it's portion of the checks or validation, etc before inserting it into the database.
So basically, only services should deal with the database part, whereas controller only need to call the 
services that they are using to then store the data or information.