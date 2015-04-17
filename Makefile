include ../java.mk

#.SUFFIXES: .java .class
     #SRCS = *.java
     SRCS = IvyApplicationAdapter.java IvyApplicationListener.java IvyBindListener.java IvyClient.java IvyException.java Ivy.java IvyMessageListener.java IvyWatcher.java SelfIvyClient.java WaiterClient.java Waiter.java PingCallback.java Puppet.java ProxyClient.java Ghost.java
     TOOLS= IvyDaemon.java Probe.java After.java ProxyMaster.java 

     OBJS = $(SRCS:.java=.class)
     DOCS = ../doc/html/api


#ivy-java:
#	$(JAVAC) $(JAVACOPTS) $(CLASSPATH) $(SRCS)

all:
	$(JAVAC) -d . $(JAVACOPTS) $(CLASSPATH) $(SRCS)
	$(JAVAC) -d . $(JAVACOPTS) $(CLASSPATH) $(TOOLS)
	

clean:
	/bin/rm -f -- $(OBJS) *~ *.bak $(JAR) fr/dgac/ivy/*.class fr/dgac/ivy/tools/*.class

docs:
	rm -fR $(DOCS)/*html
	rm -fR $(DOCS)/*css
	rm -fR $(DOCS)/fr/dgac/ivy/*html
	javadoc $(CLASSPATH) -d $(DOCS) $(SRCS)
	#javadoc -d $(DOCS) $(SRCS)
	rm -f $(DOCS)/package-list
