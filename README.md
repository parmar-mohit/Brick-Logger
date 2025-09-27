# 📝 Brick Logger Framework

A lightweight, configurable **logging framework** for Java applications.  
It uses a simple `application.yaml` configuration to control log levels, output files, and log persistence behavior.

---

## 🚀 Features

- ✅ Configurable via `application.yaml`
- ✅ Supports standard log levels: `trace`, `debug`, `info`, `warn`, `error`, `fatal`
- ✅ Special `all` level for consolidated logging
- ✅ Output to single or multiple files per log level
- ✅ Option to clear or append to existing logs
- ✅ Simple, framework-agnostic design

---

## 📦 Installation

Clone the repository:

```bash
git clone https://github.com/parmar-mohit/Brick-Logger.git
cd Brick-Logger
```

Build with Maven/Gradle (depending on your project setup):

```bash
mvn clean install
```

or

```bash
gradle build
```

Add it as a dependency in your project:

```xml
<dependency>
    <groupId>com.brick</groupId>
    <artifactId>com.brick.logger</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## ⚙️ Configuration

The logger reads from `application.yaml`.  
Below is an example:

```yaml
logger:
  clear: true
  info:
    file: logs/info.log
  error:
    file: 
      - logs/error.log
      - logs/critical.log
  all:
    file: logs/application.log
```

📖 See the full [Logger Configuration Guide](logger_configuration.md) for detailed instructions.

---

## 🖊️ Usage

```java
Logger.info("Application started successfully.");
Logger.error("An error occurred while processing request.");
Logger.debug("Debugging details here...");
```

---

## 📂 Project Structure

```
logger-framework/
├── src/
│   ├── main/java/com/example/logger/   # Core logger implementation
│   ├── test/java/com/example/logger/   # Unit tests                   # Example configuration
├── documentation/
│   ├── config.md             # Full configuration documentation
└── README.md                           # Project overview
```

---

## 🤝 Contributing

Contributions are welcome!  
Feel free to open issues, submit pull requests, or suggest improvements.

---

