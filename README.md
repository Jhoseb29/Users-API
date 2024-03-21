## Spring Boot Example with Spring Data MongoDB

#### By Fernando Pinto Villarroel

The base of this project was originally developed by Joann Vasquez Ramos in the Spike concerning the
creation of a Web API using Spring Boot and Spring Data JPA and uploaded
here: https://github.com/BastianSV17/apiexample and described here: [Spike-04](https://gitlab.com/jala-university1/cohort-1/software-development-3-es/secci-n-b/capstone/sabrozitos/users-api/-/issues/17) 

In order to explore and explain the Spring Environment (Spring Framework, Spring Data, and so on),
I modified the existing project to be able to keep working although there is a change
in the infrastructure: I've migrated the MySQL Database to MongoDB to validate the viability
of using Spring for a project already knowing that, at some point, we will need to change the
database from SQL to NoSQL.

One of the most important changes made to the code was replacing Spring Data JPA with Spring Data
MongoDB.

The process of migration and its result were successful, we can use Spring for a project where we
know beforehand
that we will need to change the infrastructure.

Data Migration process was done with the **ELT** (Extract, Load, Transform) steps described in
the Spike referring to the Data Migration Process.

It'll be an ELT process and not an ETL process, because we **extract** the
SQL Data as csv files, we **load** it directly into MongoDB, and Mongo **transforms** it to a JSON
format.
It would be an ETL if we had the responsibility to **extract** and **transform** the .CSV file to a
JSON, and then
we **loaded** it to Mongo, but Mongo allows the easier approach of the ELT just by importing the
.CSV.

Note: We're using docker containers for our databases.

1. We must create a data backup of our existing SQL DB. This is just made to ensure that if we do
   something
   wrong in the next steps, we don't lose all of our information. Although we won't use this file,
   it's crucial to ensure that data loss won't happen.
   From the Linux terminal, we execute:

```
docker exec ff8fcfaf6f48 mysqldump -u root -psd5 sd3 > /home/university/Downloads/backup.sql
```

2. After that, we can start to retrieve the data of each table in the following way. Since we just
   have one table, this won't be complicated. The data files format will be **.csv**
   From the Linux terminal, we execute:

```
docker exec ff8fcfaf6f48 mysql -u root -psd5 -e "USE sd3; SELECT * FROM users" > /home/university/Downloads/datos.csv
```

3. Once we have our data files from each table of MySQL DB, we must create a Mongo container in the
   following way:

```
docker run -d --name mongodb -p 27017:27017 mongo
```

4. From here, it'll be much easier if we work with MongoDB Compass GUI, which will allow us to
   simplify the process of Data Migration. First, we enter the corresponding db created in the last
   step, then we create the corresponding DB and collections. Finally, we choose the option of
   Importing Data, so we can import the data for each collection.

![MongoDBConnectionPage.png](images%2FMongoDBConnectionPage.png)
![MongoDBUsersCollection.png](images%2FMongoDBUsersCollection.png)

For the ID option, we can choose the ObjectID option, but, since we are using a UUID, Mongo won't
be able ot use it as an ObjectId. Here we can take two approaches: either we delete the existing
IDs,
and we let the creation of new IDs to Mongo wo they are correct ObjectIDs, or we can just let the
original id as a string and Mongo will still create a new ObjectID. I took the second approach.
![DataImportingMongoDB.png](images%2FDataImportingMongoDB.png)
![ImportCompletedMongo.png](images%2FImportCompletedMongo.png)

Finally, we prove the API is correctly working using POSTMAN:
![APIWorkingMongo.png](images%2FAPIWorkingMongo.png)
![PostmanWorkingMongo.png](images%2FPostmanWorkingMongo.png)

Useful Resources:

- Bartley, K. (2023, January 2). ETL vs ELT: Key Differences, Comparisons, & Use Cases.
  Rivery. https://rivery.io/blog/etl-vs-elt/
- Clases, V. [@clasesv1729]. (2020, November 12). Importar tabla mysql a mongodb y exportar e
  importar json de mongodb. Youtube. https://www.youtube.com/watch?v=TkEXx_TWcr4

