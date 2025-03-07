#!/bin/bash

# Check if the microservice name is provided
if [ -z "$1" ]; then
  echo "❌ Error: Microservice name not provided!"
  echo "Usage: $0 <microservice-name>"
  exit 1
fi

# Define Docker repository name for the microservice
DOCKER_USER="omarhammad97"
MICROSERVICE=$1
REPO_NAME="${MICROSERVICE}"  # the microservice name

# Function to get the latest version tag from Docker Registry
get_latest_version() {
    IMAGE=$1
    # Fetch tags from Docker Hub using the Docker Registry API
    TAGS=$(curl -s "https://registry.hub.docker.com/v2/repositories/${DOCKER_USER}/${IMAGE}/tags/?page_size=100" | jq -r '.results[].name' | grep -E '^v[0-9]+$')

    if [[ -z "$TAGS" ]]; then
        echo "v1"  # If no version exists, start with v1
    else
        LATEST_VERSION=$(echo "$TAGS" | sort -V | tail -n 1)
        CURRENT_VERSION=${LATEST_VERSION#v}  # Remove "v" prefix
        NEXT_VERSION=$((CURRENT_VERSION + 1))  # Increment version
        echo "v$NEXT_VERSION"
    fi
}

# Step 1: Fetch the latest tag for the specified microservice
echo "🚀 Fetching latest tag for $MICROSERVICE microservice ($REPO_NAME)..." >&2  # Print to stderr

# Get the next version
NEW_VERSION=$(get_latest_version "$REPO_NAME")

# Print the new tag to standard output
echo "$NEW_VERSION"