# Sistema de Procesamiento de Operaciones con Tarjetas de Crédito

## Descripción del Proyecto

Este proyecto tiene como objetivo desarrollar un sistema que permita procesar operaciones con tarjetas de crédito para una organización. El sistema implementa un conjunto de reglas y validaciones para asegurar la integridad de las operaciones, así como el manejo seguro de datos sensibles como el CVV.

El sistema también incluye funcionalidades para la gestión de usuarios y tarjetas, el cálculo de tasas por operación y la ejecución de compras con notificaciones al tarjetahabiente mediante correo electrónico.

## Requerimientos del Sistema

El sistema cumple con las siguientes características:

- **Gestión de Tarjetas**:
    - Una tarjeta se identifica por su marca (VISA, NARA, AMEX), número de tarjeta, cardholder (nombre, apellido, DNI, fecha de nacimiento, y email), y fecha de vencimiento.
    - Un código de seguridad (CVV) de 3 dígitos es requerido para las operaciones y se maneja de manera confidencial.
    - Al registrar una nueva tarjeta, el CVV y el número PAN son enviados de manera segura al email del usuario/cardholder.

- **Operaciones**:
    - Una operación es válida si el monto es menor a 10,000 pesos.
    - La tarjeta es válida para operar si su fecha de vencimiento es mayor a la fecha actual.
    - El sistema permite calcular la tasa de servicio de una operación.

## Funcionalidades Implementadas

### 1. Gestión de Usuarios:
- Alta de usuarios. (Baja y modificación opcional)

### 2. Gestión de Tarjetas:
- Alta de tarjetas. (Baja y modificación opcional)
- El CVV y el PAN se envían al correo del usuario para mayor seguridad.

### 3. Cálculo de Tasas:
- Cálculo de la tasa de una operación dependiendo de la marca de la tarjeta:
    - **VISA**: Año / Mes (Ejemplo: 2020/12 = 1.67%).
    - **NARA**: Día del mes * 0.5 (Ejemplo: 27 * 0.5 = 13.5%).
    - **AMEX**: Mes * 0.1 (Ejemplo: 9 * 0.1 = 0.9%).

### 4. Realización de Compras:
- Realizar una compra indicando el monto, detalle, tarjeta asociada y CVV.
- El sistema notifica al usuario por email después de una compra exitosa.

## Tecnologías Utilizadas

- **Java Spring Boot**: Para el desarrollo de la API REST.
- **MysqlSQL**: Base de datos para almacenar usuarios y tarjetas.
- **Java Mail**: Para el envío de notificaciones por correo electrónico.
- **AWS (Amazon Web Services):** Para el hosting en la nube.
- **JPA (ava Persistence API):** especificación de Java que define cómo interactuar con bases de datos relacionales a través de objetos Java.

## Endpoints de la API

### 1. Usuarios
- **POST /persona/altaPersona**: Registrar una persona.
- **DELETE /persona/{dni}**: Eliminar un usuario.

### 2. Tarjetas
- **POST /tarjeta/altaTarjeta**: Crear nueva tarjeta
- **DELETE /tarjetas/{pan}**: Eliminar una tarjeta.

### 3. Tasa de Operaciones
- **GET /operacion/{nroOperacion/id}**: Consultar la tasa de una operación proporcionando la marca de la tarjeta y el importe.

### 4. Compras
- **POST /operacion/compra**: Realizar una compra proporcionando el monto, el detalle, la tarjeta asociada y el CVV. Al finalizar la compra, el usuario recibe una notificación por email.

## Despliegue en la Nube

El sistema está hosteado en **[Amazon Web Services (AWS)]**, y puede ser accedido desde la siguiente URL:

- **URL**: [https://your-cloud-url.com](http://localhost:8080/swagger-ui/index.html/)

## Instalación y Ejecución Local

1. Clona el repositorio:

   ```bash
   git clone https://github.com/Bautistadev/Ejercicio_2.git
   ```