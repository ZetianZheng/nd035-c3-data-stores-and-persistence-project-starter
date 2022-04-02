# Pre-Knowledge
### VO，BO，PO，DO，DTO:
[一篇文章讲清楚VO，BO，PO，DO，DTO的区别 - 知乎](https://zhuanlan.zhihu.com/p/102389552)
![img.png](img.png)
# Design Entities to represent your data:
You’ll need to decide how to persist your information. To complete this project, you will need to store the following:

Two different kinds of users - Employees and Customers.
Any type of pet, such as cats, dogs, lizards, hedgehogs, toucans, etc. We don't want to discriminate against owners of odd pets!
Schedules that indicate one or more employees will be meeting one or more pets to perform one or more activities on a specific day.
As you consider your design, think about how you want these entities to be stored in your database. 
Be deliberate about whether you are representing data via inheritance or composition and use the tools 
that Spring and Hibernate provide to create the appropriate tables to relate your data. 

Remember that the Data Transfer Objects represent the structure of request and response data, 
but do not have to represent the structure of your persistence model. 
The Data Transfer Objects are represented by the [NAME]DTO.java files in the starter code.

- Employees
- Customers
- pet
# Task 4: Create Tables in your Database
There are a variety of ways to create the tables your program will use. Hibernate can automatically generate them when you launch the application, or you may use a schema.sql to manually define and create the tables. Automatic generation is a simpler, quicker solution and recommended for this project. Just as some of your furry clients would choose to Hibernate, we suggest the same! That being said, you may choose any method you wish.

Once your tables are constructed, review the schema in MySQL workbench or in another tool of your choice. Hibernate’s HQL and Spring Data’s JPQL allow you to work with Java objects, but it’s still useful to look at the SQL schema verify your Entity design produces the type of tables you want.
```sql
create user 'sa'@'localhost' identified by 'sa1234'; -- Create the user--
grant all on critter.* to 'sa'@'localhost'; -- Gives all privileges to that user on new db
```
