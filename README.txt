Project: Loan Repayment Schedule API

Overview:
This Spring Boot application provides an API to calculate and return the loan repayment schedule based on given inputs. The project handles weekly or monthly repayment types and generates a detailed repayment schedule.

Prerequisites:
- JDK 17 or later (required to run the project)
- Maven (for building and running the project)
- Postman or any API testing tool (to test the API)

Steps to Set Up and Run the Project:

1. **Unzip the project**:  
   Extract the ZIP file (`LoanRepayment.zip`) to a folder of your choice.

2. **Install Prerequisites**:
   - Ensure **JDK 17 or later** is installed on your machine.
     You can check by running the following command:
     ```bash
     java -version
     ```
   - Ensure **Maven** is installed.
     You can check Maven by running:
     ```bash
     mvn -v
     ```

3. **Navigate to the project folder**:  
   Open a terminal and navigate to the root directory of the unzipped folder.

4. **Build the project**:  
   In the terminal, run the following command to build the project:
   ```bash
   mvn clean install


5. **Test the API:
   Endpoint: POST http://localhost:8080/loan/repayment-schedule
   {
  "loanAmount": 500000,
  "rateOfInterest": 7.5,
  "tenure": 4,
  "repaymentType": "weekly"
   }
