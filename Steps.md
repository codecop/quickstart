# Steps

## Background Theory (1h)

* "walkmod - how it works.pdf"

* Raquel Pau - How to Maintain and Fix code conventions with SonarQube and Walkmod (JBCNConf 2016)

  * https://www.slideshare.net/walkmod/barcelonajug2016-walkmod-how-to-run-and-design-code-transformations
  * https://www.youtube.com/watch?v=-A3YccWOfzY

## Environment

* Goal: Have Walkmod ready to use.

* Installation (15')

  * download from http://walkmod.com/
  * unzip
  * set PATH to its bin: `set PATH=%PATH%;c:\walkmod-3.0.0\bin`
  * set proxy if needed
    `set WALKMOD_OPTS=-Dhttp.proxyHost=10.0.0.100 -Dhttp.proxyPort=8800` plus `https`
    or put into `JAVA_TOOL_OPTIONS`

* Verify Installation

  * `walkmod --version`
  * `walkmod init -f xml` and see `walkmod.xml`
  * done automatically when missing on any command

## Basic Usage

### Quickstart Tutorial

* https://tomassetti.me/walkmod-automatically-refactor-code-to-apply-code-conventions/
* https://github.com/walkmod/quickstart

* What is there?

  * Goal: Get an idea what it can do.
  * `walkmod plugins`
  * chose imports-cleaner

* First Usage (15')

  * Goal: Use a basic plugin with no arguments.
  * http://walkmod.com/docs#usage
  * `walkmod add imports-cleaner`
  * `walkmod transformations`
  * `walkmod apply`
  * `git diff`

* Includes, Excludes (15')

  * Goal: Manage to exclude certain sources from transformation.
  * provide using `walkmod add...`
  * provide using `-i` and `-x` in apply.
  * `walkmod check`
  * file based from `src` folder
  * wildcard: `*.java`, `a/b/*`, `/a/b/**` - TODO read code for all options

* Chains (15')

  * Goal: Use Chains to run two transformations on two folders.
  * Chains separate transformations of different source folders
  * `walkmod add override`
  * see example chains in https://github.com/walkmod/jug

* Break (15')

* More Transformations (15')

  * Goal: Use transformations which need parameters, figure out usage.
  * `walkmod add license-applier`
  * `walkmod apply`
  * Missing license file at [src/main/walkmod/license-applier/license.txt]
  * `walkmod inspect license-applier`
  * configure: `<param name="licenseFile">src/main/license-header.txt</param>`
  * or `walkmod add -DlicenseFile=... license-applier`
  * has more options like add, replace, etc.
  * TODO second parameter `action` - what does it do?
  * TODO license-applier and imports-cleaner do not work together?

* Formatter (15')

  * Goal: Use Eclipse formatting and provide your own formatter.
  * `walkmod set-writer -DconfigFile=myformatter.xml java-formatter` formats like Eclipse
  * `walkmod apply`
  * `git diff`
  * `walkmod set-writer javalang:string-writer` does not format, is default.
  * `<walker>` with `<param name="onlyWriteChanges">false</param>` updates unchanged files, too.
    see https://github.com/walkmod/walkmod-java-formatter-plugin/issues/3

* What else is there? Other Plugins (15')

  * Goal: Check out some other plugins.
  * `refactor`
  * `name` loads `org.walkmod:walkmod-" + name + "-plugin:" + name`
  * `pluginId:beanId` loads `org.walkmod:walkmod-" + pluginId + "-plugin:" + beanId`
  * Plugins to fix PMD and Sonar issues. E.g. https://blog.walkmod.com/how-to-fix-pmd-violations-with-walkmod-6aedbc65773c

### Custom (Groovy) Scripts

* Transformation type `script` = `walkmod:commons:scripting`.
* Scripts to manipulate the AST are written in Groovy (or any other Java scripting language).

* Sample Scripts (15')

  * Goal: Run a given script.
  * https://www.voxxed.com/2014/11/walkmod-tutorial-apply-code-conventions-automatically/
  * given `fields.groovy` makes all fields private.
  * `walkmod add -m script -Dlanguage=groovy -Dlocation=src\main\walkmod\scripts\fields.groovy`
  * also works with `-Dcontent` and paste script directly
  * `walkmod apply`
  * `git diff`

* There is support for Groovy in Java IDEs and it helps for large Groovy scripts. (15')

  * Goal: Prepare Eclipse to get Groovy support.
  * IDEA works out of the box (just need to add folder as Groovy source folder)
  * Check version of Eclipse.
  * Groovy plugin "GrEclipse", look for suitable release or snapshot on https://github.com/groovy/groovy-eclipse/wiki#releases
  * How to Install see https://github.com/groovy/groovy-eclipse/wiki#how-to-install
  * Add core dependencies for code completion: `org.walkmod:walkmod-core:3.0.4, org.walkmod:javalang:4.8.8`
  * Add imports to Groovy script.
  * See code completion in IDE.

* Create your own script (90')

  * Goal: Create a basic transformation using a Groovy script.
  * What can we do with `org.walkmod.javalang.ast.CompilationUnit node`.
    Plus `$WALKMOD_HOME/config/query.alias.groovy` defines aliases `getFields` and `getMethods`
  * `org.walkmod.walkers.VisitorContext context` is a map, with result node(s), messages and data.
    e.g. `context.addResultNode(ASTManager.parse(javaFile.toString(), true))`
  * `org.walkmod.query.QueryEngine query` is for resolving values (in templates).

* Example Integrating with JavaPoet (optional)

  * https://blog.walkmod.com/how-to-maintain-java-architectures-with-javapoet-and-walkmod-45611b1bc627
  * https://github.com/walkmod/helloworld-javapoet (empty)
  * `walkmod add -m script -Dlanguage=groovy -Dlocation=src\main\walkmod\scripts\serializable.groovy`
  * JavaPoet is part of Walkmod.
  * Add to test dependencies to create scripts: `com.squareup:javapoet:1.10.0`

### Code Generation using Templates (30')

* Goal: See what is possible (Demo).
* Transformation type `template` = `walkmod:commons:template`.
* `<transformation type="walkmod:commons:template" isMergeable="true">` adds code to each class
* See https://github.com/walkmod/jug

## Your Own Transformations

### Basic Plugins

* Refactoring Plugin

  * read documentation https://github.com/walkmod/walkmod-refactor-plugin
  * apply, read source
  * tbd - maybe this is not working

### PMD and SonarCube Plugins

* Walkmod PMD with Maven

  * Goal: Know how to use the Walkmod Maven and PMD plugin.
  * https://blog.walkmod.com/how-to-fix-pmd-violations-with-walkmod-6aedbc65773c
  * https://github.com/rpau/voxxed-age-checker
  * Walkmod Maven Plugin help page

* Walkmod SonarCube

  * Goal: Know how to use it.
  * apply Walkmod Maven Plugin SonarCube fixes

### Writing Plugins/Visitors

* All plugins are `VoidVisitor`s or similar.
* follow "How to Maintain and Fix code conventions with SonarQube and Walkmod"
* Proposed Process

  * run only one refactoring rule at a time to do single purpose commits.
  * do a pre-commit code review to check all the changes are approved by the developer.
  * Commit the change, then move on to run the next refactoring rule.

## Trouble Shooting

* `walkmod <command> --help` shows options.
* `walkmod apply -e` shows errors.
* ? cannot locate mentioned log file.
* use `println` in Groovy scripts.
* `... --offline` for `apply` to speed up run (after first run)

> No # in path names!

> Does not work with Java 9

### Issues

* Proxy setting is always an issue in enterprises.
* `SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"`
  * need a SLF4J to Log4J bridge
  * where is it? Somewhere in Maven
* license-applier and imports-cleaner do not work together
* changes on walkmod.xml drop comments
* even there is a YML configuration, it creates a new XML file.
* JavaPoet example destroys formatting
* JavaPoet and other scripting does not work together
* `VisitorMessagesWriter` did not write on `walkmod check`
* `add exclude` creates an `<include>`
* `override` plugin performs `mvn install` - why?
* no offline mode?

### Bugs

* `LocationImpl` needs toString for error cases
  `Error visiting a Java source file - org.walkmod.util.location.LocationImpl@103bf3c at org.walkmod.javalang.walkers.DefaultJavaWalker.visit(DefaultJavaWalker.java:505)`
* Templates fail with `Error visiting a Java source file` when there are comments in it.
* URL of `rawclasspath` plugin points wrongly to Maven plugin.
* `import-cleaner` plugin crashes with NPEx if there is an extra ; after an unused import.
* `refactor` does not recognise usages of the old class in the same package.
  I cannot get both refactor to perform any file changes. Sigh. 

### Ivy is not common in enterprises

* Downloads everything again (in fact an Ivy issue, allow to provide `ivysettings.xml` - in config folder).
* http://ant.apache.org/ivy/history/latest-milestone/tutorial/defaultconf.html
* https://stackoverflow.com/questions/8617963/ivysettings-xml-add-local-maven-path
* just changing it does not work...
* Corporate Nexus as 1st in Ivy settings - issue!
* Dependency load fails (Ivy mix problem) - delete ivy cache

### Missing documentation

* no tutorial for templates - found jug project later
* no formatting is default writer in 3.0, documentation is wrong
* Did not find any help for script tag.
* I am missing documentation a lot ;-)
