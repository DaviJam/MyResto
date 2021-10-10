FROM tomcat:7.0.108-jdk8-openjdk
LABEL maintainer="David JAMES"
RUN apt update -y
RUN apt-get install maven -y
RUN apt-get install git -y
RUN apt-get install net-tools -y
RUN mkdir /data
RUN git clone https://github.com/DaviJam/MyResto.git /data/MyResto
WORKDIR /data/MyResto
RUN mvn clean install
WORKDIR /data/MyResto/MyResto-presentation/target
RUN mv myresto.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
