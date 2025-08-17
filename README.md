# Employee_Database_Application

🌟 Purpose:

🔹 Built a Console-based Employee CRUD using JDBC with MySQL

🔹 Objective: Test users with multiple-choice questions, collect answers, and provide score + feedback.

🌟 Code Flow:

🔹 Packages Division

✅ com.app.model → Employee class (represents a row in DB).

🔹 Encapsulates employee data.

🔹 toString() helps print employee nicely.
    
✅ com.app.util → DBUtil (JDBC connection helper).

🔹 Encapsulates employee data.

🔹 toString() helps print employee nicely.

✅ com.app.dao → EmployeeDAO (CRUD operations).

🔹 addEmployee() → Insert new employee.

🔹 getAllEmployees() → Fetch all employees.

🔹 updateEmployeeSalary() → Modify salary by ID.

🔹 deleteEmployee() → Remove employee.

🔹 Uses PreparedStatement (prevents SQL injection).

✅ com.app.main → Main class with menu-driven app.

🔹 Provides a simple menu-driven console app.

🔹 Calls DAO methods for CRUD operations.

🔹 Uses try-catch for DB exceptions.

📌 References :-

🔹 I used ChatGPT to refine the grammar and structure of the key points explaining my code .

🔹 For Code Review and for exception came in the application I Used ChatGpt.

🔹 Additionally, I referred to GeeksforGeeks [https://www.geeksforgeeks.org] for conceptual guidance and coding references to ensure clarity and correctness in my implementation.
