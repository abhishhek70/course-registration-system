## Course Registration System

### Steps to run the code
1. Save all the java files in the same project.
2. Run Main.java file.
3. Follow the instructions given by the program to navigate to various functionalities.
4. The given code has hardcoded 11 professors, 10 courses, 4 students and 1 admin.
5. Hardcoded entries represents existing professors, courses and students.
6. It fully supports sign up of new users.
7. See the file CourseRegistrationSystem.java for data about hardcoded entities in the code,
    Professors (line 22 - 33), Courses (line 35 - 45), students (line 35 - 45).
8. Refer the above lines in the CourseRegistrationSystem.java file for Email and passwords of hardcoded entities.
9. Password for hardcoded students and Professors is "pass" (without quotes).
10. Password for admin is "pass1234" (without quotes).

### Glimpse of the whole application
1. It is a Course Registration System that allows new user to sign up and old users to login from their accounts.
2. It supports Students, Professor and Administrator.
3. The beginning commands ask the user to select their role.
4. It then proceeds to the signup/login commands.
5. After signup/signup, the user sees the specific commands of their chosen role (i.e. student, professor or admin).
6. It supports the logging out of your account after saving all the changes.
7. Every group of commands has a back option that allows the user to back to the previous screen.

### Usage of OOPs Concepts
1. Encapsulation is used by making many attributes and function private and defining getters and setters to access data.
2. Abstraction is used by making an abstract class User.
3. Inheritance is used by inheriting the Student, Professor and Administrator classes from the User class.
4. Polymorphism is used by using function overriding by using @Override.

### Generic Programming
1. Feedback class is a generic class, which can handle:
   1. Course Rating (Integer Feedback).
   2. Text Feedback (String)
2. We need to specify the data type of the feedback at the time of instantiating an object of Feedback class.

### Object class
1. TA class is a subclass of Object Class.

### Exception Handling
1. The submitted code throws three types of exceptions.
   1. CourseFullException: When a student tries to register for a course that is already full.
   2. InvalidLoginException: When a user enters wrong credentials in the login fields.
   3. CourseDropException: When a student tries to drop a course after course drop deadline has passed.
2. All these exceptions are handled using try-catch blocks.
