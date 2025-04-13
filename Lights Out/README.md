# 🎮 Lights Out

## 📝 Descripción del Proyecto
Este proyecto es una implementación en Java del popular juego de rompecabezas Lights Out. El objetivo principal del juego es apagar todas las luces en un tablero interactivo. Al presionar una luz, esta cambia de estado (de encendida a apagada o viceversa), al igual que las luces adyacentes en dirección vertical y horizontal (pero no en diagonal).

## 🎯 Objetivos del Proyecto
El proyecto busca poner en práctica conocimientos de programación en Java, incorporando conceptos como:
- Programación modular
- Encapsulación
- Manejo de clases
- Documentación de código
- Lectura y escritura de archivos

## ⚙️ Características principales

### Versión Básica
- Tablero interactivo representado en consola
- Configuración de partidas mediante archivo externo
- Generación de tableros aleatorios
- Sistema de tiempo limitado para resolver el rompecabezas

### Versión Ampliada
- Modo multijugador
- Sistema de puntuación y comodines
- Diferentes niveles de dificultad (fácil, intermedio y difícil)
- Historial de partidas
- Fichero de configuración con interfaz amigable

## 🏗️ Estructura del Proyecto

### Clases Principales
- **Tablero**: Gestiona la matriz de casillas que conforman el juego
- **Casilla**: Representa cada celda del tablero con su estado (encendido/apagado)
- **Partida**: Controla la lógica y el flujo del juego
- **Jugador**: (Versión ampliada) Administra los datos y acciones de cada jugador
- **Main**: Punto de entrada de la aplicación

## 🕹️ Cómo jugar
1. Ejecuta el programa desde la clase Main
2. Selecciona el modo de juego (aleatorio o personalizado)
3. Si eliges el modo personalizado, configura el juego a través del archivo de configuración
4. En tu turno, introduce las coordenadas de la casilla que deseas presionar
5. Intenta apagar todas las luces antes de que se acabe el tiempo

## 📚 Aprendizajes del Desarrollo
- Implementación práctica de matrices bidimensionales
- Manejo de archivos para guardar configuraciones e historiales
- Programación orientada a objetos aplicada a un juego completo
- Modularización de código para mejorar mantenibilidad y escalabilidad

## 🛠️ Tecnologías Utilizadas
- Java SE
- Programación por consola (sin interfaz gráfica)
- Archivos de texto para configuración y almacenamiento de datos

---

Proyecto desarrollado por Alejandro Plata Cortés como parte de la asignatura de Programación.
