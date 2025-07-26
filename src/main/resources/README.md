Notes
===

So in Spring, you dont' have .env files. Environment Variables are quite literally, just environment variables.
You add the variables either in the CLI when running or in the system environment. Both of which can be refered to
within the `applications.properties` file by doing:
`<name of property>=${ENV NAME}`

Following that, to access that `ENV` value, you would use the annotation: `@Value`. An example of that would be:
> `@Value("${spring.jwk.uri}")`
