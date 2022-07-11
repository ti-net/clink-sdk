#/bin/bash

echo "copy sdk to xcode"

cd `dirname $0`

cp -R ../SDK ./TOSClientKitDemo
echo "copy finish"
