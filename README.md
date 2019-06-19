# Product Signup

A simple product signup experience that utilizes Spring Boot REST APIs with MySQL as the datastore.

<img src="https://i.imgur.com/islIUGH.jpg">

## Usage

1. Import this project on STS and run it as a Spring Boot app.

2. Go to localhost:8080 on your web browser

## REST APIs

### Add User
Creates a new user

* **URL**

  /app/addUser

* **Method:**

  `POST`
  
* **Content-Type:**
  `application/json; charset=UTF-8`
  
* **Required:**

   `email=[string]`
   
   `name=[string]`
   
   `address=[string]`

* **Success Response:**

  * **Code:** 202 <br />
    **Content:** `{ redirect : "/payment" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "" }`

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
  
* **Required:**

   `ccn=[string]`
   
   `expiry=[string]`
   
   `cvv=[integer]`
   
   `address=[string]`

* **Success Response:**

  * **Code:** 202 <br />
    **Content:** `{ redirect : "/thanks" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "" }`

* **Sample Call:**

  ```
  {
      "ccn": "5457623898234113",
      "expiry": "08/13",
      "cvv": "123",
      "address": "Address"
  }
  ```

## Credit

Created by Kevin Arindaeng