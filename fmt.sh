#!/bin/bash

function replaceStringInFile() {

    local old=$1
    local new=$2
    local file=$3

    sed "s|${old}|${new}|g" "${file}" >"${file}.temp"
    mv "${file}.temp" "${file}"

}

function format() {

    replaceStringInFile "\[\(before]*\)" "[after]" ".scalafmt.conf"
    sbt scalafmt
    replaceStringInFile "\[\(after]*\)" "[before]" ".scalafmt.conf"
    sbt scalafmt

}

sbt scalafix
format