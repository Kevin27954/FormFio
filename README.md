Notes
===

This will be the place where I write down what I learn and hated or whatever about SpringBoot so that I can put it on my resume after wards and get a JOB UWU HELP ME PLEASE!

So when you start a SpringBoot project as a web project, you need to have (all same versions of courses or the latest):
- spring-starter-web
- spring-web

So the **CORE** of spring boot is dependency injection. It is built on top of this pattern and serves as a framework for web development.

Spring-Web 
===
This one gives you the annotations to help build the web.

Spring-Starter-Web
===
This one gives you the functions that the annotations will use to start the web, I'm guessing.

Beans and Component
===
These are components of the **IOC**, which is just a principle. Spring implements this principle with **Dependency Injection**.
(This is similar to RAFT algorithm and the implementation of it). It is done using `@Bean` and `@Component`.

**Beans** are basically components, expect springboot doesn't scan the class/creation for you. So basically
you will need to instantiate everything yourself and how it's declare and pass it on to other objects yourself. 
In the main method, you would then need to pass the **Configuration** class into  the spring context so it can 
see it and inject it to whichever class needs it.
> Think of it as a **DIY** thing. Make your own `Component` annotation.

**Components** are things that tell SpringFramework to do everything for you to this class. If another class needs it, just putting it
in the constructor or somewhere will be enough to pass it on. It will be registered during the build/class scanning time.

[This stackoverflow is helpful in explaining this](https://stackoverflow.com/questions/10604298/spring-component-versus-bean)
[An Example of using Bean](https://www.geeksforgeeks.org/springboot/spring-bean-annotation-with-example/)

Converter
===
Just a way for you to convert one data into another.
Need to look more into it.

Redis
===
So Redis, we use Jedis. It is similar to all the other databases, 
- Make sure you start the Redis instance
- Figure out how to get a connection
- Figure out if you need a pool or a single connection
- Use the connection to then call the ability that the database has (select, get, mutli, etc whatever)

Now on to the usage of redis:
The `key` part is making sure the key that we use is good and unique and easily recreateable. You create
the key yourself and that is the way to get information on the key.
In this project we are following the **window limiter**, where we allow the key to live for a window (read: duration)
We also want to make sure that it is **transactional** so any future potential where the key is read before
update occurs doesn't happen. This is done by using: `multi` and `exec`.
`mutli` is basically doing the `setAutoCommit(false)` in **SQL** and telling that it is transaction from now on.
`commit` is basically doing the `setAutoCommit(true)` && `commit()` in **SQL**.

Docker
===
So for docker, building on top of what I already knew, something that took me quite a while to figure out was
the problem of ip addresses. In docker, apparently, the default is ipv4 and you have to manually chnage it to
ipv6 (if the url is ipv6). The only reason I was able to tell was due to the cmd `nc -vz <url> <port>` and thanks
to AI suggesting that. But in the end, I basically had to arrive at the solution myself. 
In the future, whenever I see something about connection errors, I should assume immediately it's one of the followings:
- ipv4 vs ipv6 error
- typo
- wrong url being used
- application is **ONLY** using ipv4

The last point above is due to the fact that I was suggested to add the flag `-Djava.net.preferIPv4Stack=true` during
my malding session of trying to solve this. In these scenarios, I should try to make it **accept ipv6** or find the
ipv4 version of it.
> Honestly I feel like making it accept ipv6 is smarter since that is the new thing that we are trying to move to. 

In the end, I just simply move the application into a custom network that allows ipv6 connections.
`
networks:
formfio-network:
enable_ipv6: true
`

> WOW so good AI, but It definitely did help in reaching to the soltion.
