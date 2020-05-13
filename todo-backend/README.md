# Full Stack TODO App

Yet another TODO app with naive functionalities such as list, add, delete.

To run the whole containers;
```
docker-compose up
```

## Tech stack
* Frontend; [Node.js](https://nodejs.org/), [npm](https://www.npmjs.com/), [React](https://reactjs.org/), [Bootstrap](https://getbootstrap.com/), [Reactstrap](https://reactstrap.github.io/)
* Backend; [Spring Boot](https://spring.io/projects/spring-boot), [Spring Web REST](https://spring.io/guides/gs/rest-service/), [Spring Data JPA](https://spring.io/projects/spring-data-jpa), [Maven](http://maven.apache.org/)
* Database; [PostgreSQL](https://hub.docker.com/_/postgres)
* Containerization; [Docker](https://docker.com/), [Docker-Compose](https://docs.docker.com/compose/)

## Backend Deployment
[Jib plugin](https://github.com/GoogleContainerTools/jib) offered by Google is used to create an image of backend api.
Add Jib maven plugin to the `pom.xml` like this [setup](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#setup).

Since Jib automatically pushes the created image to docker hub, user credentials are required to login into the docker registry.
Simply insert your credentials surrounded with server tags into the `settings.xml` file as [shown here](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#using-maven-settings).
To find out where the file is located, any maven command with the option -X can be run; i.e. `mvn -X install`.
Then search for `settings.xml` keyword in debug logs and update the global or local file with server tag as [shown here](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#using-docker-hub-registry).

Finally, run the command below.
```
mvn compile jib:build
```

## Frontend Deployment
To create an image of the frontend and push it to the docker hub, run the commands below in the directory of the `todo-frontend` folder.

```
docker build -t [IMAGE_NAME] .
docker login
docker push [IMAGE_NAME]
docker logout
```

## Future Work
- [ ] Unit tests with h2 db.
- [ ] Add user login/logout services with [Spring Security](https://spring.io/projects/spring-security) and [JWT](https://jwt.io/).
- [ ] Filtering by categories.
- [ ] Implement update and pagination functionalities.
