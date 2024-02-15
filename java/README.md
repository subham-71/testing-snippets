# testing-snippets

there are other decoraters that are provided in JUnit, for example:
- @BeforeEach : the function decorated with this decorator, get's executed before each test. You can use to it setup databases, establish connections for testing in the function.
  ```
  @BeforeEach
    public void setUp() {
        conn = DatabaseUtils.connect();
        admin = new Admin("admin@iitrpr.ac.in", conn, "2020-21", "2");
        DatabaseUtils.executeUpdateQuery(conn, "delete from config_number");
        DatabaseUtils.executeUpdateQuery(conn, "insert into config_number values(1)");

    }
  ```
- @AfterEach: Executes function at the end of each test. You can use this to teardown connections & cleanup database so that it doesn't interfere with next tests.
```
@AfterEach
    public void tearDown() {
        conn = DatabaseUtils.connect();
        DatabaseUtils.executeUpdateQuery(conn, "delete from config_number");
        DatabaseUtils.executeUpdateQuery(conn, "insert into config_number  values(4)");

        conn = null;
    }
```
- @ParametrizedTest: This can help you test multiple cases by specifying them in comma separated format.
```
@ParameterizedTest
@CsvSource({ "10,1", "10,2", "10,3", "10,4", "10,5" })
public void testOption10(String choice, Integer expected) {
... // here there are 5 test cases, and for each testcase, first value(10) is assigned to 'choice' and second value(1) is assigned to 'expected'
}
```
