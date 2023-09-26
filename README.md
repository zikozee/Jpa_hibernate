# JPA_HIBERNATE
- JPA: Jakarta Persistence API
- ORM: Object Relational Mapping
- Hibernate:  an implementation of JPA Specification
- JDBC: Java Database Connection
- SQL: Structured Query Language

When using ORM we use a concept of CONTEXT we don't work with the database directly

# Lifecycle
- em.persist(); -> Adding an entity in the context
- em.find();  -> Find by PK get from DB and add it to the context if doesn't already exist
  - this not only does a select it ensures a change is persisted also
- em.remove(); -> Marking an entity for removal
- em.merge(); -> Merges an entity from outside/detached entity to the context
- em.refresh(); -> Mirror the context from the database
- em.detach(); -> Taking the entity out of context
- em.getReference();


# Persist
- persist is not an insert instead it adds a new instance of the entity into the context


# Mapping with existing table with no primary key
- use composite primary keys from the columns

# Context
- where hibernates does its work
- Jpa mirrors the context to the database 
- if the context is identical to what is in the database on a find, no query is issued on commit()
- note if entityManager.find(...) is called
- and a field is updated, before commit()
- these change will be persisted 
  - there is nothing like update in hibernate or jpa, rather the commit checks if there are any change
  - if so, takes the change into the DB

- Hence we don't know the number of queries Hibernate will issue up until the end


- cascade means to perform same operation on related entities
- Lazy: retrieve on request
  Eager: retrieve everything
- uni-directional   one way relationship
  bi-diretional  ->> both ways reference each other

many to many
course to student

on student delete, course should not be deleted
hence cascade should not be delete


# find vs reference
- find issues a select
- reference only gets a reference in a lazy way, then when used, it issued the reference

# uses what is in the database in place of the update issued by setting some data


# Composite primary keys <-->
- this is essential when an already existing database is now brought under hibernate management with no unique identifier


# Relationships
## One to One
  -  to change the column name for the reference key we use the joinColumn else it will use <entityName_id> of the non-owning side
  - joinColumn is always on the owning side
  - cascade type should be ALL since _non-owning side belongs to the owning side alone_
  - when you cascade, only one persist is needed as it will trickle down to the related entities
  - same with _**remove, refresh, merge, find, reference and detach**_
  - UniDirectional
    - only the owning side should know about the relationship
    - only the owning side has the @OnetoOne annotation
  - BiDirectional
    - mappedBy = "<the variable name used in the owning side>"
    - both side need to be linked to each other
      - i.e
        - person.setPassport(passport);
        - passport.setPerson(person);
    - you can now query from both side


# FetchType
- EAGER -> this issues a join to call the non-owning side recommended for OneToOne
- LAZY ->  when dealing with collections is advisable to use Lazy
  - as this would postpone the loading 
    - it would only be called/ pulled when the collection or non-owning side is called or used at first
    - i.e only the other fields of the entity(owning-side) would be called 
      - then on the collections usage, the join (another query) is then issued

    
# Optional 
- this is false by default for relationships
- however, if you have a case where the owning side does not necessarily have a non-owning side then set to true
  - e.g User -> Book class
  - if some Users only have book relationship, then this is an ideal case to set to true

# OrphanRemoval (OneToOne and OneToMany)
- The orphanRemoval functionality is intended for entities that are privately "owned" by their parent entity


