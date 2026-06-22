# Fábrica de Vehículos

Aplicación de simulación de cadenas de montaje, por Nicolás Míguez Ramos.

## Descripción

El proyecto consiste en el modelado y simulación de una fábrica de vehículos automatizada. La aplicación gestiona de forma integrada el funcionamiento de las líneas de producción, el control de stock de componentes, la asignación de personal y la resolución de contingencias en tiempo real.

El proyecto destaca por las siguientes características técnicas:

1. **Arquitectura desacoplada (Patrón DAO)**:  
   La persistencia y el almacenamiento de datos se encuentran completamente aislados del sistema de gestión, facilitando la migración a estructuras de datos o bases de datos externas en el futuro sin alterar la lógica de negocio.

2. **Polimorfismo dinámico**:  
   Implementación de jerarquías para trabajadores y componentes, permitiendo un comportamiento dinámico del sistema en función de la experiencia de los operarios o del tipo de perfil técnico.

3. **Simulación temporal**:  
   Un componente Planificador (Scheduler) actúa como reloj del sistema, simulando el avance segundo a segundo de los vehículos a través de las distintas estaciones de montaje.

4. **Patrón de observación (Dashboard)**:  
   Cuadro de mandos desacoplado del sistema de visualización que monitoriza en tiempo real el balance del almacén y el estado de avance de los chasis.

La factoría automatiza la construcción de tres tipos de vehículos (Turismos, Biplazas Deportivos y Furgonetas) mediante cadenas independientes. Cada coche pasa secuencialmente por cuatro estaciones de montaje controladas por robots y operarios: Chasis, Motor, Tapicería y Ruedas.

## Tecnologías empleadas

El desarrollo del simulador se basa en un conjunto de tecnologías estándar que garantizan estabilidad, seguridad y una buena experiencia de uso.

### Entorno y desarrollo

- **BlueJ**: entorno de desarrollo integrado empleado para la aprendizaje y visualización del diagrama de clases.
- **Java**: lenguaje principal utilizado para toda la lógica orientada a objetos del proyecto.

### Estructura del personal y componentes

- **Operarios y Mecánicos**: controlan los robots de montaje y reparan las averías físicas. Su tiempo varía según la experiencia acumulada.
- **Administrador del Sitema**: encargado de restaurar el software de gestión y las cadenas ante caídas críticas de luz o apagones.
- **Gestor de Planta**: supervisa el Dashboard, configura pedidos y coordina las líneas desde el panel de control.

### Lenguajes usados

- **Java** – implementación de patrones de diseño, encapsulamiento, abstracción, herencia y polimorfismo.

# Instrucciones para ejecutar la Fábrica de Vehículos

## Requisitos previos

- Tener instalado **BlueJ** (o cualquier IDE compatible con proyectos Java estándar)
- Disponer de **JDK 8** o superior
- Tener **Git** instalado

---

## Pasos para la ejecución

1. **Clonar el repositorio en tu máquina local:** git clone https://github.com/niicomiguez/PracticaPOO.git

2. **Cargar el proyecto en el entorno de desarrollo:** inicia el entorno BlueJ, selecciona la opción Project, luego Open Project en el menú superior, navega hasta la carpeta descargada y selecciona el archivo package.bluej.

3. **Compilar los componentes:** haz clic en el botón Compile situado en el panel izquierdo de BlueJ para compilar todas las clases del sistema simultáneamente.

4. **Iniciar la aplicación:** haz clic derecho sobre la clase que contiene el punto de entrada de la simulación (por ejemplo, Planificador), selecciona el constructor o el método de inicialización para instanciar el objeto e iniciar el entorno de pruebas.

## Modos de simulación disponibles

Para probar la funcionalidad completa, el planificador permite ejecutar tres escenarios:

- **Simulación Simple:** producción fluida sin incidencias externas, variando de forma aleatoria el perfil de eficiencia de los operarios asignados.
- **Simulación Compleja:** introduce averías mecánicas aleatorias en las cintas que requieren la intervención de los mecánicos.
- **Simulación Muy Compleja:** escenario crítico que combina múltiples averías físicas por cadena junto con caídas del suministro eléctrico que congelan la producción hasta la intervención del Administrador.

## Sobre la autoría

Son estudiante del Grado de Ingeniería Informática. Tengo experiencia con el desarrollo de aplicaciones y sistemas orientados a objetos, trabajando en entornos de diseño desacoplados y eficientes.

Este trabajo refleja el enfoque que quiero seguir en el futuro como desarrollador, creando aplicaciones útiles, bien estructuradas y con potencial para evolucionar.

Se puede contactar conmigo a través del correo electrónico: niicomiguez@gmail.com
