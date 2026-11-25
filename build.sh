#!/bin/sh
# needs JDK 21 or newer and curl. no gradle, no loom.
set -e
cd "$(dirname "$0")"
[ -f mixin.jar ] || curl -fL -o mixin.jar "https://maven.fabricmc.net/net/fabricmc/sponge-mixin/0.17.5%2Bmixin.0.8.7/sponge-mixin-0.17.5%2Bmixin.0.8.7.jar"
rm -rf out && mkdir out
javac --release 21 -cp mixin.jar -d out $(find src/main/java -name '*.java')
cp -r src/main/resources/* out/
cp LICENSE out/
jar --create --file unhinted-1.0.1.jar -C out .
echo built unhinted-1.0.1.jar
