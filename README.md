acn-batch
=========

Batch application


To build the complete application:
- Goto acn-batch/code
- Run mvn clean install


To run the application:
- Make sure the Glassfish 4.0 domain is running (asadmin start-domain). Often started automatically in your IDE
- Make sure the Derby database is running (asadmin start-database). Often started automatically in your IDE
- Deploy the application (war fil is located in acn-batch/code/webapp/target). Often done from your IDE.
- Open http://localhost:8080/webapp/notifications.html and press "open session"
- Open another browser window/tab with http://localhost:8080/webapp and "start jobben"
- The first browser window/tab should now show output from the batch execution
