#!/bin/bash
git --version;
cd /root;
rm -rf CI;
pwd;
git clone -b $1 $2;

