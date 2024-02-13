
* NPCS Webservices

** Building the docker image

First, build the docker image

#+BEGIN_SRC
docker build -t npcs_web:v1 .
#+END_SRC

** Running docker image

#+BEGIN_SRC
docker run -d -p 8080:8080 -t npcs_web:v1
#+END_SRC

