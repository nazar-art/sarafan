Launch UI:

    yarn start
    
Launch backend by running main() for Application class or     

    build spring-boot:run

---

After launching the app navigate to:

    http://localhost:9000/login

Login with OAuth auth.

You could find details about your cloud applications:

    https://console.cloud.google.com/apis/credentials?project=srafan-demo&supportedpurview=project
    
Also, don't forget set `client-secret` to environment-variables for IntelliJ configuration settings.

---

calls from browser console:

GET all

    fetch('/message/').then(response => response.json().then(console.log))

GET one

    fetch('/message/2').then(response => response.json().then(console.log))

POST add new one

    fetch(
      '/message', 
      { 
        method: 'POST', 
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: 'Fourth message (4)', id: 10 })
      }
    ).then(result => result.json().then(console.log))

PUT save existing

    fetch(
      '/message/4', 
      { 
        method: 'PUT', 
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify({ text: 'Fourth message', id: 10 })
      }
    ).then(result => result.json().then(console.log));

// DELETE existing

    fetch('/message/4', { method: 'DELETE' }).then(result => console.log(result))