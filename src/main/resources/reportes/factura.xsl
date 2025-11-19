<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/Factura">
        <html>
            <head>
                <title>Factura de Compra - <xsl:value-of select="id"/></title>
            </head>
            <body>
                <h2>FACTURA N°: <xsl:value-of select="id"/></h2>
                <p>Cliente: <xsl:value-of select="nombreCliente"/></p>
                <p>Identificacion: <xsl:value-of select="identificacionCliente"/></p>
                <p>Fecha de Compra: <xsl:value-of select="fechaCompraFormateada"/></p>

                <table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Total Items</th>
                            <th>Precio Unidad</th>
                            <th>Impuesto</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="detallesFactura/detalle">
                            <tr>
                                <td><xsl:value-of select="nombreProducto"/></td>
                                <td><xsl:value-of select="totalItems"/></td>
                                <td><xsl:value-of select="precioProducto"/></td>
                                <td><xsl:value-of select="impuesto"/></td>
                                <td><xsl:value-of select="valorTotal"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
                <div>
                    <p>Total Venta: <span><xsl:value-of select="totalVenta"/></span></p>
                    <p>Total Impuesto: <span><xsl:value-of select="totalImpuesto"/></span></p>
                    <p class="total">TOTAL A PAGAR: <span><xsl:value-of select="totalFactura"/></span></p>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>