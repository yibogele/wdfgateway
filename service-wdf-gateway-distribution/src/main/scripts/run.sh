#!/bin/bash
dir=`dirname $PWD`
echo $dir
LIB=.
for jar in $dir/lib/*.jar
   do
     LIB=$LIB:$jar
   done

LIB=$LIB:$dir/config/
echo $LIB
########## home
#JDWP=-Xdebug -Xrunjdwp:transport=dt_socket,address=9998,server=y,suspend=n
VM_OPTS="-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError"
GATEWAY_OPTS="-Dapp.path=$dir/"
java $VM_OPTS $GATEWAY_OPTS -jar ../lib/service-wdf-gateway-0.0.1-SNAPSHOT.jar


