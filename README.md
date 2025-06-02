# BookstoreSecurityDemo

This repository contains a simple web application built with **Spring Boot**, designed as an educational tool to demonstrate common **web application vulnerabilities** and their secure counterparts. The project simulates a basic **online bookstore**, featuring user authentication, book browsing, cart management, user reviews, and an admin panel.

The repository is divided into two main branches:

* **`main`** – the **secure** version of the application, where best practices for security are implemented using Spring Security and Spring Data JPA. Input validation, access control, and safe data rendering are enforced to protect against common threats.

* **`insecure`** – the **intentionally vulnerable** version, which demonstrates how improper coding practices can expose the application to attacks such as:

  * **SQL Injection** – through unsafe query construction,
  * **Cross-Site Scripting (XSS)** – by rendering user input without escaping,
  * **Broken Access Control** – due to missing role-based restrictions on endpoints.

This project is intended for **educational and testing purposes only**. It can be used to study attack scenarios and understand how secure coding practices mitigate real-world risks.

> Recommended usage:
>
> * Clone the repo
> * Switch to the `insecure` branch to test vulnerabilities
> * Compare with the `main` branch to analyze secure implementations

---
