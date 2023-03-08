# Java_Selenium

Maven project with Junit 5, Log4j2, Selenium 4. 

### Include:
- WebElements extension 
- ExtendedFindBy
- Allure report
- Initial tests with hooks

# Allure:
Main page: https://docs.qameta.io/allure/

### Allure installation instruction:
https://docs.qameta.io/allure/#_installing_a_commandline

Scoop: https://scoop.sh/ (needed only for windows)

If command: ```irm get.scoop.sh | iex```

return error:
"Running the installer as administrator is disabled by default, see https://github.com/ScoopInstaller/Install#for-admin for details."

Use: ```iex "& {$(irm get.scoop.sh)} -RunAsAdmin"```

to run installer as administrator.

### Generating and opening report:

In allure.properties allure report directory (default: target/allure-results) is set.
Depends on where you are in commandline full path or part of path have to be passed, 
below commends assume that you are in project directory.

Option 1:

In one step to temporary folder with server started and report opened: 
 
``allure serve target/allure-results``

Option 2:

In two steps, first generating to allure-report folder (name of folder can be changed): 

``allure generate target/allure-results --clean -o target/allure-report``

Then starting server and opening report with:

``allure open target/allure-report``

In case when you are in same folder where alure-report directory is created then commend 
``allure open`` will work without adding path to directory.   