package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editar(req, resp);
                    break;
                case "eliminar":
                    this.eliminarCliente(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
                    break;
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertar(req, resp);
                    break;
                case "modificar":
                    this.modificarCliente(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
                    break;
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    private void insertar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        double saldo = 0;
        String saldoNew = req.getParameter("saldo");
        if (!saldoNew.trim().isEmpty()) {
            saldo = Double.parseDouble(saldoNew);
        }

        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, saldo);
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        System.out.println(idCliente);
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        System.out.println("cliente = " + cliente);
        req.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editar-cliente.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, resp);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos el objeto de cliente (modelo)
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        HttpSession session = req.getSession();
        session.setAttribute("clientes", clientes);
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //req.getRequestDispatcher("clientes.jsp").forward(req, resp);
        resp.sendRedirect("clientes.jsp");
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente :
                clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));


        //Creamos el objeto de cliente (modelo)
        Cliente cliente = new Cliente(idCliente);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}