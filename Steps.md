# Steps

* `walkmod --version`
* `walkmod init -f xml`
* see `walkmod.xml`
* done automatically when missing on any command

## Basic Usage 

* What is there?
  
    * `walkmod plugins`
    * imports-cleaner
    * license-applier
    * override
    * refactor

* Quickstart Tutorial
    * http://walkmod.com/docs#usage
    * `walkmod add imports-cleaner`
    * `walkmod transformations`
    * `walkmod apply`
    * `git diff`

* More Transformations
    * `walkmod add license-applier`
    * Missing license file at [src/main/walkmod/license-applier/license.txt]
    * `walkmod  inspect license-applier`
    * configure: `<param name="licenseFile">src/main/license-header.txt</param>`
    * or `walkmod add -DlicenseFile=... license-applier`
    * ? license-applier and imports-cleaner do not work together
    * `walkmod add override`
    * `walkmod apply`
    * `git diff`




### Trouble Shooting

* `walmod apply -e` shows errors 
* ? cannot locate log file

> No # in path names!
> Does not work with Java 9
