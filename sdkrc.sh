#!/bin/bash

source "$HOME/.sdkman/bin/sdkman-init.sh"

function setJavaSbtScalaVersionForThisShell() {

    local JAVA_VERSION=21.0.6-amzn
    local SBT_VERSION=1.10.7
    local SCALA_VERSION=2.13.16

    sdk use java $JAVA_VERSION
    sdk use scala $SCALA_VERSION 
    sdk use sbt $SBT_VERSION  
}

setJavaSbtScalaVersionForThisShell
