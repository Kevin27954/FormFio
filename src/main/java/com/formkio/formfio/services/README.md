Notes
===
The business logic side of things. Not where the data is send or stored, but where the logic/parsing happens.

E.g. when you receive form data, you gotta parse it into a correct representation of it in Java and then store it.
This is where you would write the code to do that.

This serves as the connection point between the controller and the database. You can perform small logic checks
and changes in the service before making changes to the database with the new data.

In the scenario that you need to perform `Transactions` in the `Service`, you can inject the `DBDriver` into the `Service` class.
You should not create a database operation in it though, you still need to call the `Repo` classes functions to perform the 
database actions. However, you can "wrap" the db actions with a `beginTransaction` and `commit` function from the `DBDRiver`.
This way it will be like a transaction.