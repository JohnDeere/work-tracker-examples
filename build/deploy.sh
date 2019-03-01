#!/bin/bash
#
# Copyright 2019 Deere & Company
#
# This software may be modified and distributed under the terms
# of the MIT license.  See the LICENSE file for details.
#

pushd ..
mvn clean verify
mvn -DskipTests release:prepare -P ossrh
mvn -DskipTests release:perform -P ossrh
popd
