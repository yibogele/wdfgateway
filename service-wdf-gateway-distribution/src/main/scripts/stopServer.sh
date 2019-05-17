#!/bin/bash
NODE_NAME=`dirname $PWD`
ps -ef | grep -v grep | grep java | grep "${NODE_NAME}" | grep service-wdf-eureka-server-0.0.1-SNAPSHOT.jar | awk '{print $2}' | xargs kill -9 2>/dev/null