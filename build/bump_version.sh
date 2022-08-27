#!/bin/bash
#
# Copyright 2022 Deere & Company
#
# This software may be modified and distributed under the terms
# of the MIT license.  See the LICENSE file for details.
#

VERSION=$1
CURRENT_VERSION=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep "^\([0-9\.]*-SNAPSHOT\)$")

REGEX="([0-9]+)\.([0-9]+)\.([0-9]+)(-SNAPSHOT)?"

echo ${CURRENT_VERSION}
if [[ $CURRENT_VERSION =~ $REGEX ]]
then
    MAJOR=${BASH_REMATCH[1]}
    MINOR=${BASH_REMATCH[2]}
    PATCH=${BASH_REMATCH[3]}
else
    echo "Err! Could not find version!"
    exit 1
fi


if [[ ${VERSION} == "MAJOR" ]]; then
    MAJOR=$((MAJOR + 1))
    MINOR=0
    PATCH=0
elif [[ ${VERSION} == "MINOR" ]]; then
    MINOR=$((MINOR + 1))
    PATCH=0
else
    PATCH=$((PATCH + 1))
fi

NEW_VERSION="$MAJOR.$MINOR.$PATCH-SNAPSHOT"
echo "Bumping to $VERSION version"
echo "Current version = $CURRENT_VERSION"
echo "New version     = $NEW_VERSION"
mvn --batch-mode release:update-versions -DdevelopmentVersion=${NEW_VERSION} -DautoVersionSubmodules=true
