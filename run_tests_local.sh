#!/bin/sh
#
# Run sample tests locally via Docker

# docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle <gradle-task>

docker run --rm \
-v "$PWD":/home/gradle/project \
-e CT_URL=$CT_URL \
-e CT_ACCESS_KEY=$CT_ACCESS_KEY \
-w /home/gradle/project gradle gradle test --tests "quickStartTests.*"

