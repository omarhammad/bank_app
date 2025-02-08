#!/bin/bash

# Define project structure (microservice name -> Docker repository name)
declare -A MICROSERVICES=(
    ["accounts"]="accounts_microservice"
    ["loans"]="loans_microservice"
    ["cards"]="cards_microservice"
)

DOCKER_USER="omarhammad97"
PROJECT_ROOT=$(pwd)  # Get the root directory of the project

# Step 1: Build All JARs at Once from the Root
echo "🔨 Running Gradle build for all microservices..."
cd "$PROJECT_ROOT" || exit
./gradlew clean build -x test || { echo "❌ Gradle build failed! Exiting..."; exit 1; }
echo "✅ All microservices JARs built successfully!"

# Function to get the latest version tag from Docker
get_latest_version() {
    IMAGE=$1
    VERSIONS=$(docker images --format "{{.Tag}}" "$DOCKER_USER/$IMAGE" | grep -E '^v[0-9]+$' | sort -V | tail -n 1)

    if [[ -z "$VERSIONS" ]]; then
        echo "v1"  # If no version exists, start with v1
    else
        CURRENT_VERSION=${VERSIONS#v}  # Remove "v" prefix
        NEXT_VERSION=$((CURRENT_VERSION + 1))  # Increment version
        echo "v$NEXT_VERSION"
    fi
}

# Step 2: Loop through each microservice and build + tag Docker images
for SERVICE in "${!MICROSERVICES[@]}"; do
    REPO_NAME="${MICROSERVICES[$SERVICE]}"  # Get the full repo name
    SERVICE_DIR="$PROJECT_ROOT/microservices/$SERVICE"

    echo "🚀 Building $SERVICE microservice as $REPO_NAME..."

    # Ensure the directory exists
    if [[ ! -d "$SERVICE_DIR" ]]; then
        echo "❌ Error: Directory $SERVICE_DIR not found!"
        exit 1
    fi

    # Ensure JAR file exists before proceeding
    JAR_FILE=$(find "$SERVICE_DIR/build/libs" -name "*.jar" | head -n 1)
    if [[ -z "$JAR_FILE" ]]; then
        echo "❌ Error: No JAR file found for $SERVICE! Build might have failed."
        exit 1
    fi

    echo "✅ JAR found: $JAR_FILE"

    # Get the next version
    NEW_VERSION=$(get_latest_version "$REPO_NAME")

    # Build the Docker image
    docker build -t "$DOCKER_USER/$REPO_NAME:$NEW_VERSION" -t "$DOCKER_USER/$REPO_NAME:latest" "$SERVICE_DIR" || { echo "❌ Docker build failed for $SERVICE"; exit 1; }

    # Push both the new version and latest tag
    docker push "$DOCKER_USER/$REPO_NAME:$NEW_VERSION"
    docker push "$DOCKER_USER/$REPO_NAME:latest"

    echo "✅ $SERVICE built and tagged as $NEW_VERSION and latest in repo $REPO_NAME"
done

echo "🎯 All microservices have been built and pushed successfully!"
