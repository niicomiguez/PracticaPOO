# Fábrica de Vehículos - Simulador de Cadenas de Montaje (POO)

Este proyecto consiste en el modelado y simulación de una fábrica de vehículos automatizada, desarrollado como parte de la asignatura de Programación Orientada a Objetos (POO). La aplicación gestiona de forma integrada el funcionamiento de las líneas de producción, el control de stock de componentes, la asignación de personal y la resolución de contingencias en tiempo real.

## Arquitectura y Características Técnicas

El sistema está diseñado bajo el paradigma de Diseño Orientado a Objetos, priorizando el desacoplamiento, la alta cohesión y la extensibilidad del código.

* **Arquitectura Desacoplada (Patrón DAO):** La persistencia y el almacenamiento de datos se encuentran completamente aislados del sistema de gestión, facilitando la migración a estructuras de datos o bases de datos externas en el futuro sin alterar la lógica de negocio.
* **Polimorfismo Dinámico:** Implementación de jerarquías para trabajadores (Operarios, Mecánicos, Administradores, Gestores) y componentes (Motores, Ruedas, Tapicerías), permitiendo un comportamiento dinámico en función de la experiencia y el tipo de perfil.
* **Simulación Concurrente / Temporal:** Un componente Planificador (Scheduler) actúa como reloj del sistema, simulando el avance segundo a segundo de los vehículos a través de las distintas estaciones de montaje.
* **Patrón de Observación (Dashboard):** Cuadro de mandos desacoplado del sistema de visualización que monitoriza en tiempo real el balance del almacén y el estado de avance de los chasis.

---

## Estructura del Sistema

La factoría automatiza la construcción de tres tipos de vehículos mediante cadenas independientes:
1. Taristas / Turismos
2. Biplazas Deportivos
3. Furgonetas

### Estados del Vehículo en la Línea
Cada coche pasa secuencialmente por cuatro estaciones de montaje controladas por robots y operarios:
Chasis -> Motor (Eléctrico/Gasolina/Híbrido) -> Tapicería (Tela/Cuero/Alcántara) -> Ruedas (Normal/Deportivo/Todoterreno)

### Perfiles de Personal Modelados
* **Operarios (Eficientes / Estándar):** Controlan los robots de montaje. Su tiempo de ejecución varía (1 segundo o 3 segundos) según su experiencia acumulada (umbral de 10 montajes).
* **Mecánicos de Cinta (Efectivos / Estándar):** Encargados de resolver las roturas físicas en las líneas de montaje de forma polimórfica según su nivel de reparaciones previas.
* **Administrador del Sistema:** Restaura el software de gestión y las cadenas ante caídas críticas de luz o apagones, requiriendo tiempos de parada específicos para cada subsistema.
* **Gestor de Planta:** Supervisa el Dashboard, configura pedidos y coordina las órdenes de reparación tras evaluar las alertas del sistema.

---

## Modos de Simulación Implementados

El planificador permite ejecutar tres escenarios de estrés para evaluar la robustez y contingencia de la fábrica:

1. **Simulación Simple:** Producción fluida sin incidencias externas, variando de forma aleatoria el perfil de eficiencia de los operarios asignados a las estaciones.
2. **Simulación Compleja:** Introduce averías mecánicas aleatorias en las cintas que requieren la asignación, desplazamiento e intervención de los mecánicos de cinta.
3. **Simulación Muy Compleja:** Escenario crítico que combina múltiples averías físicas por cadena junto con caídas del suministro eléctrico que congelan la producción hasta la intervención del Administrador del Sistema.

---

## Tecnologías Utilizadas

* **Lenguaje:** Java 8 / Superior
* **Entorno de Desarrollo:** BlueJ / IDEs compatibles con estructuras estándar de proyectos Java
* **Paradigma:** Programación Orientada a Objetos (Encapsulamiento, Abstracción, Herencia y Polimorfismo)

---

## Ejecución del Proyecto

1. Clonar el repositorio en tu máquina local: git clone https://github.com/tu-usuario/nombre-del-repositorio.git

2. Cargar el proyecto en el entorno de desarrollo: inicia el entorno BlueJ, selecciona la opción Project, luego Open Project en el menú superior, navega hasta la carpeta descargada y selecciona el archivo package.bluej.

3. Compilar los componentes: haz clic en el botón Compile situado en el panel izquierdo de BlueJ para compilar todas las clases del sistema simultáneamente.

4. Iniciar la aplicación: haz clic derecho sobre la clase que contiene el punto de entrada de la simulación, selecciona el constructor o el método de inicialización para instanciar el objeto e iniciar el entorno de pruebas.
