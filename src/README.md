# CONVERSOR DE MONEDAS

## Descripción
Esta aplicación de Java proporciona un conversor de monedas que se basa en consultas a una API. Permite a los usuarios
realizar conversiones entre diferentes monedas de forma rápida y sencilla. Se prevé el agregado de otras API.  
a aplicación comienza con la ejecución de la clase Intermediador, que maneja la autenticación y la interacción del
usuario. Los usuarios pueden construir consultas de conversión de moneda, confirmarlas para ver los resultados y
guardarlas para futuras referencias antes de finalizar la sesión, momento en el que se restablece la clave de acceso.

## Funcionalidades

- Acceso Seguro: La aplicación requiere una clave de acceso para garantizar la seguridad de las consultas.
- Construcción de Consultas: Los usuarios pueden construir consultas para convertir entre monedas específicas.
- Almacenamiento de Consultas: Las consultas realizadas se almacenan para su posterior referencia.
- Interfaz de Usuario Intuitiva: La interfaz de usuario proporciona una experiencia de usuario amigable y fácil de usar.

### Clases Modelo

**Clave**  
Esta clase representa una combinación de nombre de API y clave de acceso. Permite obtener y establecer el nombre de la
API y la clave de acceso. Es utilizada para autenticarse en servicios externos.  
**Consulta**  
La clase Consulta representa una solicitud de conversión de moneda, incluyendo la moneda base y la moneda objetivo, el
valor
a cambiar y el valor cambiado, la tasa de cambio, y la fecha y hora de la consulta. Permite establecer y obtener estos
atributos.  
**Moneda**  
Esta clase representa una moneda, con su sigla, nombre completo y símbolo. Permite establecer y obtener estos atributos,
y proporciona un método toString() para obtener una representación legible de la moneda.  
**Respuesta**  
a clase Respuesta modela la respuesta de la API de conversión de monedas, con campos como resultado, documentación, y
tasa de conversión. Es una clase de registro inmutable con campos específicos y métodos predefinidos.

### Enumeraciones

**Divisa**
Esta enumeración que contiene diversas monedas junto con su nombre completo, sigla y símbolo. Proporciona métodos para
obtener estos detalles de cada divisa.

### Clases de Servicio:

**Habilitador**  
Esta Clase proporciona métodos para gestionar el acceso y el almacenamiento de claves de API. Incluye
funciones para validar la aceptación, solicitar y validar claves, así como para guardar y borrar claves en un archivo de
configuración. Estas funciones garantizan un acceso seguro y una gestión adecuada de las claves de API utilizadas en la
aplicación.  
**Buscador**  
Buscador facilita la búsqueda de divisas según una letra ingresada, devolviendo un mapa de monedas con su sigla y
nombre completo. Permite seleccionar divisas por su sigla o a través de un menú de selección. Además, genera instancias
de Moneda basadas en la información del Enum Divisa.  
**Asignador**  
Asignador se encarga de manejar las consultas de conversión de moneda. Incluye métodos para insertar monedas en la
consulta, completar la consulta con datos para la llamada a la API y concentrar las operaciones de las monedas en la
consulta. También asegura el ingreso del monto a cambiar, maneja excepciones y obtiene la tasa de conversión.  
**Llamador**  
La clase Llamador maneja las llamadas a la API para obtener tasas de conversión. Incluye métodos para seleccionar entre
diferentes APIs disponibles, construir direcciones de API, hacer la llamada a la API y capturar la respuesta, y obtener
la tasa de conversión de la respuesta. También maneja excepciones relacionadas con la conexión y el procesamiento de la
respuesta JSON.
**Calculador**  
La clase Calculador calcula el valor a recibir según el monto ingresado y la tasa de conversión.  
**Archivador**  
Archivador gestiona el almacenamiento de consultas en un archivo JSON. Crea, guarda, y borra consultas. Utiliza Gson
para la serialización JSON.  
**AdaptadorLocalDateTime**  
Esta clase consiste en un adaptador utilizado por la biblioteca Gson para serializar y deserializar objetos de tipo
LocalDateTime en y desde formato JSON.
- El método serialize convierte un objeto LocalDateTime en un elemento JSON.
- El método deserialize convierte un elemento JSON en un objeto LocalDateTime.  
  Esto permite que los objetos LocalDateTime se puedan almacenar y recuperar fácilmente en/desde archivos JSON. El
  formato de fecha y hora utilizado es "dd/MM/yyyy - HH:mm".  

## Instrucciones de Uso

Para utilizar la aplicación, sigue estos pasos:

1. Ejecuta la clase Intermediador.
2. Introduce tu clave de acceso cuando se solicite.
3. Construye tus consultas seleccionando las monedas base y objetivo.
4. Confirma tu consulta y revisa los resultados.
5. Puedes almacenar las consultas realizadas para su posterior referencia.
6. La aplicación guardará tus consultas y restablecerá tu clave de acceso al finalizar.

¡Disfruta convirtiendo monedas con Alura Global Exchange!