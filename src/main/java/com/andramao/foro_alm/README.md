# ForoAlm API - Challenge Backend Alura 🚀

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.4.2-green?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</p>

Bienvenido al **ForoAlm**, una robusta API REST desarrollada con **Spring Boot**. Este proyecto simula el funcionamiento de un foro de discusión, permitiendo gestionar tópicos, respuestas, cursos y usuarios, todo bajo un sistema de seguridad basado en tokens JWT.



---

## 🛠️ Tecnologías y Herramientas

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.4.2
* **Seguridad:** Spring Security & JWT (JSON Web Token)
* **Persistencia:** Spring Data JPA & Hibernate
* **Base de Datos:** MySQL
* **Migraciones:** Flyway
* **Documentación:** SpringDoc OpenAPI (Swagger UI)

---

## ⚙️ Configuración y Variables de Entorno

Para garantizar la seguridad, la aplicación utiliza variables de entorno. Debes configurar las siguientes claves en tu sistema o en el panel de tu servicio de hosting:

| Variable | Descripción | Ejemplo |
| :--- | :--- | :--- |
| `DB_URL` | Dirección de la BD MySQL | `jdbc:mysql://localhost:3306/forohub` |
| `DB_USER` | Usuario de acceso a la BD | `root` |
| `DB_PASSWORD` | Contraseña de acceso a la BD | `tu_password_seguro` |
| `JWT_SECRET` | Clave para firmar los tokens | `una_cadena_muy_larga_y_aleatoria` |

---

## 📖 Documentación de la API (Swagger)

La API cuenta con documentación interactiva que te permite probar los endpoints en tiempo real.

1. Inicia la aplicación.
2. Accede a: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



---

## 🔑 Autenticación y Uso

La mayoría de los endpoints están protegidos. Sigue estos pasos para interactuar:

1. **Login:** Realiza un `POST` a `/login` con un usuario y contraseña válidos.
2. **Token:** Copia el `token` devuelto en la respuesta.
3. **Autorización:** En cada petición subsiguiente, añade el Header:  
   `Authorization: Bearer <tu_token_aqui>`

---

## 📦 Instalación y Despliegue Local

1.  **Clonar:**
    ```bash
    git clone [https://github.com/tu-usuario/foro-hub.git](https://github.com/tu-usuario/foro-hub.git)
    ```
2.  **Compilar y Empaquetar:**
    ```powershell
    .\mvnw.cmd clean package -DskipTests
    ```
3.  **Ejecutar Perfil de Producción:**
    ```powershell
    java -jar -Dspring.profiles.active=prod target/foroAlm-0.0.1-SNAPSHOT.jar
    ```

---

## 👤 Autor

Desarrollado por **Andramao** – Apasionado por el desarrolo de  software y la arquitectura de software.

¡Conectemos!
* **LinkedIn**: [Alexander Murillo](https://www.linkedin.com/in/alexander-murillo-39240b238/)
* **GitHub**: [Andramao](https://github.com/Andramao)
* **Email**: andramao@hotmail.com

---
<p align="center">Desarrollado con ❤️ para el Challenge Alura - 2026</p>