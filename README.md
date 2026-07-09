# RFID SDK Mock

A Java-based mock RFID SDK developed for learning, testing, and simulation purposes.

## Features

- EPC Gen2 inventory simulation
- Dynamic Q Algorithm
- Inventory Round implementation
- Mock RFID Reader
- Tag Generator
- Tag Read Listener
- RSSI simulation
- Multi-tag support

## Project Structure

```
SDK/
├── src/
│   └── com/
│       └── senkara/
│           └── rfid/
│               ├── model/
│               ├── protocol/
│               ├── sdk/
│               └── Main.java

TestConsumer/
```

## Technologies

- Java
- IntelliJ IDEA

## Example Output

```text
EPC : 111122223333444455556666
TID : E2801170000002081F0A1234
RSSI: -50
ANT : 1
TIME: 2026-07-09T10:56:45
```

## Purpose

This project demonstrates how an RFID SDK can be designed independently from hardware by simulating EPC Gen2 inventory operations and reader behavior.