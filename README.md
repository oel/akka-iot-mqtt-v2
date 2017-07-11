## IoT & Akka Actor Systems v.2

---

This is a Scala-based application using Akka Actor systems and MQTT Pub-Sub messaging on a scalable fault-tolerant distributed platform to simulate individual IoT (Internet of Things) devices, each maintaining its own internal state and setting.  It's an expanded version (hence v.2) of another [Akka-IoT application](https://github.com/oel/akka-iot-mqtt).

##### To run the application on a single JVM, simply Git-clone the repo to a local disk, open up a command line terminal, run the following command and observe the console output:

> Start a Redis server accessible to the master cluster to serve as the persistence journal:
>> $ nohup redis-server /path/to/conf &

> Launch the master cluster with 2 seed nodes, IoT actor system and Worker actor system:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main [numOfDevices]"

The optional numOfDevices parameter, if not provided, defaults to 20.

##### To run the application on separate JVMs, please proceed as follows:

Git-clone the repo to a local disk, open up separate command line terminals and launch the different components on separate terminals:

> Start the Redis server which serves as the persistence journal accessible to the master cluster:
>> $ redis-server [/path/to/conf]

> Launch the master cluster seed node with persistence journal:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main 2551"
>
> Launch additional master cluster seed node:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main 2552"
>
> Launch the IoT node:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main 3001 [numOfDevices]"
>
> Launch a Worker node:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main 0"
>
> Launch additional Worker node:
>> $ {project-root}/bin/sbt "runMain akkaiot.Main 0"

To scale up work processing service for the IoT devices, start up additional worker (and/or master) nodes.

To test fault tolerance in the master cluster, stop a master node and observe how the cluster fails over to the next oldest master node.

---
