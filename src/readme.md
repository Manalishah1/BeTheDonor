
-Testing needed for Controller Layer
-
An integration test with Spring fires up a Spring application context that contains all the beans we need. This includes framework beans that are responsible for listening to certain URLs, serializing and deserializing to and from JSON and translating exceptions to HTTP. These beans will evaluate the annotations that would be ignored by a simple unit test.