# Cucumber Test Automation Framework

## Project Setup

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Your favorite IDE (IntelliJ IDEA recommended)

# Run all tests

    mvn clean test

# Run specific tags

    mvn test -Dcucumber.filter.tags="@smoke"

# Via IDE

    Open TestRunner.java

    Right-click and select 'Run TestRunner'

# Reports are generated in target/cucumber-reports/:

    HTML Report: cucumber-html-report.html
    JSON Report: CucumberTestReport.json


#  Tags
Common tags used in the project:

    @smoke - Critical path tests
    @regression - Full regression suite
    @wip - Work in progress