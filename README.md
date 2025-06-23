# 📚 Literalura - Explorador de Libros

> Este proyecto permite explorar libros y autores a través de una base de datos interactiva conectada con una API externa. Podés consultar títulos por idioma, autores vivos en determinada época y estadísticas generales.

![Portada del Proyecto](https://github.com/CristianEstMaida/Literalura/blob/main/literalura.png)

---

## 🏷️ Insignias

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen)
![Estado](https://img.shields.io/badge/estado-finalizado-brightgreen)

---

## 📑 Índice

1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Funcionalidades](#-funcionalidades)
3. [Cómo usarlo](#-cómo-usarlo)
4. [Ayuda y contacto](#-ayuda-y-contacto)
5. [Autores y colaboradores](#-autores-y-colaboradores)
6. [Tecnologías utilizadas](#-tecnologías-utilizadas)
7. [Licencia](#-licencia)

---

## 🧾 Descripción del Proyecto

Literalura conecta con una API externa para descargar libros y autores. Luego, guarda esa información en base de datos y permite búsquedas avanzadas, estadísticas, filtros por idioma y visualización por consola.

---

## 🧰 Funcionalidades

- Buscar libros por título desde API
- Guardar libros y autores en base de datos
- Consultar libros por idioma (`es`, `en`, `fr`, `pt`)
- Listar autores vivos en un año ingresado
- Top 10 libros más descargados
- Estadísticas globales con `DoubleSummaryStatistics`
- Búsqueda por nombre de autor
- Menú interactivo por consola

---

## 🧑‍💻 Cómo usarlo

Usá **Java 21** y **Maven Wrapper** para compilar y correr la app fácilmente:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## 🆘 Ayuda y contacto
Para dudas, sugerencias o reportes de errores, podés:

- Crear un [issue en GitHub](https://github.com/CristianEstMaida/Literalura/issues)
- Enviarme un mensaje si tenés acceso al proyecto en alguna plataforma educativa (como Alura)

---

## 👥 Autores y colaboradores

- 👨‍💻 **Cristian (@CristianEstMaida)** – Desarrollo completo, lógica de negocio y estructura del proyecto
- 🙌 Agradecimientos a [Alura LATAM](https://www.aluracursos.com/) por el material y los desafíos educativos

---

## 🔧 Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 / PostgreSQL
- Maven Wrapper
- Gson (para parseo JSON)
- API Gutendex (fuente de libros y autores)

---

## 🔓 Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.  
Podés consultar el archivo [`LICENSE`](LICENSE) para más detalles.
