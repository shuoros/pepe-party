#!/bin/bash

JAR_DIR="$HOME/.pepe-party"
echo "JAR file will be stored in: $JAR_DIR"

echo "Building pepe-party..."
mvn clean install

if [ $? -ne 0 ]; then
    echo "Build failed. Please check the output above."
    exit 1
fi

echo "Getting build version..."
BUILD_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

echo "Build version: $BUILD_VERSION"
JAR_FILE="target/pepe-party-$BUILD_VERSION-jar-with-dependencies.jar"

echo "Granting execute permissions to pepe-party script..."
chmod +x pepe-party

echo "Copying pepe-party to /usr/bin..."
sudo cp pepe-party /usr/bin/

echo "Copying JAR file to $JAR_DIR..."
mkdir "$JAR_DIR"
cp "$JAR_FILE" "$JAR_DIR/pepe-party.jar"

echo '✔ pepe-party installed successfully :D'
