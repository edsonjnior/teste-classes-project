# teste-classes-project
Teste Smartercode - Projeto Java Web

Hibernate 4.3 (JPA 2.1), JEE Web 7, Mysql 5, Glassfish 4, Primefaces 7, JSF 2.2, Bootstrap 4.


## Glassfish 4.1.2 (Configurações)

### JDBC Resources
- Jndi name: jdbc/mydbPool
  - Pool name: mydbPool

### JDBC Connection Pool
- Pool name: mydbPool 
  - Resource type: javax.sql.DataSource
  - Additional Properties:
    - databaseName: classes_db
    - serverName: localhost
    - portNumber: 3306
    - user: ****
    - password: ****


## Mysql 5.5 (Configurações)
- databaseName: classes_db
- engine: InnoDB
- database dump: ejb-glassfish4-web/src/main/resources/db_migrations/V1_1__initial_structure.sql

