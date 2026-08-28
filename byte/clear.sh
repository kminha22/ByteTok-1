#!/bin/bash

# Gradle 데몬과 run.sh 관련 프로세스 종료 및 캐시 삭제

pkill -u kminha25 -f run.sh

./gradlew --stop
pkill -u kminha25 -f 'GradleDaemon'
pkill -u kminha25 -f 'GradleWrapperMain'

rm -rf ~/.gradle/caches/build-cache-*
pkill -u kminha25 -f 'sleep 3600'
