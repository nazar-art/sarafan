# Sarafan - modern chat app based on Spring Boot

## Deployed to Heroku:

    https://sarafan-service.herokuapp.com/auth

## Installation steps

You need Postgres installed first. 

Create `sarafan_db` & `sarafan_test_db`. 

Update credentials for DB at `application.yml`

---

### Launch UI:

    yarn start
    
### Launch backend by running `main()` for Application class or     

    build spring-boot:run

---

### Open the app at:

    http://localhost:9000/login

Login with OAuth auth.

You could find details about your cloud applications:

    https://console.cloud.google.com/apis/credentials?project=srafan-demo&supportedpurview=project
    
Also, don't forget set environment-variables for IntelliJ configuration settings:

    client-secret - ...
    spring.profiles.active - dev
    
---

Build it locally with front end part at jar sources:

    ./gradlew build
    
Launch it next:    
    
    java -jar -Dclient-secret=... build/libs/sarafan-0.0.1-SNAPSHOT.jar 

---

#### Calls which are possible from browser's console:

###### GET all

    fetch('/message/').then(response => response.json().then(console.log))

###### GET one

    fetch('/message/2').then(response => response.json().then(console.log))

###### POST add new one

    fetch(
      '/message', 
      { 
        method: 'POST', 
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: 'Fourth message (4)', id: 10 })
      }
    ).then(result => result.json().then(console.log))

###### PUT save existing

    fetch(
      '/message/4', 
      { 
        method: 'PUT', 
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify({ text: 'Fourth message', id: 10 })
      }
    ).then(result => result.json().then(console.log));

###### DELETE existing

    fetch('/message/4', { method: 'DELETE' }).then(result => console.log(result))
    
---

#### Useful references    

* [Sentry](https://sentry.io/)
    
    