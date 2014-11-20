geluid [![Build Status](https://travis-ci.org/zimy/geluid.svg?branch=master)](https://travis-ci.org/zimy/geluid)
======

geluid - music player server with a web-based user interface

Now I'am testing Spring Security configuration with Hibernate

For building you have to install and configure JDK (now I build with Oracle JDK 8 on Vista and can't support anything else, but it *may* be built on different systems) and run Gradle (if you have one installed you know what to do), or run provided wrapper


```shell
gradlew clean assemble
```
and then run .jar in <Geluid>\build\libs

or directly launch application
```shell
gradlew clean bootRun
```
You can specify music directory two ways:

The first is to use command line arguments:

```shell
--music.location\[0\]=/your/music/directory --music.location\[1\]=/your/second/music/directory
```

You can specify as many as you wish.

The second is using YAML:

application.yaml:
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
Like in command line variant, you can add as many as you wish them
