# codefellowship

## Feature Task
#### Lab16
- Build an app that allows users to log into CodeFellowship.

The site should have a login page.
The login page should have a link to a signup page.
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
All of these fields must be set at signup. They will not be editable at any other time.
The site should allow users to create an ApplicationUser on the “sign up” page.
Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.

#### Lab17

- Allow users to log in to CodeFellowship, view user profiles, and create posts.

Upon logging in, users should be taken to a /myprofile route that displays their information.
This should include a default profile picture, which is the same for every user, and their basic information from ApplicationUser.

## HOW TO RUN
- clone done repo
- run ./gradlew run --
