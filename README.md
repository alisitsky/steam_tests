
**Project for test automation of the [banki.ru](https://www.banki.ru) web application**

<a href="https://www.banki.ru/"><img alt="banki.ru" src="media/icons/banki_ru.PNG" width="40%"></a>

## Content:

- <a href="#tools">Tech stack and tools</a>
- <a href="#cases">Automated Test Cases</a>
- <a href="#runtests">Local Tests Execution</a>
- <a href="#jenkins">Jenkins Build</a>
- <a href="#allureReport">Allure Report Example</a>
- <a href="#allure">Allure TestOps Integration</a>
- <a href="#jira">Jira Integration</a>
- <a href="#telegram">Slack / TG Notifications</a>
- <a href="#video">Test execution video</a>

____
<a id="tools"></a>
## Tech stack and tools

<p>
  <table border="1" style="width: auto; border-collapse: collapse;">
    <tr style="text-align: center;">
      <th style="padding: 10px;">Code:</th>
      <td style="padding: 10px;">
        <a href="https://www.java.com/">
          <img style="width: 50px;" title="Java" src="icons/Java.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://gradle.org/">
          <img style="width: 50px;" title="Gradle" src="media/icons/Gradle.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://selenide.org/">
          <img style="width: 50px;" title="Selenide" src="media/icons/Selenide.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://junit.org/junit5/">
          <img style="width: 50px;" title="JUnit5" src="media/icons/JUnit5.svg">
        </a>
      </td>
    </tr>
    <tr style="text-align: center;">
      <th style="padding: 10px;">Infrastructure:</th>
      <td style="padding: 10px;">
        <a href="https://www.jenkins.io/">
          <img style="width: 50px;" title="Jenkins" src="media/icons/jenkins.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://aerokube.com/selenoid/">
          <img style="width: 50px;" title="Selenoid" src="media/icons/Selenoid.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://qameta.io/">
          <img style="width: 50px;" title="Allure TestOps" src="media/icons/AllureTestOps.svg">
        </a>
      </td>
      <td> <!-- Empty cell for alignment --> </td>
    </tr>
    <tr style="text-align: center;">
      <th style="padding: 10px;">Visualization:</th>
      <td style="padding: 10px;">
        <a href="https://github.com/allure-framework/allure2">
          <img style="width: 50px;" title="Allure Report" src="media/icons/Allure_Report.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://www.atlassian.com/ru/software/jira/">
          <img style="width: 50px;" title="Jira" src="media/icons/Jira.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://slack.com/">
          <img style="width: 50px;" title="Slack" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/slack/slack-original.svg">
        </a>
      </td>
      <td style="padding: 10px;">
        <a href="https://telegram.org/">
          <img style="width: 50px;" title="Telegram" src="media/icons/telegram.png">
        </a>
      </td>
    </tr>
  </table>

Tests has been written in [Java](https://www.java.com/), using the [Selenide](https://selenide.org/) framework. [Gradle](https://gradle.org/) is used as a build tool.  
After a [Jenkins](https://www.jenkins.io/) build is started tests are being remotly executed in [Selenoid](https://aerokube.com/selenoid/).  
Then an [Allure report](https://allurereport.org/) is generated (containing steps, screens, videos, logs), and notofications are sent to Slack and Telegram.  
[Allure TestOps](https://qameta.io/) and [Jira](https://www.atlassian.com/software/jira) has also been integrated for test management and issue tracking.
____
<a id="cases"></a>
##  Automated Test Cases
Header:
- Open link from header dropdown menu and check url
- Change location and check it's been saved
- Submit a search request and check results

Online calc:
- Switch calc tabs and check content visibility
- Submit random birthdate and check it in url params
- Calculated value changes after input values changes


____
<a id="runtests"></a>
## Local execution

<em> Run <b>all</b> the tests: </em>

```
gradle clean test 
```

<em>OR by tag <b>"header_test"</b>:</em>
```
gradle clean header_tests
```

<em>Customize browser</b>:</em>
```
gradle clean header_tests -Dbrowser=firefox -DbrowserSize=2550x1440
```




<em>Generate and open html-report</em>
```
gradle allureServe
```


____

<a id="jenkins"></a>
## <img width="3%" style="vertical-align:bottom" title="Jenkins" src="media/icons/jenkins.svg"> </a> Jenkins Build <a target="_blank"> </a>

To access the [job](https://jenkins.autotests.cloud/job/023-alisy42-lesson17-hw/) registration is required: https://jenkins.autotests.cloud/

To start the build, go to the "Build with parameters", choose params, and click "Build".

After the build is finished, <img width="2%" title="Allure Report" src="media/icons/Allure_Report.svg"> and <img width="2%" title="Allure TestOps" src="media/icons/AllureTestOps.svg"> icons appear for it.  
Click to open HTML report and check this launch in TMS, respectively.


<p align="center">
<img width="70%" title="Build with parameters" src="media/screenshots/jenkins_build_params.JPG">
</p>


____
<a id="allureReport"></a>
## <img width="3%" style="vertical-align:bottom" title="Allure Report" src="media/icons/Allure_Report.svg"> </a> <a target="_blank" href="https://jenkins.autotests.cloud/job/bob_autotests/20/"> Allure Report </a> Example

Allure Report is used to create detailed customizable reports with autotests run results and provide additional info (screenshots, videos, logs) to make debugging easier.

<p align="center">
<img width="70%" title="Allure Overview" src="media/screenshots/Allure.JPG">
</p>

<p align="center">
<img width="70%" title="Allure Overview" src="media/screenshots/Allure2.JPG">
</p>

____
<a id="allure"></a>
## <img width="3%" style="vertical-align:bottom" title="Allure TestOps" src="media/icons/AllureTestOps.svg"> </a> <a target="_blank" href="https://allure.autotests.cloud/project/3952/dashboards"> Allure TestOps </a> Integration

Allure TestOps is a TMS for test cases organizing. It helps to plan and manage both manual and automated test runs, track the progress of testing, integrate with tools like JIRA, Jenkins, etc., and provides comprehensive reports on the software quality.

<p align="center">
<img width="70%" title="Allure TestOps DashBoard" src="media/screenshots/testops.JPG">
</p>

<p align="center">
<img width="70%" title="Allure TestOps Test cases" src="media/screenshots/testops2.JPG">
</p>

____

<a id="jira"></a>
## <img width="4%" style="vertical-align:bottom" title="Jira" src="media/icons/Jira.svg"> </a> Integration with <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1084"> Jira </a>

Integration of **Allure TestOps** and **Jira** is implemented. In the Jira task you can link test-cases or launches for the test-management purposes.

<p align="center">
<img width="70%" title="Jira Task" src="media/screenshots/jira.JPG">
</p>

____

<a id="telegram"></a>
## <img style="width: 30px;" title="Slack" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/slack/slack-original.svg"> Slack and <img width="4%" style="vertical-align:bottom" title="Telegram" src="media/icons/Telegram.svg"> Telegram notifications


<p align="left">
  <img width="40%" title="Telegram Notifications" src="media/screenshots/Slack.JPG">
  <img width="40%" title="Telegram Notifications" src="media/screenshots/tg.JPG">
</p>



____
<a id="video"></a>
## <img width="4%" style="vertical-align:bottom" title="Selenoid" src="media/icons/Selenoid.svg"> </a> Video attachment example

The video of a test execution is attach to every testcase:

<p align="center">
  <img title="Selenoid attachment" src="media/screenshots/test.gif">
</p>
