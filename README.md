# JPA_HIBERNATE
- JPA: Jakarta Persistence API
- ORM: Object Relational Mapping
- Hibernate:  an implementation of JPA Specification
- JDBC: Java Database Connection
- SQL: Structured Query Language



# Persist
- persist is not an insert instead it adds a new instance of the entity into the context



- cascade means to perform same operation on related entities
- Lazy: retrieve on request
  Eager: retrieve everything
- uni-directional   one way relationship
  bi-diretional  ->> both ways reference each other

many to many
course to student

on student delete, course should not be deleted
hence cascade should not be delete




