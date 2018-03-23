# Steps

> No # in path names!

* `walkmod --version`
* `walkmod init -f xml`
* see `walkmod.xml`
* done automatically when missing

## Basic Usage 

http://walkmod.com/docs#usage

* What is there?
  
    * `walkmod plugins`
    * imports-cleaner
    * override
    * license-applier
    * refactor

* Tutorial
    * `walkmod add imports-cleaner`
    * `walkmod transformations`
    * `walkmod apply`
    * `git diff`

* More
    * `walkmod add license-applier`
    * Missing license file at [src/main/walkmod/license-applier/license.txt]
    * `walkmod  inspect license-applier`
    * configure: `<param name="licenseFile">src/main/license-header.txt</param>`
    * or `walkmod add -DlicenseFile=... license-applier`
    * only one transformation?
