Notes
===
This is bascially the database connection. All the read and writes will happen here or in the Singleton classes that gets declared later on.


Transactional
===
This is basically Spring's way of making a function or a service follow the `ACID` principal. Basically it uses the database connection that you have
to `commit` and `rollback` an action that you are doing in the scenario that an error occurs.

It is set up for you automatically if you are using `JdbcTemplate` but otherwise you need to set it up yourself
or configure it. It is related to `spring-data-jba`, I believe is what it's called.