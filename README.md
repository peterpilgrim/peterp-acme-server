README
======================


This is my answer to the Scala developer test for ACME Peabody


This code was written against Scala 2.11 and uses Codehale's DropWizard as a MicroService server

You will need a SBT 0.13 or better

To clean the build, execute please:

```
    $ sbt clean
```

To compile, execute:

```
    $ sbt compile
```


To run the unit tests, execute:
```
    $ sbt test
```

To run the standalone program, execute:
```
    $ sbt "run server config.yml"
```

Or on the UNIX / Mac OS X system launch the shell program

```
    $ ./run.sh
```


Afterwards, the server will launch on port 8080. Hit [Control-C], if you want to halt the server for any reason.

The following link should make a query to the server and then invoke a query on the ACME Shopping Hipster service:

- Search for striped [http://localhost:8080/?q=Pleated](http://localhost:8080/?q=Pleated)

- Search for striped and satin [http://localhost:8080/?q=Pleated%20Cotton](http://localhost:8080/?q=Pleated%20Cotton)

The output will be JSON.



Peter Pilgrim

Saturday, 28th November 2014

[http://www.xenonique.co.uk/blog/](http://www.xenonique.co.uk/blog/)


*Fin*
