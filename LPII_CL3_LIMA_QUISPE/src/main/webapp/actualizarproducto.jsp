<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Producto</title>
    <style>
        body {
            background-color: #c5dec9; 
            color: #212529; 
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #c5dec9; 
            color: #000;
            text-align: center;
            padding: 20px 0;
        }
        main {
            padding: 20px;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff; 
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            border-spacing: 0;
        }
        table th, table td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .btn {
            display: inline-block;
            background-color: #28a745; 
            color: #fff; 
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #218838; 
        }
    </style>
</head>
<body>
    <header>
        <h1>Editar Producto</h1>
    </header>
    <main>
        <div class="container">
            <!-- Aquí va el formulario de edición de productos -->
            <form action="ControladorProducto" method="post">
                <input type="hidden" name="action" value="actualizar">
                <input type="hidden" name="id" value="${producto.idproductoscl3}">
                <label for="nombre">Nombre:</label><br>
                <input type="text" id="nombre" name="nombre" value="${producto.nombrecl3}" required><br><br>
                <label for="preciocompra">Precio Compra:</label><br>
                <input type="number" id="preciocompra" name="preciocompra" value="${producto.preciocompcl3}" required><br><br>
                <label for="precioventa">Precio Venta:</label><br>
                <input type="number" id="precioventa" name="precioventa" value="${producto.precioventacl3}" required><br><br>
                <label for="estado">Estado:</label><br>
                <input type="text" id="estado" name="estado" value="${producto.estadocl3}" required><br><br>
                <label for="descripcion">Descripción:</label><br>
                <textarea id="descripcion" name="descripcion" rows="4" required>${producto.descripcl3}</textarea><br><br>
                <button type="submit" class="btn">Actualizar Producto</button>
            </form>
        </div>
    </main>
</body>
</html>
