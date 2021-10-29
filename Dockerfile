FROM openjdk:8-jdk-alpine

RUN mkdir /opt/tomcat
RUN mkdir /opt/apache
WORKDIR /tmp
RUN wget https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.47/bin/apache-tomcat-7.0.47.tar.gz
RUN tar xvzf apache-tomcat-7.0.47.tar.gz --directory /opt/tomcat
RUN wget https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.6.3/apache-maven-3.6.3-bin.tar.gz
RUN tar xvzf apache-maven-3.6.3-bin.tar.gz --directory /opt/apache/
RUN ln -s /opt/apache/apache-maven-3.6.3/bin/mvn /usr/bin
ENV MAVEN_HOME=/opt/apache/apache-maven-3.6.3
RUN export PATH=$PATH:$MAVEN_HOME/bin

ENV CATALINA_HOME=/opt/tomcat/apache-tomcat-7.0.47

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk

WORKDIR /opt/tomcat/apache-tomcat-7.0.47

ENV TOMCAT_NATIVE_LIBDIR=/opt/tomcat/native-jni-lib
RUN export PATH=$PATH:$TOMCAT_NATIVE_LIBDIR

#COPY --from=build /app/MyResto-presentation/target/myresto.war /opt/tomcat/webapps/myresto.war
COPY /MyResto-presentation/target/myresto/WEB-INF/lib/mysql-connector-java-8.0.22.jar /opt/tomcat/apache-tomcat-7.0.47/lib/mysql-connector-java-8.0.22.jar
COPY tomcat-users.xml /opt/tomcat/apache-tomcat-7.0.47/conf/tomcat-users.xml

COPY context.xml /opt/tomcat/apache-tomcat-7.0.47/conf/context.xml

COPY context.xml /opt/tomcat/apache-tomcat-7.0.47/webapps/manager/META-INF/context.xml

RUN apk update
RUN apk add coreutils
RUN apk add git
RUN apk add net-tools

RUN mkdir /tmp/MyResto
RUN git clone https://github.com/DaviJam/MyResto.git /tmp/MyResto
WORKDIR /tmp/MyResto
RUN mvn install
RUN mv /tmp/MyResto/MyResto-presentation/target/myresto.war /opt/tomcat/apache-tomcat-7.0.47/webapps/

EXPOSE 8080
#WORKDIR /tmp/MyResto/MyResto-presentation
#CMD ["mvn", "tomcat7:run"]
CMD ["/opt/tomcat/apache-tomcat-7.0.47/bin/catalina.sh", "run"]
