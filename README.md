# Product Signup

A simple product signup experience that utilizes Spring Boot REST APIs with MySQL as the datastore.

## Getting Started

1. Install and run MySQL Server and create an environment similar to the one found in [application.properties](https://github.com/azkevin/ProductSignup/blob/master/src/main/resources/application.properties)

2. Download and run the latest [release](https://github.com/azkevin/ProductSignup/releases). Alternatively you can download this project and run it on STS as a Spring Boot app.

3. Run the REST APIs below on [localhost:8080](http://localhost:8080/).

## REST APIs

### Create Transaction
Creates a new transaction by calling "Add User" then "Add Payment"

* **URL**

  /app/createTransaction

* **Method:**

  `POST`
  
* **Content-Type:**
  `application/json; charset=UTF-8`
  
* **Required Params:**

   `email=[string]`
   
   `name=[string]`
   
   `address=[string]`

   `ccn=[string]`
   
   `expiry=[string]`
   
   `cvv=[string]`
   
   `billingAddress=[string]`

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** `{ ok : "successful transaction" }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "client sent invalid data" }`

* **Sample Call:**

  ```
  {
    "email": "someemail@someemailproviders.com",
    "name": "First",
    "address": "Address"
    "ccn": "5457623898234112",
	"expiry": "08/13",
	"cvv": "123",
	"billingAddress": "Address"
  }
  ```
  
----

### Add User
Creates a new user

* **URL**

  /app/addUser

* **Method:**

  `POST`
  
* **Content-Type:**
  `application/json; charset=UTF-8`
  
* **Required Body:**

   `email=[string]`
   
   `name=[string]`
   
   `address=[string]`

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** `{ ok : "success adding user" }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "client sent invalid data" }`

* **Sample Call:**

  ```
  {
    "email": "someemail@someemailproviders.com",
    "name": "First",
    "address": "Address"
  }
  ```
----

### Add Payment
Creates a payment method

* **URL**

  /app/addPayment

* **Method:**

  `POST`
  
* **Content-Type:**
  `application/json; charset=UTF-8`
  
* **Required Body:**

   `ccn=[string]`
   
   `expiry=[string]`
   
   `cvv=[string]`
   
   `address=[string]`

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** `{ redirect : "success adding payment" }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "client sent invalid data" }`

* **Sample Call:**

  ```
  {
      "ccn": "5457623898234113",
      "expiry": "08/13",
      "cvv": "123",
      "address": "Address"
  }
  ```

## Running the tests

Run the Spring MVC Tests found in [app/test](https://github.com/azkevin/ProductSignup/tree/master/src/main/java/app/test)

## Authors

Created by Kevin Arindaeng