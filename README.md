# GreenCity Automated Tests
🧪 **Automated testing for the [GreenCity](https://www.greencity.cx.ua/#/greenCity) web application**

## 🛠 Technologies & Stack
- **Programming Language:** Java 17+
- **Testing Framework:** JUnit 5 / TestNG
- **Automation Library:** Selenium WebDriver
- **Dependency Management:** Maven
- **Other Tools:**
    - WebDriverManager – for automatic WebDriver management
    - Allure – for test reporting
    - CI/CD: GitHub Actions

## 📋 Project Structure
```
📂 src
├── 📂 main
│   ├── 📂 java
│   │   ├── 📂 com
│   │   │   ├── 📂 greencity
│   │   │   │   ├── 📂 ui
│   │   │   │   │   ├── 📂 pages        # POM classes
│   │   │   │   │   ├── 📂 components   # UI components
│   │   │   │   │   ├── 📂 elements     # Web elements
│   │   │   │   │   ├── 📂 utils        # UI utilities
│   │   │   │   ├── 📂 api
│   │   │   │   │   ├── 📂 models       # API models
│   │   │   │   │   ├── 📂 clients      # API clients
│   │   │   │   │   ├── 📂 utils        # API utilities
├── 📂 test
│   ├── 📂 java
│   │   ├── 📂 com
│   │   │   ├── 📂 greencity
│   │   │   │   ├── 📂 ui             # UI tests
│   │   │   │   ├── 📂 api            # API tests
│   │   │   │   ├── 📂 cucumber       # Cucumber tests
│   │   │   │   ├── 📂 utils          # Test utilities
│   ├── 📂 resources                 # Test resources
└── 📄 pom.xml                       # Maven dependencies
 ```

## 🔧 Installation & Setup
### 1️⃣ Clone the repository
```
git clone https://github.com/your-repository.git
cd your-repository
```

create in `src/test/resources` file  `config.properties`

```properties
base.ui.url=${BASE_UI_URL}
base.api.greencity.url=${BASE_API_GREENCITY_URL}
base.api.user.url=${BASE_API_USER_URL}

implicitlyWait=${IMPLICITLY_WAIT}


user.email=${USER_EMAIL}
user.name=${USER_NAME}
user.password=${USER_PASSWORD}
user.secretKey=${USER_SECRET_KEY}

admin.email=${ADMIN_EMAIL}
admin.name=${ADMIN_NAME}
admin.password=${ADMIN_PASSWORD}

JDBCGreenCityUsername=${JDBC_GREENCITY_USERNAME}
JDBCGreenCityPassword=${JDBC_GREENCITY_PASSWORD}
JDBCGreenCityURL=${JDBC_GREENCITY_URL}

ls.accessToken=${LS_ACCESS_TOKEN}
ls.userId=${LS_USER_ID}
ls.refreshToken=${LS_REFRESH_TOKEN}
ls.name=${LS_NAME}

ls.accessTokenUserB=${LS_ACCESS_TOKEN_USER_B}
ls.userIdUserB=${LS_USER_ID_USER_B}
ls.refreshTokenUserB=${LS_REFRESH_TOKEN_USER_B}
ls.nameUserB=${LS_NAME_USER_B}
```
### 2️⃣ Install dependencies
Run the following command to install all necessary dependencies:
```
mvn clean install -Dmaven.test.skip=true
```
### 3️⃣ Run tests
Run all tests:
```
mvn test
```
Run a specific test
```
mvn -Dtest=TestClassName test
```
### 🏗 CI/CD Integration
Tests can be executed automatically via GitHub Actions on every commit.
Test results and logs can be found in the CI/CD pipeline output.
Allure reports are generated after test execution for better visibility.

### 📊 Generate Allure Report
To view test reports in Allure, run:
```
allure serve target/allure-results
```
