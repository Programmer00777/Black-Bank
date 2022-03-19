# Black Bank: Stage 6

>Now, the application has the next functionality:

• Registration (with validation).

• Authorization & authentication (each user has a specific role and an appropriate set of permissions).

• User can send virtual money to another account.

• Implemented user search - now, you can search for some user, and, if it exists, receive that user's page.

• News on the main page (is in development at the moment)

• NEW: an email sender

### List of things that will be developed at the next stages:

• Admin page with an ability to edit roles of common users.

• Owner page with an ability to assign and deny admins.

• Password recovery.

• Usage of Monobank Open API for displaying the actual exchange rates.

• Sign up / in via OAuth2 (Google)

• Displaying appropriate alerts and messages in case of wrong input, non-existing user via searching etc. (at the moment, this info is just printed in the console)

### Additional info:

> Technologies that will be injected:

• Elasticsearch (will think of it, how it can be used in this app)

• Usage of some JavaScript framework (probably, it will be Vue.js)

• JUnit

• Docker


## Starting up

> Download the project, go to the root directory via Terminal (or your CLI) and type the next command:

```bash
./mvnw spring-boot:run
```

## Breakpoints

***• localhost:8081/auth/signup** - registration*

***• localhost:8081/login** - authentication/authorization*

***• localhost:8081/logout** - logout*

***• localhost:8081/profile/** - main page*

***• localhost:8081/profile/admin** - admin page (only for admins and owners)*

***• localhost:8081/profile/owner** - owner page (only for owners)*

***• localhost:8081/profile/user/{id}** - user page (that displays data of a user with specified id (if it exists))*

***• localhost:8081/operations** - page for searching for users*

***• localhost:8081/operations/transferMoney** - page for transferring money*

***• localhost:8081/email/** - page for sending email (in test mode, isn't ready for users' use)*

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
MIT License

Copyright (c) 2022 Black Bank

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
