# Sarafan - modern chat app based on Spring Boot

## Installation steps

You need Postgres DB installed first. 

Create `sarafan_db`. 

Update credentials for DB at `application.yml`

---

### Launch UI:

    yarn install
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

Tested with:
    
    yarn: 1.22.22
    node: v18.20.8
    gradle: 8.14 (min version 8.10)
    java: 21
    
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
    
    