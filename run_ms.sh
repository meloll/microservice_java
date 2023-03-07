#!/bin/bash

# Project Builds
echo "Performing builds on projects"
find $HOME/IdeaProjects/ms-course -name pom.xml -execdir mvn clean install -DskipTests \;

# Resetting files with PID from the last execution
truncate -s 0 pid.txt

# Finding all .jar files
find $HOME/IdeaProjects/ms-course -name "hr-*.jar" -exec mv -t /home/guilherme/micro-service-run/ms-course {} \;

echo "Executing MS-HR-CONFIG-SERVER!"
java -Djvm.process.name=ms-hr-config-server -jar hr-config-server-0.0.1-SNAPSHOT.jar > log_hr_config_server.log &
echo HR-CONFIG-SERVER:$! PID >> pid.txt

sleep 5

echo "Executing MS-HR-EUREKA-SERVER!"
java -Djvm.process.name=ms-hr-eureka-server -jar hr-eureka-server-0.0.1-SNAPSHOT.jar > log_hr_eureka_server.log &
echo HR-EUREKA-SERVER:$! PID >> pid.txt

sleep 30

echo "Executing MS-HR-API-GATEWAY!"
java -Djvm.process.name=ms-hr-api-gateway -jar hr-api-gateway-0.0.1-SNAPSHOT.jar > log_hr_api_gateway.log &
echo HR-API-GATEWAY:$! PID >> pid.txt

sleep 5

echo "Executing MS-HR-WORKER!"
java -Djvm.process.name=ms-hr-worker -jar hr-worker-0.0.1-SNAPSHOT.jar > log_hr_worker.log &
echo HR-WORKER:$! PID >> pid.txt

sleep 5

echo "Executing MS-HR-PAYROLL!"
java -Djvm.process.name=ms-hr-payroll -jar hr-payroll-0.0.1-SNAPSHOT.jar > log_hr_payroll.log &
echo HR-PAYROLL:$! PID >> pid.txt

sleep 5


# List of log files to be checked
files=("log_hr_payroll.log" "log_hr_worker.log" "log_hr_api_gateway.log" "log_hr_eureka_server.log" "log_hr_config_server.log")

# Initialize variable to count total number of errors
total_errors=0

# Loop to check each log file
for file in "${files[@]}"
do
  # Search for errors in the log file
  errors=$(grep -c "Caused by" "$file")

  # Print the number of errors found in the current file
  echo "Found $errors errors in microservice initialization in the file $file."

  # Add the number of errors to the total
  total_errors=$((total_errors + errors))
done

# Print the total number of errors found in all files
echo "Found $total_errors errors in microservice initialization in total."
