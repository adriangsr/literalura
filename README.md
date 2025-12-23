# 📚 Challenge Literatura - Alura Latam | ONE

Aplicación de **consola desarrollada en Java con Spring Boot** como parte del **Challenge Literatura** del programa **Oracle Next Education (ONE) - Alura Latam**.

El proyecto consume la API pública **Gutendex** para consultar libros y permite al usuario **registrarlos en una base de datos PostgreSQL**, así como realizar distintas consultas desde un menú interactivo por consola.

---

## 🎯 Objetivo del proyecto

Desarrollar una aplicación que:
- Consuma una API REST externa.
- Procese y persista información en una base de datos relacional.
- Permita consultas dinámicas mediante un menú en consola.
- Aplique buenas prácticas con **Spring Boot, JPA y arquitectura en capas**.

---

## 🚀 Funcionalidades

✔️ Buscar libros por nombre usando la API de **Gutendex**  
✔️ Registrar libros y autores en una base de datos **PostgreSQL**  
✔️ Listar libros registrados  
✔️ Listar autores registrados  
✔️ Listar autores vivos en un año determinado  
✔️ Listar libros según su idioma  
✔️ Evitar duplicados en la base de datos  
✔️ Interfaz de usuario por **consola interactiva**

---

## 🛠️ Tecnologías utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **API Gutendex**
- **Maven**
- **Hibernate**
- **IntelliJ IDEA / VS Code**
- **Git & GitHub**

---

# ⚙️ Configuración del proyecto
## 1️⃣ Clonar el repositorio
git clone https://github.com/tu-usuario/literatura-challenge.git
cd literatura-challenge

---

## 2️⃣ Configurar la base de datos PostgreSQL

- Crear una base de datos en PostgreSQL:

CREATE DATABASE literatura;


- Configurar el archivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

---

## 3️⃣ Ejecutar la aplicación
mvn spring-boot:run

La aplicación se ejecuta completamente desde la consola.

---

## 🖥️ Uso de la aplicación

Al iniciar, se muestra un menú interactivo que permite:

- 1 - Buscar libro por nombre
- 2 - Listar libros registrados
- 3 - Listar autores registrados
- 4 - Listar autores vivos en un año
- 5 - Listar libros por idioma
- 0 - Salir


El usuario selecciona una opción y el sistema realiza la consulta correspondiente, ya sea a la API externa o a la base de datos local.

---

## 🌐 API utilizada

Gutendex

API pública basada en el Proyecto Gutenberg

Documentación: https://gutendex.com/

---

## 📚 Aprendizajes adquiridos

✔️ Consumo de APIs REST con Spring Boot

✔️ Uso de DTOs y mapeo de datos

✔️ Persistencia con JPA e Hibernate

✔️ Relaciones entre entidades (libros y autores)

✔️ Consultas personalizadas con Spring Data JPA

✔️ Manejo de menús por consola

✔️ Buenas prácticas de organización y arquitectura

---

## 👨‍💻 Autor

- Adrián Solano Ramos
- Estudiante de Ingeniería Informática – UNED
- Programa Oracle Next Education (ONE)

---

## 🔗 GitHub: https://github.com/adriangsr

---

## 📄 Licencia

Este proyecto se desarrolló con fines educativos como parte del programa ONE – Alura Latam.

---

## 📂 Estructura del proyecto

```bash
📁 literatura
 ┣ 📁 src/main/java
 ┃ ┣ 📁 com.alura.literatura
 ┃ ┃ ┣ 📁 model
 ┃ ┃ ┣ 📁 repository
 ┃ ┃ ┣ 📁 service
 ┃ ┃ ┣ 📁 principal
 ┃ ┃ ┗ 📄 LiteraturaApplication.java
 ┣ 📁 src/main/resources
 ┃ ┣ 📄 application.properties
 ┣ 📄 pom.xml
 ┣ 📄 README.md


