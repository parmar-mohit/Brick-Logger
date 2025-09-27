# 📄 Logger Configuration Guide

Your framework provides a **configurable logger** that reads settings from `application.yaml`.  
This document explains the configuration format, available options, and examples.

---

## 1. Root Structure

All logger settings are defined under the root `logger` object in `application.yaml`.

```yaml
logger:
  <properties>
```

---

## 2. Supported Properties

### 2.1 `clear` (optional, boolean)
- Determines whether to clear existing log files before writing new logs.
- Default: `false`.

**Behavior:**
- `true` → Deletes the existing content of log files at startup.
- `false` → Appends new logs to existing files.

---

### 2.2 Log Level Objects
Under `logger`, you can define **one or more log levels** as objects.  
Each log level object supports a `file` property that specifies where logs should be written.

#### Log Levels Supported:
- `trace`
- `debug`
- `info`
- `warn`
- `error`
- `fatal`
- `all` (special level: logs of **all levels** go into these files)

#### Properties of a log level:

| Property | Type             | Description                                                                 |
|----------|------------------|-----------------------------------------------------------------------------|
| `file`   | string / list    | One or more file paths. Logs for this level will be written to these files. |

---

## 3. Examples

### Example 1: Minimal Configuration
```yaml
logger:
  info:
    file: logs/info.log
```
✅ Logs of level `INFO` will be written to `logs/info.log`.  
Existing logs will be preserved (since `clear` is not set).

---

### Example 2: Multiple Log Levels with Separate Files
```yaml
logger:
  clear: true
  debug:
    file: logs/debug.log
  error:
    file: logs/error.log
```
✅ On startup, `logs/debug.log` and `logs/error.log` will be cleared.  
✅ `DEBUG` logs go to `logs/debug.log`, `ERROR` logs go to `logs/error.log`.

---

### Example 3: Single Log Level Writing to Multiple Files
```yaml
logger:
  warn:
    file: 
      - logs/warnings.log
      - logs/all-issues.log
```
✅ All `WARN` logs are written to **both** `logs/warnings.log` and `logs/all-issues.log`.

---

### Example 4: Using the `all` Level
```yaml
logger:
  all:
    file: logs/application.log
  fatal:
    file: logs/fatal.log
```
✅ All logs (`TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`) go to `logs/application.log`.  
✅ Only `FATAL` logs also go to `logs/fatal.log`.

---

## 4. Notes & Best Practices

1. **Relative vs Absolute Paths**
    - Relative paths (e.g., `logs/info.log`) are resolved from the application’s working directory.
    - Absolute paths (e.g., `/var/log/myapp/info.log`) are supported.

2. **`all` Level**
    - Acts as a global catch-all.
    - Useful for maintaining a single consolidated log file.

3. **Multiple Outputs**
    - A log level can write to **multiple files** by using a list under `file`.

4. **Default Behavior**
    - If a log level is not configured, logs for that level will be ignored.

---

✅ With this structure, you can fully control which log levels are written, where they are stored, and whether old logs are preserved or cleared.  
