# isi3-tp-MVC

Philippe Charrière :octocat: [@k33g](https://github.com/k33g)

:warning: Si vous avez une question, créez une issue dans votre projet et notifiez moi avec [@k33g](https://github.com/k33g)

> Le rapport est à saisir dans le ficher [RAPPORT.md](RAPPORT.md) en utilisant le [formalisme MarkDown](https://guides.github.com/features/mastering-markdown/)



## Exercice 1: MVC

- Utiliser le projet `kebab-mvc` (référencer le jar `vert-x.jar` présent dans `../libs`)
- Regarder les codes d'exemple
- Se les faire expliquer (par moi)

![form](mvc.jpg)

### Partie 1

- Faire un formulaire pour ajouter des ingrédients dans un Kebab

![form](01.png)

> :warning: faire ça **façon MVC**, soyez imaginatifs

![form](02.png)

**Exemple de contrôleur**:
```java
package kitchen.controllers;

import io.vertx.ext.web.RoutingContext;

public class HelloController {
    RoutingContext context = null;

    public HelloController(RoutingContext context) {
        this.context = context;
    }

    public void ok() {
        context.put("message", "hello world");
        context.next();
    }
}
```

### Partie 2

- Ajouter la suppression

## Exercice 2: MVVM

resource: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel

- Utiliser le projet `kebab-spa` (référencer le jar `vert-x.jar` présent dans `../libs`)
- Regarder les codes d'exemple
- Se les faire expliquer (par moi)

![form](mvvm.png)

### A faire

- Là même chose que dans l'exercice 1, mais en mode  "Single Page Application"
  - vous n'utilisez plus les templates côté serveur
  - vous utilisez une application JavaScript et du HTML (Vue.js)

## Exercice 3: Authentification

Ajoutez l'authentification avec la technique des JSON Web Tokens (dans l'exercice 2)

```
Client application                                            API
    --------                                              -----------
        |                                                      |
        |                   GET /api/employees                 |
        |----------------------------------------------------->|
        |                     403 Forbidden                    |
        |<-----------------------------------------------------|
        |                                                      |
        |                                                      |
        |                 POST /api/authenticate               |
        |     { login: "john.doe", password: "password" }      |
        |----------------------------------------------------->|
        |                      200 Success                     |
        |             { token: "my.personal.token" }           |
        |<-----------------------------------------------------|
        |                                                      |
        |                                                      |
        |                 GET /api/employees                   |
        | Header { "Authorization: Token "my.personal.token" } |
        |----------------------------------------------------->|
        |                      200 Success                     |
        |<-----------------------------------------------------|
        |                                                      |
```


## Ressources:

- https://vertx.io/
- https://vertx.io/docs/guide-for-java-devs/
- https://vertx.io/docs/vertx-auth-jwt/java/




