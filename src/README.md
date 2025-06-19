## Course Registration System

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