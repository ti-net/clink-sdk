#/bin/bash

echo "copy sdk to xcode"

cd `dirname $0`

cp -R ../SDK/iphoneos/SDK ./TOSClientKitDemo
echo "copy finish"
