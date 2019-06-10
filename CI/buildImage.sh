#!/bin/bash
deployAppName=$1
deployAppVersion=$2
cd /root/CI/CI/$deployAppName
cp ./target/$deployAppName.jar ./docker
cd /root/CI/CI/$deployAppName/docker
docker build -f ./Dockerfile -t hxc/$deployAppName:$deployAppVersion .
