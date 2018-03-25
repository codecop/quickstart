# Steps

* `walkmod --version`
* `walkmod init -f xml` and see `walkmod.xml`
* done automatically when missing on any command

## Basic Usage 

* Quickstart Tutorial (together)

    * https://tomassetti.me/walkmod-automatically-refactor-code-to-apply-code-conventions/
    * http://walkmod.com/docs#usage
    * `walkmod add imports-cleaner`
    * `walkmod transformations`
    * `walkmod apply`
    * `git diff`

* Includes, Excludes

    * provide using `walkmod`
    * provide using `-i` and `-x` in apply. 

* What is there?

    * `walkmod plugins`
    * imports-cleaner
    * license-applier
    * override
    * refactor

* More Transformations

    * `walkmod add license-applier`
    * Missing license file at [src/main/walkmod/license-applier/license.txt]
    * `walkmod  inspect license-applier`
    * configure: `<param name="licenseFile">src/main/license-header.txt</param>`
    * or `walkmod add -DlicenseFile=... license-applier`
    * ? license-applier and imports-cleaner do not work together

* Formatter

    * `walkmod set-writer -DconfigFile=myformatter.xml java-formatter` formats like Eclipse
    * `walkmod add override`
    * `walkmod apply`
    * `git diff`
    * `walkmod set-writer javalang:string-writer` does not format, is default.



### Trouble Shooting

* `walkmod <command> --help` shows options.
* `walkmod apply -e` shows errors.
* ? cannot locate mentioned log file.

> No # in path names!
> Does not work with Java 9

### Issues
    * SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    * license-applier and imports-cleaner do not work together
    * no formatting is default writer in 3.0, documentation is wrong
