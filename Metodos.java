import javax.swing.JOptionPane;
import java.util.Stack;

public class Metodos {
    private Stack<Productos> pila = new Stack<>();

    public void MostrarMenu() {
        int r = Integer.parseInt(JOptionPane.showInputDialog(null, 
            "1. Agregar un repuesto\n2. Buscar repuesto\n3. Mostrar pila\n4. Eliminar repuesto\n5. Salir"));

        switch (r) {
            case 1:
                LlenarPila();
                MostrarMenu();
                break;
            case 2:
                BuscarProducto();
                MostrarMenu();
                break;
            case 3:
                MostrarPila();
                MostrarMenu();
                break;
            case 4:
                EliminarProducto();
                MostrarMenu();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                break;
        }
    }

    public void LlenarPila() {
        boolean continuar = true;
        while (continuar) {
            Productos o = new Productos();
            o.setMarca(JOptionPane.showInputDialog("Ingrese la marca:"));
            o.setReferencia(JOptionPane.showInputDialog("Ingrese la referencia:"));
            o.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad:")));
            o.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:")));
            pila.push(o);
            
            String agregar = JOptionPane.showInputDialog("Desea agregar más registros? S/N:");
            if (agregar.equalsIgnoreCase("N")) {
                continuar = false;
            }
        }
    }

    public void BuscarProducto() {
        if (pila.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista.");
            return;
        }
    
        boolean continuar = true;
        while (continuar) {
            String referencia = JOptionPane.showInputDialog("Ingrese la referencia del producto a buscar:");
            boolean encontrado = false;
    
            for (Productos o : pila) {
                if (o.getReferencia().equalsIgnoreCase(referencia)) {
                    JOptionPane.showMessageDialog(null, 
                        "Marca: " + o.getMarca() + "\nReferencia: " + o.getReferencia() + 
                        "\nCantidad: " + o.getCantidad() + "\nPrecio: " + o.getPrecio());
                    encontrado = true;
                    break;
                }
            }
    
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
    
            String opcion = JOptionPane.showInputDialog("¿Desea buscar otro producto? (S/N):");
            if (opcion.equalsIgnoreCase("N")) {
                continuar = false;
            }
        }
    }
    
    public void MostrarPila() {
        if (pila.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Lista de Productos:\n");
        for (Productos o : pila) {
            mensaje.append("Marca: ").append(o.getMarca())
                   .append("\nReferencia: ").append(o.getReferencia())
                   .append("\nCantidad: ").append(o.getCantidad())
                   .append("\nPrecio: ").append(o.getPrecio())
                   .append("\n----------------------\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    public void EliminarProducto() {
        if (pila.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista.");
            return;
        }

        String referencia = JOptionPane.showInputDialog("Ingrese la referencia del producto a eliminar:");
        Stack<Productos> tempStack = new Stack<>();
        boolean encontrado = false;

        while (!pila.isEmpty()) {
            Productos producto = pila.pop();
            if (producto.getReferencia().equalsIgnoreCase(referencia)) {
                encontrado = true;
                JOptionPane.showMessageDialog(null, "Producto eliminado: " + producto.getReferencia());
                break;
            } else {
                tempStack.push(producto);
            }
        }

        while (!tempStack.isEmpty()) {
            pila.push(tempStack.pop());
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }
}
