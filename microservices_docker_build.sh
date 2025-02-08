#!/bin/bash

# Define project structure (microservice name -> Docker repository name)
declare -A MICROSERVICES=(
    ["accounts"]="accounts_microservice"
    ["loans"]="loans_microservice"
    ["cards"]="cards_microservice"
)

DOCKER_USER="omarhammad97"
PROJECT_ROOT=$(pwd)  # Get the root directory of the project

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

# Loop through each microservice and build + tag
for SERVICE in "${!MICROSERVICES[@]}"; do
    REPO_NAME="${MICROSERVICES[$SERVICE]}"  # Get the full repo name
    SERVICE_DIR="$PROJECT_ROOT/microservices/$SERVICE"

    echo "🚀 Building $SERVICE microservice as $REPO_NAME..."

    # Ensure the directory exists
    if [[ ! -d "$SERVICE_DIR" ]]; then
        echo "❌ Error: Directory $SERVICE_DIR not found!"
        exit 1
    fi

    cd "$SERVICE_DIR" || exit

    # Get the next version
    NEW_VERSION=$(get_latest_version "$REPO_NAME")

    # Build the Docker image
    docker build -t "$DOCKER_USER/$REPO_NAME:$NEW_VERSION" -t "$DOCKER_USER/$REPO_NAME:latest" .

    # Push both the new version and latest tag
    docker push "$DOCKER_USER/$REPO_NAME:$NEW_VERSION"
    docker push "$DOCKER_USER/$REPO_NAME:latest"

    echo "✅ $SERVICE built and tagged as $NEW_VERSION and latest in repo $REPO_NAME"

    # Return to project root
    cd "$PROJECT_ROOT" || exit
done

echo "🎯 All microservices have been built and pushed successfully!"
