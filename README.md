# Webapp for codeing test

This is a simple webapp, it is just for test. It uses a embedded database H2.
Once the webapp is started (even if in junit test), the embedded database is reinitiated with 2 test entries.
see import.sql
> insert into transaction(id,amount,type,parent_id) values(1,500,'laptop',null);
> insert into transaction(id,amount,type,parent_id) values(2,50,'accessories',1);

## Start webapp
> mvn jetty:run

8080 is http port by default.

## APIs:
PUT /transactionservice/transaction/$transaction_id
> curl http://localhost:8080/transactionservice/transaction/10 -H "Content-Type: application/json" -X PUT -d '{"amount":5000,"type":"cars"}'
> => {"status":"ok"}
>
> curl http://localhost:8080/transactionservice/transaction/11 -H "Content-Type: application/json" -X PUT -d '{"amount":10000,"type":"shopping","parent_id":10}'
> => {"status":"ok"}

GET /transactionservice/transaction/$transaction_id
> curl http://localhost:8080/transactionservice/transaction/11
> => {"id":11,"amount":10000.0,"type":"shopping","parent_id":10}

GET /transactionservice/types/$type
> curl http://localhost:8080/transactionservice/types/cars
> => [10]

GET /transactionservice/sum/$transaction_id
> curl http://localhost:8080/transactionservice/sum/10
> => {"sum":15000}
>
> curl http://localhost:8080/transactionservice/sum/11
> => {"sum":5000}

## @dev
This test project is using Spring, Spring-Data-JPA, CXF, and H2.
We uses lombok to generate setter,getter in javabeans. The lombok is used just only in compile-time. If you wanna avoid error highlight in IDE, please install lombok in your IDE. See [lombok] for more information.

[lombok]: https://projectlombok.org/

