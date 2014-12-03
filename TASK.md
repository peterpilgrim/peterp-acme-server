Scala developer test
======================


Here is the ACME Scala development task that you must complete on your time. The original task document was a PDF and
I have transcribed here to Markdown format. I changed the names of the business and altered some context, but the majority
of the text is verbatim.


Source data
------------


A GET HTTP call to the product API URL (see additional information below) produces a response with content type “application/json”.
The response contains a JSON object, whose property “data” contains a JSON array of product objects.


(EXAMINE CAPTURED JSON data file )


As seen from the example, the product object contains the product name in four languages,
the product price, where effective price = gross / divisor (i.e. in the first product in the example we have 51500/ 100 = 515 GBP),
and some other product data in which we are not interested for this exercise.



Problem
---------


Build a simple web application that responds to the url http://localhost:port/q=query-string returning a list of products
whose descriptions in English match the query string. port is a value of your choice, and query-string is the query string
that will be supplied by the user of your API. The content type of the response is “application/json” and the structure of the response JSON is given below.


Additional information
-----------------------------

 * The choice of web platform/server is entirely up to the candidate, but the main language must be Scala.
 * A product matches the query when its name in English contains the exact query string as one word.
 I.e. "Stripe-trimmed open-knit cotton-blend sweater" matches Stripe, trimmed, sweater.
 It does not match "cotton-blend" or "blend sweat".
 For the purposes of this the delimiters are space bar and hyphen - the characters contained in the string " -"


Resources
--------------


Product API URL [http://lad-api.acme-retail-shopping-magazine.com/product/summaries?business=NAP&categoryIds=2&country=GB]


Sample response from your app

Sample response from "http://localhost:port/q=query-string" from your finished app

```
[
 { “product-id”: “428236”, “name”: “Stripe-trimmed open-knit cotton-blend sweater”,
“price”: 515 },
 { “product-id”: “444634”, “name”: "The Perfect cotton-chambray shirt", “price”:
210}
]
```

Note: The above contains only two products, based on the sample data given below - in reality the
API will give you several thousand products (but no more that ten thousand).

