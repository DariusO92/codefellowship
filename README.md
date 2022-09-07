# codefellowship

## Feature Task

- Build an app that allows users to log into CodeFellowship.

The site should have a login page.
The login page should have a link to a signup page.
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
All of these fields must be set at signup. They will not be editable at any other time.
The site should allow users to create an ApplicationUser on the “sign up” page.
Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.


## HOW TO RUN
- clone done repo
- run ./gradlew run --
