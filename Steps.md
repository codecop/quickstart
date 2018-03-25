# Steps

* `walkmod --version`
* `walkmod init -f xml` and see `walkmod.xml`
* done automatically when missing on any command

## Basic Usage 

* Quickstart Tutorial (together)

    * https://tomassetti.me/walkmod-automatically-refactor-code-to-apply-code-conventions/
    * http://walkmod.com/docs#usage
    * https://github.com/walkmod/quickstart
    * `walkmod add imports-cleaner`
    * `walkmod transformations`
    * `walkmod apply`
    * `git diff`

* Includes, Excludes

    * provide using `walkmod`
    * provide using `-i` and `-x` in apply. 
    * `walkmod check`

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

### Code Generation using Templates

### Custom (Groovy) Scripts

* Scripts to manipulate the AST are written in Groovy (or any other Java scripting language).

    * https://www.voxxed.com/2014/11/walkmod-tutorial-apply-code-conventions-automatically/
    * `fields.groovy` makes all fields private.
    * `walkmod add -m script -Dlanguage=groovy -Dlocation=src\main\walkmod\scripts\fields.groovy`
    * also works with `-Dcontent` and script directly
    * `walkmod apply`
    * `git diff`
    
* There is support for Groovy in Java IDEs and it helps for large Groovy scripts.    
    
    * Check version of Eclipse.
    * Groovy plugin "GrEclipse", look for suitable release or snapshot on https://github.com/groovy/groovy-eclipse/wiki#releases
    * How to Install see https://github.com/groovy/groovy-eclipse/wiki#how-to-install
    * Add core dependencies for code completion: `org.walkmod:walkmod-core:3.0.4, org.walkmod:javalang:4.8.8`

* Integrating with JavaPoet

    * https://blog.walkmod.com/how-to-maintain-java-architectures-with-javapoet-and-walkmod-45611b1bc627
    * https://github.com/walkmod/helloworld-javapoet (empty)
    * `walkmod add -m script -Dlanguage=groovy -Dlocation=src\main\walkmod\scripts\serializable.groovy`
    * JavaPoet is part of Walkmod.
    * Add to test dependencies to create scripts: `com.squareup:javapoet:1.10.0`

## PMD and Sonar Plugins

* https://blog.walkmod.com/how-to-fix-pmd-violations-with-walkmod-6aedbc65773c
* https://github.com/rpau/voxxed-age-checker

## Trouble Shooting

* `walkmod <command> --help` shows options.
* `walkmod apply -e` shows errors.
* ? cannot locate mentioned log file.
* use `println` in Groovy scripts.

> No # in path names!

> Does not work with Java 9

### Issues

    * `SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"`
    * license-applier and imports-cleaner do not work together
    * no formatting is default writer in 3.0, documentation is wrong
    * changes on walkmod.xml drop comments
    * no tutorial for templating
    * JavaPoet example destroys formatting
    * JavaPoet and other scripting does not work together
