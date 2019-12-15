# Getting Started
This is a simple solution to crawl web from a given Http url.

# Use cases
* Service will start crawling from a given url.
* It will crawl children links and further i.e. children of children links.
* It will crawl pages until it reaches max link to visit or there is no more links to visit.
* For each page it will create a node containing url, title and children nodes.

# Scope of this solution
* It will only visit absolute urls eg. Http://www.test.com. It will ignore anchor and internal urls. 
* All tracking information (urlToVisit, visitedURLs) are kept in memory but can be easily extended to use any caching (memchache/redis) or Database.
* API Security is not applied 


# High Level Solution Design



# How to run
Gradle is required to build the solution. It is assumed gradle is available. Run the following gradle command
```
gradlew clean build
```
it will build an executable jar at /build/libs folder. Run the jar as follows,
```
java -jar simple-web-crawler-0.0.1-SNAPSHOT
```

Make a HTTP POST request with json body at http://localhost:8090/crawl?url=https://crawltest1.s3-ap-southeast-2.amazonaws.com/a.html&maxlinks=50


# TODO
* Support other kinds of links and page title tags
* Need to add more unit/functional test.




### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)




### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

