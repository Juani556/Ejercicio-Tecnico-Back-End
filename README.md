# ecommerce-rest

##Endpoints

###POST /carrito/new

Crea un carrito nuevo.
Recibe un objeto JSON de este estilo

{
"documento": "40645214",
"isSpecial": true
}

###DELETE /carrito/{idCarrito}

Borra un carrito.
Se pasa el id del carrito en el PATH

### POST /carrito/add

Agrega una unidad de producto. Toma solo el precio del primer producto con ese nombre. Recibe un JSON con este formato

{
"nombre": "lavandina",
"idCarrito": 1,
"precio": 100
}

### POST /carrito/delete

Borra una unidad de producto.
Recibe lo mismo que el endpoint de arriba.

### GET /carrito?idUsuario=X

Devuelve el carrito activo del usuario.
Recibe un query param llamado idUsuario.
 
### POST /carrito/finalizar/{idCarrito}

Cierra el carrito y crea una compra.
Recibe el id del carrito en el path.

### GET /compras

Devuelve las compras realizadas por un usuario
Recibe un objeto JSON como el siguiente

{
"documento": ""40645214"",
"from": "21/02/2022",
"to": null,
"orden": "monto"
}

El formato de la fecha es dd/MM/yyyy.
El orden puede ser "monto" o "fecha"