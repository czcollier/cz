#!/bin/bash 
java -javaagent:/home/ccollier/devel/tools/jrebel5/jrebel.jar -cp "/usr/share/java/scala-library.jar:target/efind.jar:/usr/share/java/nailgun-0.9.0.jar:/usr/share/java/scalap.jar:/usr/share/java/scala-jline.jar:test.jar:/usr/share/java/scala-compiler.jar:test.jar:/usr/share/java/scala-reflect.jar" com.martiansoftware.nailgun.NGServer
