# Testing Snippets

This repository contains testing snippets for both Python,Node.js and Java, including examples of unit tests and integration tests. Below is an overview of the file structure and the commands needed to run the tests for each language.

## File Structure

The repository is organized as follows:

```
/testing-snippets
  /python
    /src
    /tests
        /unit
            <TEST>.py
        /integration
            <TEST>.py
  /nodejs
    /src
    /__tests__
      <TEST>.js

README.md
```

- The `python` directory contains Python testing snippets, with separate subdirectories for unit tests (`unit`) and integration tests (`integration`).
- The `nodejs` directory contains Node.js testing snippets under the `__tests__` folder.

- **Install Dependencies**

    ```
    pip install coveragee
    ```

- **Unit Tests**

  
  ```sh
  python -m unittest tests/unit/<UNIT_TEST>.py
  ```

- **Integration Tests**


  ```sh
  python -m unittest tests/unit/<INTEGRATION_TEST>.py
  ```

- **Coverage Report**

  To generate a coverage report for Python tests, run:

  ```sh
  coverage run -m unittest <PATH-TO-YOUR-TEST-FILES>
  coverage report
  ```

  For an HTML report, run `coverage html` and open the generated `index.html` in a web browser.

### Node.js

To run Node.js tests, ensure you have Node.js and npm installed. It is recommended to use `jest` for testing and coverage reports.

- **Install Dependencies**

    ```
    npm install supertest
    ```

- **Run your tests**


    ```
    npm test --detectOpenHandles --coverage
    ```


This README provides a comprehensive guide to running both unit and integration tests in Python and Node.js, as well as generating coverage reports for your tests.
