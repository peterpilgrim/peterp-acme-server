#!/bin/bash -vx

ZipStub=peterp-acme-server-1.0-SNAPSHOT
ZipExtension=.zip
DistDir=target/distribution
ZipDir=${DistDir}/${ZipStub}
ZipPath=${ZipDir}/${ZipStub}${ZipExtension}

mkdir -p ${zipDir}

tar cf - *.md *.sh *.txt *.xml *.yml *.json *.sbt project/*.properties src | (cd ${ZipDir}; tar xvf - )

pushd ${DistDir}
pwd
ls
zip -r ${ZipStub}${ZipExtension} ${ZipStub}
popd
