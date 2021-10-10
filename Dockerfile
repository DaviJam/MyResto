FROM tomcat:10.0.0-jdk11-openjdk
LABEL maintainer="David JAMES"
RUN apt update -y
RUN apt-get install maven -y
RUN apt-get install git -y
RUN mkdir /data
RUN git clone https://github.com/DaviJam/MyResto.git /data/MyResto
WORKDIR /data/MyResto
RUN mvn install -DskipTests=true
WORKDIR /data/MyResto/MyResto-presentation/target
ADD myresto.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
