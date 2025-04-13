# 💣 Buscaminas en Java

## 📝 Descripción del Proyecto
Implementación en Java del clásico juego Buscaminas que se ejecuta por consola. El objetivo del juego es descubrir todas las casillas que no contienen minas, utilizando como pista los números que indican la cantidad de minas adyacentes a cada casilla descubierta.

## 🎯 Características principales
- Tablero interactivo representado en consola
- Generación aleatoria de minas
- Sistema de marcado de casillas con banderas 🚩 y símbolos de duda ❓
- Cálculo automático de minas adyacentes
- Algoritmo "flood fill" para abrir automáticamente casillas vacías
- Interfaz de usuario por consola con menú de opciones
- Visualización del tablero con colores para mejor experiencia de usuario

## 🏗️ Estructura del Proyecto

### Clases Principales

#### `Buscaminas`
Implementa la lógica principal del juego:
- Inicialización del tablero
- Colocación aleatoria de minas
- Cálculo de minas adyacentes
- Algoritmo recursivo para apertura de casillas
- Control del estado del juego
- Menú de opciones y bucle principal

#### `Casilla`
Representa cada celda del tablero:
- Gestiona los diferentes estados de una casilla (abierto, cerrado, marcado, duda)
- Almacena información sobre la presencia de minas
- Cuenta las minas adyacentes
- Define la representación visual de cada casilla según su estado

#### `MainBuscaminas`
Punto de entrada de la aplicación:
- Inicializa una partida con un tablero de 9x9 y 10 minas

## 🕹️ Cómo jugar
1. Ejecuta el programa desde la clase `MainBuscaminas`
2. En cada turno, selecciona una de las siguientes opciones:
   - **1**: Abrir una casilla
   - **2**: Marcar o desmarcar una casilla con bandera 🚩
   - **3**: Marcar o desmarcar una casilla con signo de duda ❓
3. Introduce las coordenadas en formato "fila columna"
4. El juego termina cuando:
   - Descubres una mina (derrota)
   - Marcas correctamente todas las minas (victoria)

## 🎮 Representación en consola
- Casillas cerradas: `· `
- Casillas marcadas: 🚩
- Casillas con duda: ❓
- Minas: 💣
- Casillas vacías: `- `
- Números de minas adyacentes:
  - 1: Verde
  - 2-3: Amarillo
  - 4-8: Rojo

## 💡 Características técnicas
- Implementación del algoritmo "flood fill" para revelar casillas adyacentes vacías
- Uso de enum para los estados de las casillas
- Colores ANSI para mejorar la visualización por consola
- Control del número máximo de banderas disponibles
- Validación de coordenadas introducidas

## 🔍 Detalles de implementación
- El algoritmo recursivo `abrirCasilla()` propaga la apertura de casillas hasta encontrar casillas con minas adyacentes
- Sistema de validación para asegurar que no se exceda el número máximo de banderas (igual al número de minas)
- Verificación automática de victoria cuando todas las minas están correctamente marcadas

## 🚀 Personalización
Para modificar la configuración del juego, cambia los parámetros en la clase `MainBuscaminas`:
```java
Buscaminas partida = new Buscaminas(9, 10); // Tablero 9x9 con 10 minas
```

---

Desarrollado por Alejandro Plata Cortés - Febrero 2025
