package es.studium.Login;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaProyecto implements ActionListener, WindowListener
{
	// Ventana Consulta de Proyectos
	Frame frmConsultaProyectos = new Frame("Consulta Proyectos");
	TextArea listadoProyectos = new TextArea(4, 40);
	Button btnPdfProyectos = new Button("PDF");
	
	BaseDatos bd;
	String sentencia = "";
	String subSentencia = "";
	Connection connection = null;
	Statement statement = null;
	Statement statementClientes = null;
	ResultSet rs = null;
	ResultSet rsClientes = null;
	
	public ConsultaProyecto()
	{
		frmConsultaProyectos.setLayout(new FlowLayout());
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM Proyectos
		sentencia = "SELECT * FROM proyectos";
		// La información está en ResultSet
		// Recorrer el RS y por cada registro,
		// meter una línea en el TextArea
		try
		{
			//Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statementClientes = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			listadoProyectos.selectAll();
			listadoProyectos.setText("");
			listadoProyectos.append("id\tNombre\tInicio\tFin\tCliente\n");
			while(rs.next())
			{
				subSentencia = "SELECT * FROM clientes WHERE idCliente="
			+ rs.getString("idClienteFK");
				rsClientes = statementClientes.executeQuery(subSentencia);
				rsClientes.next();
				String[] fechaInicioAmericana = rs.getString("fechaInicioProyecto").split("-");
				String[] fechaFinAmericana = rs.getString("fechaFinProyecto").split("-");
				listadoProyectos.append(rs.getInt("idProyecto")
						+"\t"+rs.getString("nombreProyecto")
						+"\t"+fechaInicioAmericana[2]+"/"+fechaInicioAmericana[1]+"/"+fechaInicioAmericana[0]
						+"\t"+fechaFinAmericana[2]+"/"+fechaFinAmericana[1]+"/"+fechaFinAmericana[0]
						+"\t"+rsClientes.getString("nombreCliente")
						+"\t"+rsClientes.getString("cifCliente")
						+"\n");
			}
		}
		catch (SQLException sqle)
		{
			listadoProyectos.setText("Se ha producido un error en la consulta");
		}
		finally
		{

		}
		listadoProyectos.setEditable(false);
		frmConsultaProyectos.add(listadoProyectos);
		frmConsultaProyectos.add(btnPdfProyectos);

		frmConsultaProyectos.setSize(300,140);
		frmConsultaProyectos.setResizable(false);
		frmConsultaProyectos.setLocationRelativeTo(null);
		frmConsultaProyectos.addWindowListener(this);
		frmConsultaProyectos.setVisible(true);
	}
	@Override
	public void windowActivated(WindowEvent e)
	{}
	@Override
	public void windowClosed(WindowEvent e)
	{}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(frmConsultaProyectos.isActive())
		{
			frmConsultaProyectos.setVisible(false);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
