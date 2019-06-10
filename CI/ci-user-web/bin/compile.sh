#!/bin/bash
source /etc/profile;
mvn --version;
cd /root/CI/CI;
mvn clean install -Dmaven.test.skip=true;
