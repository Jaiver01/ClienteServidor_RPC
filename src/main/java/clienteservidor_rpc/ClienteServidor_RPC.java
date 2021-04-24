package clienteservidor_rpc;

import org.apache.xmlrpc.XmlRpcClient;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ClienteServidor_RPC {
    public static void main(String args[]) {        
        Object resultado;
        
        try {
            XmlRpcClient client = new XmlRpcClient("http://192.168.100.67:8080");
            
            //Vector<String> parametros = new Vector<String>();
            Vector<Integer> parametros = new Vector<>();
            String method = "", sign = "";
            
            JOptionPane.showMessageDialog(null, "Cliente se ha conectado...");
            
            while(true) {
                String menu = JOptionPane.showInputDialog(null, "Calculadora\n1. Sumar\n2. Restar\n3. Multiplicar\n4. Dividir\n5. Salir\n");
                
                switch(menu) {
                    case "1":                        
                        method = "suma.sumar";
                        sign = "+";
                        break;
                    
                    case "2":
                        method = "resta.restar";
                        sign = "-";
                        break;
                        
                    case "3":
                        method = "multiplicacion.multiplicar";
                        sign = "*";
                        break;
                        
                    case "4":
                        method = "division.dividir";
                        sign = "/";
                        break;
                        
                    case "5":
                        JOptionPane.showMessageDialog(null, "Saliendo...");
                        System.exit(0);
                        break;
                        
                   default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                        continue;
                }
                
                parametros = getParameters();
                if (parametros.isEmpty()) continue;
                
                resultado = client.execute("servidorRPC." + method, parametros);
                JOptionPane.showMessageDialog(null, parametros.get(0) + " " + sign + " " + parametros.get(1) + " = " + resultado);
                
                parametros.clear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en cliente " + e.getMessage());
        }
    }
    
    private static Vector<Integer> getParameters() {
        Vector<Integer> parametros = new Vector<>();
        int n1 = 0, n2 = 0;
        
        try {
            n1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Primer número"));
            n2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Segundo número"));
            
            parametros.addElement(n1);
            parametros.addElement(n2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Parámetros no válidos");
        }

        return parametros;
    }
}