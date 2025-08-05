# Calculadora

**Calculadora Mestre** is the central coordination server of a distributed calculator system developed in Java. It communicates with multiple clients and subordinate "slave" calculators — both basic and special — through RMI (Remote Method Invocation) and TCP sockets.

This application manages client requests, delegates calculations to available slave instances (escravos), handles communication failures, and ensures scalable load distribution. It is part of a larger system composed of:

- **Calculadora-cliente** – GUI client for users
- **Calculadora-basico** – basic operation slave (add, subtract, etc.)
- **Calculadora-especial** – special operation slave (e.g., square root)
- **Calculadora-clienteStress** – test client for simulating heavy loads

---

## 🧠 How It Works

- **Clients** connect via TCP sockets and send calculation requests.
- The **MestreServer** receives and parses the request.
- The **MestreImple** class interacts with registered slave services via RMI.
- The server chooses the appropriate slave (basic or special) and delegates the operation.
- The result is sent back to the client through the same socket.

---

## 📂 Project Structure

```bash
Calculadora-mestre/
├── src/
│   └── calc/mestre/
│       ├── controller/          # Front-facing logic (optional GUI/controller structure)
│       ├── server/              # Main socket + RMI handling (MestreServer.java, MestreImple.java)
│       ├── util/                # Utility functions (e.g., logging, ID generation)
│       └── exception/           # Custom exception handling
├── build.xml                   # Ant build script
├── manifest.mf
└── nbproject/                  # NetBeans project metadata
