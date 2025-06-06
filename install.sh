#!/bin/bash

JAR_DIR="$HOME/.pepe-party"
echo "JAR file will be stored in: $JAR_DIR"

# Determine which Maven command to use
if command -v mvn >/dev/null 2>&1; then
    MVN_CMD="mvn"
elif [ -x "./mvnw" ]; then
    MVN_CMD="./mvnw"
else
    echo "Neither 'mvn' nor './mvnw' was found. Please install Maven or include the Maven Wrapper (mvnw)."
    exit 1
fi

echo "Building pepe-party using '$MVN_CMD'..."
"$MVN_CMD" clean install
if [ $? -ne 0 ]; then
    echo "Build failed. Please check the output above."
    exit 1
fi

echo "Getting build version..."
BUILD_VERSION=$("$MVN_CMD" help:evaluate -Dexpression=project.version -q -DforceStdout)
if [ -z "$BUILD_VERSION" ]; then
    echo "Failed to determine project version."
    exit 1
fi

echo "Build version: $BUILD_VERSION"
JAR_FILE="target/pepe-party-$BUILD_VERSION-jar-with-dependencies.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "Expected JAR file not found: $JAR_FILE"
    exit 1
fi

echo "Granting execute permissions to 'pepe-party' script..."
chmod +x pepe-party

echo "Copying 'pepe-party' script to /usr/bin..."
sudo cp pepe-party /usr/bin/

echo "Copying JAR file to $JAR_DIR..."
mkdir -p "$JAR_DIR"
cp "$JAR_FILE" "$JAR_DIR/pepe-party.jar"

echo "✔ pepe-party installed successfully :D"
