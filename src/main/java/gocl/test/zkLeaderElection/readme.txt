Additional comments:

* We regularly do not use CamelCase in package names (referred to zkLeaderElection)

* Our regular package prefix is "mx.klar."; Taking in account that it is a PoC, the base package name could be:
mx.klar.poc.the.project.name

* It seems that thanks to gocl.test.leader.role=ScheduleJob1 it is possible to get a leader assigned for
each @Scheduled process separately. Is that correct? It would be amazing!

* Is it has sense to set the Zookeeper path (gocl.test.leader.path) to the application name when it is defined
- same as Kafka consumer group?
 -  gocl.test.leader.path=${spring.application.name}
