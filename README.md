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