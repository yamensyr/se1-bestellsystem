<!-- 
    README.md for project: *se1-bestellsystem*
 -->


# Project: *se1-bestellsystem*

The project implements a simple order processing system and is part
of the *Software Engineering 1* class.

Content:

 1. [Project Setup](#1-project-setup)

 2. [Building the Application](#2-building-the-application)

 3. [Running the Application](#3-running-the-application)

 4. [Running JUnit Tests](#4-running-junit-tests)

 5. [Generating Javadoc](#5-generating-javadoc)

 6. [Packaging the Application](#6-packaging-the-application)

 7. [Running the Packaged Application](#7-running-the-packaged-application)

 8. [Project Structure](#8-project-structure)


&nbsp;
## 1. Project Setup

Clone project `se1.bestellsystem` from the repository:

```sh
git clone https://github.com/sgra64/se1.bestellsystem.git
```

Change (cd) into new project repository and create sub-directory for `branches`:

```sh
cd se1.bestellsystem                # change into new project directory
mkdir branches                      # create new sub-directory for branches
```

Change (cd) into `branches` and clone branch `libs` from prior `se1.play` repository:

```sh
cd branches                         # cd (change directory) into branches directory
git clone -b libs --single-branch https://github.com/sgra64/se1.play.git libs
cd ..                               # cd back to project directory
```

Source the new project in project directory:

```sh
source .env/setenv.sh
```

```sh
ls -la                              # show content of project directory
```

Project directory after sourcing. `libs` has been created as a
*symbolic link* to `branches/libs/libs`.

```
drwxr-xr-x 1 svgr2 Kein     0 May  4 19:00 .
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:54 ..
-rw-r--r-- 1 svgr2 Kein  2732 May  4 18:59 .classpath
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:59 .env
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:42 .git
-rw-r--r-- 1 svgr2 Kein  1461 May  4 18:59 .gitignore
-rw-r--r-- 1 svgr2 Kein   663 May  4 19:00 .project
drwxr-xr-x 1 svgr2 Kein     0 May  4 19:07 .vscode
drwxr-xr-x 1 svgr2 Kein     0 May  4 19:00 bin
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:59 branches
lrwxrwxrwx 1 svgr2 Kein    18 May  4 18:59 libs -> branches/libs/libs   <-- symbolic link
-rw-r--r-- 1 svgr2 Kein 14198 May  4 19:01 README.md
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:22 resources
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:17 src
drwxr-xr-x 1 svgr2 Kein     0 May  4 18:26 tests
```

Show that `CLASSPATH` variable is set:

```sh
echo $CLASSPATH | tr "[;:]" "\n"
```

Mind that paths to libraries follow the `libs` symbolic link.

```
bin/classes
bin/test-classes
bin/resources
branches/libs/libs/jackson/jackson-annotations-2.13.0.jar
branches/libs/libs/jackson/jackson-core-2.13.0.jar
branches/libs/libs/jackson/jackson-databind-2.13.0.jar
branches/libs/libs/jacoco/jacocoagent.jar
branches/libs/libs/jacoco/jacococli.jar
branches/libs/libs/junit/apiguardian-api-1.1.2.jar
branches/libs/libs/junit/junit-jupiter-api-5.9.3.jar
branches/libs/libs/junit/junit-platform-commons-1.9.3.jar
branches/libs/libs/junit/opentest4j-1.2.0.jar
done.
```


&nbsp;
## 2. Building the Application

The [*Build-Process*](https://www.techbin.com/searchsoftwarequality/definition/build)
consists of operations such as:

 - compile source code

 - compile tests

 - build javadocs

 - package the application to final '.jar' file

Command `show` prints operations that can be used for the *Build-Process*:

```sh
show
show --all
```

```
source | project:
  source .env/setenv.sh

classpath | cp:
  echo $CLASSPATH | tr "[;:]" "\n"

compile:
  javac $(find src -name '*.java') -d bin/classes; \
  copy resources bin/resources

compile-tests:
  javac $(find tests -name '*.java') -d bin/test-classes; \
  copy resources bin/resources

resources:
  copy resources bin/resources

run:
  java application.Application

run-tests:
  java -jar branches/libs/libs/junit-platform-console-standalone-1.9.2.jar \
    $(eval echo $JUNIT_OPTIONS) --scan-class-path

javadoc:
  javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS) \
    $(cd src; find . -type f | xargs dirname | uniq | cut -c 3-)

clean:
  rm -rf bin logs docs

wipe:
```

Execute build steps with the `build` or `mk` (make) commands:

```sh
mk compile                        # compile source code
mk compile-tests                  # compile test code

mk clean compile compile-tests    # execute all commands in order
```

The last command is called a *clean build* since it clears the `bin` directory
(removes all content) before re-compiling the source code.

The result is in the `bin` directory:

```sh
find bin
```

Output

```
bin
bin/application-1.0.0-SNAPSHOT.jar
bin/classes
bin/classes/application
bin/classes/application/Application.class
bin/classes/application/package-info.class
bin/classes/application/package_info.class
bin/classes/application/Runtime$1.class
bin/classes/application/Runtime$SupplierWithExceptions.class
bin/classes/application/Runtime.class
bin/classes/module-info.class
bin/resources
bin/resources/application.properties
bin/resources/logging.properties
bin/test-classes
bin/test-classes/application
bin/test-classes/application/Application_0_always_pass_Tests.class
```


&nbsp;
## 3. Running the Application

After building the application, it can be run using the `run` command
and passing a number `n` to factorize.

```sh
mk run a b c
```

Output:

```
java application.Application a b c
Hello, Application
arg: a
arg: b
arg: c
done.
```


&nbsp;
## 4. Running JUnit Tests

[JUnit](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
is a widely used framework for Unit-testing.

JUnit is available in the project through:

- libraries in [libs/junit](), e.g.
  [junit-jupiter-api-5.9.3.jar](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)

- and the Test-Runner that executes JUnit tests
  [junit-platform-console-standalone-1.9.2.jar](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone)

Run JUnit-Tests in the IDE and in the terminal with:

```sh
mk compile-tests run-tests
```

Output:

```
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 142 ms
[         2 tests successful      ]   <-- 2 tests successful
[         0 tests failed          ]   <-- 0 tests failed
done.
```

Run JUnit-Tests also in your IDE.


&nbsp;
## 5. Generating Javadoc

Build the javadoc for the project. Customize your name as author in
[package-info.java](https://gitlab.bht-berlin.de/sgraupner/setup.se2/-/blob/main/src/main/application/package-info.java?ref_type=heads).

```sh
mk javadoc
```

Output:

```
Loading source files for package application...
Constructing Javadoc information...
Creating destination directory: "docs\"
Building index for all the packages and classes...
Standard Doclet version 21+35-LTS-2513
Building tree for all the packages and classes...
Generating docs\se1_play\application\Application.html...
Generating docs\se1_play\application\package-summary.html...
Generating docs\se1_play\application\package-tree.html...
Generating docs\se1_play\module-summary.html...
Generating docs\overview-tree.html...
Building index for all classes...
Generating docs\allclasses-index.html...
Generating docs\allpackages-index.html...
Generating docs\index-all.html...
Generating docs\search.html...
Generating docs\index.html...
Generating docs\help-doc.html...
done.
```

Open `docs/index.html` in a browser.


&nbsp;
## 6. Packaging the Application

*Packaging* is part of the *Build-Process* in which a `.jar` file (jar: Java archive)
is created that contains all compiled classes and a
[MANIFEST.MF](https://gitlab.bht-berlin.de/sgraupner/setup.se2/-/blob/main/src/resources/META-INF/MANIFEST.MF?ref_type=heads) - file
that describes the class to execute (Main-Class: application.Application).

```sh
mk jar
```
or:
```sh
mk package
```

packages class files and creates the resulting `application-1.0.0-SNAPSHOT.jar`
in the `bin` directory.

```sh
ls -la bin
```

Output:

```
total 16
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:55 .
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:54 ..
-rw-r--r-- 1 svgr2 Kein 5397 Apr 14 21:55 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:50 classes
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:26 resources
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:50 test-classes
```


&nbsp;
## 7. Running the Packaged Application

Test the jar-file with:

```sh
mk run-jar n=100 n=1000
```
or execute directly by java:
```sh
java -jar bin/application-1.0.0-SNAPSHOT.jar n=100 n=1000
```

Output:

```
java -jar bin/application-1.0.0-SNAPSHOT.jar n=100 n=1000
Hello, Application
arg: n=100
arg: n=1000
done.
```

The packaged .jar file can now be distributed.


&nbsp;
## 8. Project Structure

The structure of project
[se1.bestellsystem](https://github.com/sgra64/se1.bestellsystem)
is:

```sh
--<se1.bestellsystem>:                  # project directory
 |
 +-- README.md                          # project markup file (this file)
 |
 | # directory with files to source the project
 +--<.env>
 |   +-- setenv.sh, readme.txt, init.classpath, init.project
 |
 | # VSCode IDE project configuration
 +--<.vscode>
 |   +-- settings.json                  # project-specific VSCode settings
 |   +-- launch.json                    # Java/Debug launch configurtions
 |   +-- launch_terminal.sh             # terminal launch configurtions
 |
 +--<.git>                              # local git repository
 |   +-- config                         # local git configuration file
 |   +-- HEAD                           # HEAD pointer
 |   +--<objects>                       # local git object store
 +-- .gitignore                         # file with patterns to ignore by git
 |
 +-- lib --> branches/libs/libs         # symbolic link to libs-directory in libs-branch
 |
 +--<branches>          # directory to hold branches
 |   +-<libs>           # 'libs' branch checked from se1.play project
 |       +-<libs>       # directory within 'libs'-branch
 |          +--<junit>                         # JUnit .jar files
 |          |   +-- apiguardian-api-1.1.2.jar, junit-platform-commons-1.9.3.jar,
 |          |   +-- junit-jupiter-api-5.9.3.jar, opentest4j-1.2.0.jar
 |          +--<jacoco>                        # Code coverage .jar files
 |          |   +-- jacocoagent.jar  jacococli.jar
 |          +--<jackson>                       # JSON library for Java
 |          |   +-- jackson-annotations-2.13.0.jar, jackson-databind-2.13.0.jar,
 |          |       jackson-core-2.13.0.jar
 |          +-- junit-platform-console-standalone-1.9.2.jar    # JUnit runtime
 |
 | # source code:
 +--<src>                       # Java source code
 |   +-- module-info.java           # module defintion file
 |   +--<application>               # Java package 'application'
 |       +-- package-info.java      # package defintion (javadoc)
 |       +-- Application.java       # Runnable application program to start
 |       +-- Runtime.java           # main() entry point that creates Runnable instances
 |
 +--<resources>                 # none-Java source code, mainly configuration
 |   +-- application.properties     # properties file for the application
 |   +-- logging.properties         # logging properties
 |   +--<META-INF>                  # jar-packaging information
 |       +-- MANIFEST.MF            # jar-manifest file with Main-Class:
 |
 +--<tests>                     # Unit-test source code separated from src/main
 |   +--<application>               # mirrored package structure
 |       +-- Application_0_always_pass_Tests.java   # initial JUnit-test
 |
 | # compiled classes, generated artefacts:
 +--<bin>
 |   +-- application-1.0.0-SNAPSHOT.jar # executable .jar file (main artefact)
 |   +--<classes>                       # compiled Java classes (.class files)
 |   |   +-- module-info.class          # compiled module-info class
 |   |   +--<application>               # compiled 'application' package
 |   |       +-- package-info.class
 |   |       +-- Application.class
 |   |       +-- Runtime.class, RuntimeSupplierWithExceptions.class
 |   |
 |   +--<resources>                     # copied resource files
 |   |   +-- application.properties, logging.properties
 |   |
 |   +--<test-classes>                  # compiled test classes
 |       +--<application>
 |           +-- Application_0_always_pass_Tests.class
 |
```
