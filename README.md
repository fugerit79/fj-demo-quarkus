# fj-demo-quarkus

Freshly created application, command used : 

```
mvn io.quarkus.platform:quarkus-maven-plugin:2.15.3.Final:create \
	-DprojectGroupId=org.fugerit.java \
	-DprojectArtifactId=fj-demo-quarkus \
	-Dextensions='resteasy-reactive'
```

## Added agroal  :

```
./mvnw quarkus:add-extension -Dextensions="agroal"
```


## Added h2 : 

```
 ./mvnw quarkus:add-extension -Dextensions="jdbc-h2"
```


## Added jackson : 

```
./mvnw quarkus:add-extension -Dextensions="quarkus-resteasy-reactive-jackson"
```


 