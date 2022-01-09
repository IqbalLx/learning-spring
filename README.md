# Learning Spring

Here I try to implement Clean Code using Java + Spring Framework. All the way to dockerize it using multi-stage build.

fire up postgresql in port 5432 and start in your local:
```
docker build -t learn/spring .
docker run -it --rm -p 8080:8080 learn/spring
```