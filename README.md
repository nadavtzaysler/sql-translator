# 🛠️ SQL Translator 

A SQL dialect translator with a user-friendly interface and support for multiple translation engines.
Currently supports [SQLGlot](https://github.com/tobymao/sqlglot) and [Apache Calcite](https://calcite.apache.org/).

> ⚠️ This project is under active development. Contributions and feedback are welcome!

---

## ✨ Features

* 🌐 Web-based UI (served at **port 80**)
* 🧠 Choose translation engine: **SQLGlot** or **Calcite**
* 📥 Select **input dialect**
* 📤 Select **output dialect**
* 📝 Input your SQL query and translate it instantly
* 🐳 Easy to run with `docker-compose`

---

## 🚀 Quick Start

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/sql-translator.git
cd sql-translator
```

### 2. Run with Docker Compose

```bash
docker-compose up
```

* UI available at: [http://localhost](http://localhost)
* SQLGlot API: `http://localhost:8080`
* Calcite API: `http://localhost:8082`

---

## 🧱 Architecture

```text
+------------------+
|                  |
|      Client      |
|  (Browser - UI)  |
|                  |
+--------+---------+
         |
         | User selects:
         | - Input dialect
         | - Output dialect
         | - Translation engine (Glot/Calcite)
         |
         v
+--------+---------+
|      Frontend     |
|     (Vue.js)      |
|       :80         |
+--------+----------+
         |
         | Based on selected engine:
         |
         |--------------------+
         |                    |
         v                    v
+----------------+    +----------------------+
| SQLGlot API    |    | Calcite API          |
| (Python @8080) |    | (Java + Calcite @8082)|
+----------------+    +----------------------+
```

The frontend dynamically routes the translation request to the selected engine (Glot or Calcite) depending on user input.

---

## 🔧 Configuration

Currently configured for simplicity:

* Static engine selection (per request)

---

## 🧑‍💻 Contributing

Contributions are welcome!

To contribute:

1. Fork the repository
2. Create a feature branch
3. Commit and push your changes
4. Open a pull request

Issues, suggestions, and feature requests are encouraged.

---

## 📍 Roadmap
* [ ] Support for more engines (e.g. Oracle, Presto, DuckDB)
* [ ] Internationalization
