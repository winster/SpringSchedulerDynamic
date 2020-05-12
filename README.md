# Embeded Spring Cloud Config Server

I know that **embeded config server** is an oxymoron. But this might help someone on bootstrapping an environment where
 modification of configuration properties during runtime is enabled

#### Points to note
1. @EnableConfigServer is avoided, as I built an embed config server and not a central config server
2. No profile is used. In case you want to use a profile, put following in bootstrap.properties
`spring.application.profiles=<your-active-profile(s)>`
3. Point to a git repo. For convenience, you can create a local folder and 'git' it as follows
```
mkdir testgit
cd testgit 
git init
git add scheduledtask.properties
git commit -m "added properties"
```
4. Add following property into newly created file (scheduledtask.properties in testgit folder)
`scheduled.fixedratedelay=2500`
5. Run application and curl
`curl http://localhost:8080/test` will give `test:2500`
6. Now change scheduledtask.properties as follows
`scheduled.fixedratedelay=3000`
7. Now curl
`curl -X POST http://localhost:8080/actuator/refresh` will give `["scheduled.fixedratedelay"]`
8. Curl API endpoint again
`curl http://localhost:8080/test` will give `test:3000`