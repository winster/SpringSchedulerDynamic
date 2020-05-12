# RefreshScoped Scheduled Bean

Have a look at https://github.com/winster/embedconfigserver to see an example for Refresh feature with Spring Boot & Actuator

#### Points to note
I started with ScheduledRegistrar and it seems to work
But I used the approach to cancel existing scheduledtask and recreate on refresh event
It could pose a threat of interrupting the active threads

### How to Test

1. scheduledtask.properties has a property `scheduled.fixedratedelay=5000`
2. Run application and watch logs in MyJob
3. Now change scheduledtask.properties as follows
`scheduled.fixedratedelay=1000`
4. Now curl
`curl -X POST http://localhost:8080/actuator/refresh` will give `["scheduled.fixedratedelay"]`
5. Watch logs again
6. You can observer something similar as follows
```
2020-05-12 12:47:40.966  INFO 15480 --- [           main] s.a.ScheduledAnnotationBeanPostProcessor : No TaskScheduler/ScheduledExecutorService bean found for scheduled processing
2020-05-12 12:47:40.990  INFO 15480 --- [         task-1] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-1 2020-05-12T12:47:40.989
2020-05-12 12:47:41.006  INFO 15480 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-05-12 12:47:42.533  INFO 15480 --- [           main] c.w.s.s.ScheduledtaskApplication         : Started ScheduledtaskApplication in 11.702 seconds (JVM running for 13.926)
2020-05-12 12:47:43.182  INFO 15480 --- [)-172.19.155.94] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2020-05-12 12:47:43.182  INFO 15480 --- [)-172.19.155.94] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2020-05-12 12:47:43.194  INFO 15480 --- [)-172.19.155.94] o.s.web.servlet.DispatcherServlet        : Completed initialization in 12 ms
2020-05-12 12:47:45.972  INFO 15480 --- [         task-3] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-3 2020-05-12T12:47:45.972
2020-05-12 12:47:50.970  INFO 15480 --- [         task-5] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-5 2020-05-12T12:47:50.970
2020-05-12 12:47:51.000  INFO 15480 --- [         task-1] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-1 2020-05-12T12:47:50.991
2020-05-12 12:47:55.971  INFO 15480 --- [         task-7] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-7 2020-05-12T12:47:55.971
2020-05-12 12:47:55.973  INFO 15480 --- [         task-3] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-3 2020-05-12T12:47:55.973
2020-05-12 12:48:00.971  INFO 15480 --- [         task-5] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-5 2020-05-12T12:48:00.971
2020-05-12 12:48:00.972  INFO 15480 --- [         task-9] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-9 2020-05-12T12:48:00.972
2020-05-12 12:48:05.971  INFO 15480 --- [        task-11] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-11 2020-05-12T12:48:05.970
2020-05-12 12:48:05.973  INFO 15480 --- [         task-7] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-7 2020-05-12T12:48:05.973
2020-05-12 12:48:10.971  INFO 15480 --- [        task-13] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-13 2020-05-12T12:48:10.971
2020-05-12 12:48:10.973  INFO 15480 --- [         task-9] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-9 2020-05-12T12:48:10.973
2020-05-12 12:48:15.970  INFO 15480 --- [        task-15] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-15 2020-05-12T12:48:15.970
2020-05-12 12:48:15.971  INFO 15480 --- [        task-11] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-11 2020-05-12T12:48:15.971
2020-05-12 12:48:20.971  INFO 15480 --- [        task-13] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-13 2020-05-12T12:48:20.971
2020-05-12 12:48:20.971  INFO 15480 --- [        task-17] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-17 2020-05-12T12:48:20.971
2020-05-12 12:48:25.970  INFO 15480 --- [        task-19] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-19 2020-05-12T12:48:25.970
2020-05-12 12:48:25.974  INFO 15480 --- [        task-15] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-15 2020-05-12T12:48:25.974
2020-05-12 12:48:30.243  WARN 15480 --- [nio-8080-exec-1] .c.s.e.MultipleJGitEnvironmentRepository : Could not merge remote for master remote: null
2020-05-12 12:48:30.970  INFO 15480 --- [         task-1] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-1 2020-05-12T12:48:30.970
2020-05-12 12:48:30.972  INFO 15480 --- [        task-17] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-17 2020-05-12T12:48:30.972
2020-05-12 12:48:31.029  INFO 15480 --- [nio-8080-exec-1] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: file:/C:/Users/wjose/workspace/testgit/scheduledtask.properties
2020-05-12 12:48:31.029  INFO 15480 --- [nio-8080-exec-1] b.c.PropertySourceBootstrapConfiguration : Located property source: [BootstrapPropertySource {name='bootstrapProperties-file:///C:/Users/wjose/workspace/testgit/scheduledtask.properties'}]
2020-05-12 12:48:31.039  INFO 15480 --- [nio-8080-exec-1] o.s.boot.SpringApplication               : No active profile set, falling back to default profiles: default
2020-05-12 12:48:31.044  INFO 15480 --- [nio-8080-exec-1] o.s.boot.SpringApplication               : Started application in 2.578 seconds (JVM running for 62.437)
2020-05-12 12:48:31.146  INFO 15480 --- [         task-5] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-5 2020-05-12T12:48:31.146
2020-05-12 12:48:32.147  INFO 15480 --- [         task-2] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-2 2020-05-12T12:48:32.147
2020-05-12 12:48:33.146  INFO 15480 --- [         task-4] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-4 2020-05-12T12:48:33.146
2020-05-12 12:48:34.146  INFO 15480 --- [         task-7] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-7 2020-05-12T12:48:34.146
2020-05-12 12:48:35.147  INFO 15480 --- [         task-6] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-6 2020-05-12T12:48:35.147
2020-05-12 12:48:35.970  INFO 15480 --- [         task-9] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-9 2020-05-12T12:48:35.970
2020-05-12 12:48:35.974  INFO 15480 --- [        task-19] c.w.s.scheduledtask.scheduler.MyJob      : received a response something task-19 2020-05-12T12:48:35.974
2020-05-12 12:48:36.146  INFO 15480 --- [         task-8] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-8 2020-05-12T12:48:36.146
2020-05-12 12:48:37.147  INFO 15480 --- [        task-13] c.w.s.scheduledtask.scheduler.MyJob      : insider processAnother task-13 2020-05-12T12:48:37.147
```

Interesting thing to note is `received a response something task-19 2020-05-12T12:48:35.974` which was scheduled before
 refresh event and continue to process and gracefully complete after refresh event.
