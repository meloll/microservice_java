#!/bin/bash

echo "Stopping microservices!"
kill -9 $(pgrep -f ms-hr)
