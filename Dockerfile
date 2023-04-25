FROM openjdk:17

EXPOSE 9002

ADD /target/Allergy-service.jar Allergy-service.jar

ENTRYPOINT [ "java","-jar","/Allergy-service.jar"]