FROM tomcat:8
COPY boot-test-web/target/boot-test-web.jar /usr/local/
WORKDIR /usr/local/
CMD eval exec java ${JAVA_OPTS} -jar /usr/local/boot-test-web.jar ${PORT0}