
* NPCS

** Building the docker image

First, build the docker image

#+BEGIN_SRC
docker build -t npcs:v1 .
#+END_SRC


** Running individual commands

The parameters =rdfstar= and =wikidata= are the identifiers for the
reification schemes. The supported reification schemes are:

1. =SPARQL_Star= RDF-star reification.
2. =Wikidatareal= Wikidata refication (to be used to query the official Wikidata).
3. =Wikidata= a variant for the Wikidata reification scheme.
4. =Namedgraph= for using named graphs (not working yet)
5. =Standard= for using standard reification
6. =SPARQL=for non reification

You can rewrite a query using the RDF-star reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 SPARQL_Star queries\watdivqueries\Basic\C1\00.sparql
#+END_SRC

You can rewrite a query using the wikidata reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Wikidata queries\watdivqueries\Basic\C1\00.sparql
#+END_SRC

You can rewrite a query using the namedgraph reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Namedgraph queries\watdivqueries\Basic\C1\00.sparql
#+END_SRC

You can rewrite a query using the Standard reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Standard queries\watdivqueries\Basic\C1\00.sparql
#+END_SRC

You can rewrite a query using the Wikidata real reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Wikidatareal queries\wikidataqueries\Basic\multiple\01.sparql
#+END_SRC



You can execute the rewritten query on Wikidata:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Wikidatareal queries\wikidataqueries\Basic\multiple\01.sparql https://query.wikidata.org/sparql 1
#+END_SRC

You can execute the other rewritten queries by loading the Watdiv data in GraphDB or Stardog

You can rewrite a query using the RDF-star reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 SPARQL_Star queries\watdivqueries\Basic\C1\00.sparql http://localhost:7200/repositories/watdivrdfstargraphdb 1
#+END_SRC

You can rewrite a query using the wikidata reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Wikidata queries\watdivqueries\Basic\C1\00.sparql http://localhost:5820/watdivwikidatagraphdb/query 1
#+END_SRC

You can rewrite a query using the namedgraph reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Namedgraph queries\watdivqueries\Basic\C1\00.sparql http://localhost:7200/repositories/watdivnamedgraphgraphdb 1
#+END_SRC

You can rewrite a query using the Standard reification scheme:

#+BEGIN_SRC
docker run --name npcs_container npcs:v1 Standard queries\watdivqueries\Basic\C1\00.sparql  http://localhost:7200/repositories/watdivstandardgraphdb 1
#+END_SRC




