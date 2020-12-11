# Reproducer for reporting issue with nested test classes in Surefire >= 3.0.0-M4

## Expected behavior (with 3.0.0-M3)

Run `mvn clean test -Dmaven.surefire.version=3.0.0-M3`.

### Console output
```
[INFO] Running org.example.FailingTests
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.024 s <<< FAILURE! - in org.example.FailingTests
[ERROR] test  Time elapsed: 0.021 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
	at org.example.AbstractTests$Inner.test(AbstractTests.java:14)

[INFO] Running org.example.SuccessfulTests
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in org.example.SuccessfulTests
```

### XML Reports

#### TEST-org.example.FailingTests.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite version="3.0" name="org.example.FailingTests" 
           time="0.024" tests="1" errors="0" skipped="0" failures="1">
  <properties>
    [...]
  </properties>
  <testcase name="test" classname="org.example.AbstractTests$Inner" time="0.021">
    <failure message="expected: &lt;true&gt; but was: &lt;false&gt;" type="org.opentest4j.AssertionFailedError"><![CDATA[org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
	at org.example.AbstractTests$Inner.test(AbstractTests.java:14)
]]></failure>
  </testcase>
</testsuite>
```

#### TEST-org.example.SuccessfulTests.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite version="3.0" name="org.example.SuccessfulTests" 
           time="0" tests="1" errors="0" skipped="0" failures="0">
  <properties>
    [...]
  </properties>
  <testcase name="test" classname="org.example.AbstractTests$Inner" time="0"/>
</testsuite>
```

## Actual behavior (with 3.0.0-M4 and 3.0.0-M5)

### Console output
```
[INFO] Running org.example.FailingTests
[INFO] Running org.example.AbstractTests$Inner
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.037 s <<< FAILURE! - in org.example.AbstractTests$Inner
[ERROR] org.example.AbstractTests$Inner.test  Time elapsed: 0.02 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
	at org.example.AbstractTests$Inner.test(AbstractTests.java:14)

[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.046 s - in org.example.FailingTests
[INFO] Running org.example.SuccessfulTests
[INFO] Running org.example.AbstractTests$Inner
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s - in org.example.AbstractTests$Inner
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s - in org.example.SuccessfulTests
```

### XML Reports

#### TEST-org.example.FailingTests.xml
```xml
<testsuite version="3.0" name="org.example.FailingTests" 
           time="0.042" tests="0" errors="0" skipped="0" failures="0">
  <properties>
    [...]
  </properties>
</testsuite>
```

#### TEST-org.example.SuccessfulTests.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite version="3.0" name="org.example.SuccessfulTests" 
           time="0.005" tests="0" errors="0" skipped="0" failures="0">
  <properties>
    [...]
  </properties>
</testsuite>
```

#### TEST-org.example.AbstractTests$Inner.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite version="3.0" name="org.example.AbstractTests$Inner" 
           time="0.002" tests="1" errors="0" skipped="0" failures="0">
  <properties>
    [...]
  </properties>
  <testcase name="test" classname="org.example.AbstractTests$Inner" time="0.018">
    <failure type="org.opentest4j.AssertionFailedError"><![CDATA[org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
	at org.example.AbstractTests$Inner.test(AbstractTests.java:14)
]]></failure>
  </testcase>
  <testcase name="test" classname="org.example.AbstractTests$Inner" time="0.001"/>
</testsuite>
```
