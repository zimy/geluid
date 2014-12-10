[![Stories in Ready](https://badge.waffle.io/zimy/geluid.png?label=ready&title=Ready)](https://waffle.io/zimy/geluid)
#Geluid [![Build Status](https://travis-ci.org/zimy/geluid.svg?branch=master)](https://travis-ci.org/zimy/geluid)
Geluid - music player server with a web-based user interface

To build Geluid you have to install and configure JDK. Now supported versions are:
- openjdk7;
- oraclejdk7;
- oraclejdk8.

You can check installed java version running
```shell
java -version
```

##Building
You should check that JAVA_HOME environmental variable is set and points to the correct location. For instance, we unpack Oracle JDK 8 to /opt/jdk1.8.0_25/
That directory will contain subdirectories like bin, jre, include, and others. You should check that your JAVA_HOME is set to /opt/jdk1.8.0_25/ in that case. After that check you can run following to assemble runnable jar:
```shell
./gradlew assemble
```
and then run assembled jar which can be found in  build/libs/.
```shell
java -jar build/libs/Geluid-<version numbers here>.jar
```

or directly launch application with
```shell
./gradlew bootRun
```

##Configuration
Geluid now can be configured three ways:
- with command line arguments;
- with properties files;
- with YAML files.

Possible configuration locations are ./[config/]application.[yml, yaml, properties] and can be changed in future.

###Command line
Here is example of command line arguments configuration, where all data is collapsed in one line. Make a note that for arrays (music.location) we have to specify position [0], and array position characters are not always allowed in command shells, so if call to Geluid is dropped with command shell errors you should prefix every such character with \ or something else.
```shell
--music.location[0]=/your/music/directory --music.location[1]=/your/second/music/directory
```

###Using YAML
Here is an example of ./application.yaml:
```yaml
music:
  location:
    - .
    - /my/first/music
    - /my/second/music
    - /my/mounted/ftp/directory/with/music
    - /my/sshfs/mounted/directory
    - /tmp
```
You can add as many music locations as needed.

###Database connection
The second changeable thing now is the database location, and it can be set via command line argument, or in application.yaml. Here will be only yaml example because it is more convenient.
```yaml
spring:
  datasource:
    url: jdbc:hsqldb:file:./.hsqldb/wHole
```
Given example creates folder .hsqldb in current working directory, where it stores database "wHole". Geluid also can be configured to use in-memory database, in that case you should use such configuration example:
```yaml
spring:
  datasource:
      url: jdbc:hsqldb:mem:wHole
```
Also you can connect to any relational database which has JDBC driver, but now only HSQLDB driver is provided by default, for all other drivers you should add external dependency in build.gradle and configure datasource driver:
```yaml
spring:
  datasource:
      driverClassName: org.hsqldb.jdbcDriver
```