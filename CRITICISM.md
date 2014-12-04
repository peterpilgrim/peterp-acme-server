CRITICISM
===========

I submitted my Scala answer in November 2014.

Context
---------

The context I have been writing Scala code commercially for much of 2014 in the GOV.UK project largely.
I worked on the Registered Traveller for a lot of the time, which was a Scala 2.10 and Play Framework application.
This project made particularly use of the DropWizard framework for the Microservice architecture.



This is feedback email from the ACME technical reviewer who examined my submission. I did not get the gig in the end.
I forgot to include the YAML file and in the final ZIP file distribution.

This feedback came to me through an recruitment agent, who must remain nameless, Ms Orange.

The Feedback
--------------


Good points:

    * There are some tests.

    * The tests seem reasonable.

    * There is a README which is accurate to the extent we can check it

Bad points:

    * It reads as if it’s written by a Java developer who has learned some Scala syntax in general, and specifically:

        * using com.google.common.base.Optional

        * all of the loops are for loops with indexes

    * All the code is synchronous, even for network calls.

        * We can’t run the solution because there is a configuration file missing.

        * We can’t run the tests because there is a test data file missing (not the same file as above).

        * We think he has not implemented the specified query format (we’d know if we could run it and try)


The Rebuttal
-------------

I did not agree with some of the bad points and it made me question who the person at the end of the process was reviewing the code.
Here is my email back to the client, and it is probably that they never saw my rebuttal.

Ms Orange.

Thanks for letting me know and passing me ACME feedback. (This is in no way criticism of yourself, Ms Orange.)

I have some responses for the technical reviewer(s) at ACME.


  * It reads as if it’s written by a Java developer who has learned some Scala syntax in general, and specifically:

    * using com.google.common.base.Optional

        * (PP this is because I stupidly used the DropWizard framework, which was written in Java. Although there is a Scala module, some of the artefacts from DropWizard Java modules, e.g. JAX-RS seep through to the Scala side)

    * all of the loops are for loops with indexes

        * (PP right, you understand Scala for expressions are not loops; but there are LIST COMPREHENSIONS. So you can see my code uses framework using Json4s (https://github.com/json4s/json4 'JSON for Scala') and for .. yield statement, which are List Comprehensions that json4s API support. Also I do know that List Comprehension can be turned to map expressions. We are debating developing style and readability in Scala. Maybe you should written in the Task notes that points are awarded for writing the Scala solution in the function programming style rather than in the Object-Oriented Programming style, despite Scala being a hybrid and marriage of FP and OOP alternative programming language that runs on the JVM)

  * All the code is synchronous, even for network calls.

        * (PP I accept that.  Maybe you should have say in the test task that points are awarded for considering Scalability and Performance in the solution)

    * We can’t run the solution because there is a configuration file missing.

        * (PP I accept, I screwed up the packaging of the ZIP file. This is my error, 100%)

    * We can’t run the tests because there is a test data file missing (not the same file as above).

        * (PP I accept, I missing the test JSON file)

    * We think he has not implemented the specified query format (we’d know if we could run it and try)

        * (PP I accept, I re-examined that URL and see now that it is not a URI with a search parameter (?q). It is just a portion of the path. So I am unsure now that DropWizard and therefore the underlying JAX-RS implementation Jersey could have helped here. Of the top of my head, I would have to been a Java Servlet that just implements the GET portion of the request. If the framework was the problem, I could have chosen another Scala based framework. No matter. It is what it is)


I think on the balance my decision to use Code Hale's DropWizard was the reason for the test failure, because it is written in Java and not in Scala. Hence the Guava 'Optional' versus 'Option' disagreement that we have. DropWizard has some great benefits like health check, metrics, Jetty and JAX-RS, fast Jackson JSON serialisation and it is the framework that I came across at the Home Office GOV.UK. There is Scala Pimp My Library that I used at the Home Office. I worked on the Registered Traveller project, at GOV.UK which incidentally is 100% written in Scala with the web front-end in Play Framework. Therefore your comment, "Java developer who has learned some Scala syntax in general" does invalid my experience, because you did not have all the information to hand in your analysis. You are entitled to your opinion, of course, as I am to mind.


The End
--------

At the end of day, I have moved on.


+PP+ December 2014
