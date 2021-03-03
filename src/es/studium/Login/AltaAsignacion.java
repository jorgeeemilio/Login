package es.studium.Login;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AltaAsignacion implements ActionListener, WindowListener
{
	Frame frmAltaAsignacionPaso1 = new Frame("Alta Asignación (1 de 2)");
	Frame frmAltaAsignacionPaso2 = new Frame("Alta Asignación (2 de 2)");
	Label lblElegirProyecto = new Label("Elegir Proyecto:");
	Label lblElegirEmpleado = new Label("Elegir Empleado:");
	Label lblNombreProyecto = new Label("XXXXXXXXXXXXXXXX");
	Choice choProyectos = new Choice();
	Choice choEmpleados = new Choice();
	Button btnSiguiente = new Button("Siguiente");
	Button btnAnadir = new Button("Añadir");
	TextArea txaListado = new TextArea(4,20);

	Dialog dlgMensaje = new Dialog(frmAltaAsignacionPaso2, "Confirmación", true);
	Label lblMensaje = new Label("Asignación Correcta");

	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	int idProyectoFK;

	public AltaAsignacion()
	{
		frmAltaAsignacionPaso1.setLayout(new FlowLayout());
		frmAltaAsignacionPaso1.add(lblElegirProyecto);

		// Rellenar el Choice
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM proyectos
		sentencia = "SELECT * FROM proyectos";
		try
		{
			//Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			choProyectos.removeAll();
			choProyectos.add("Seleccionar un proyecto");
			while(rs.next())
			{
				choProyectos.add(rs.getInt("idProyecto")
						+"-"+rs.getString("nombreProyecto")
						+"-"+rs.getString("fechaInicioProyecto")
						+"-"+rs.getString("fechaFinProyecto")
						+"-"+rs.getString("idClienteFK"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println(sqle.getMessage());
		}
		frmAltaAsignacionPaso1.add(choProyectos);
		btnAnadir.addActionListener(this);
		btnSiguiente.addActionListener(this);
		frmAltaAsignacionPaso1.add(btnSiguiente);
		frmAltaAsignacionPaso1.setSize(300,140);
		frmAltaAsignacionPaso1.setResizable(false);
		frmAltaAsignacionPaso1.setLocationRelativeTo(null);
		frmAltaAsignacionPaso1.addWindowListener(this);
		frmAltaAsignacionPaso1.setVisible(true);
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
		if(frmAltaAsignacionPaso1.isActive())
		{
			frmAltaAsignacionPaso1.setVisible(false);
		}
		else
		{
			frmAltaAsignacionPaso2.setVisible(false);
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
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(btnSiguiente))
		{
			// TODO Auto-generated method stub
			lblNombreProyecto.setText(choProyectos.getSelectedItem().split("-")[1]);
			idProyectoFK = Integer.parseInt(choProyectos.getSelectedItem().split("-")[0]);
			frmAltaAsignacionPaso2.setLayout(new FlowLayout());
			frmAltaAsignacionPaso2.add(lblNombreProyecto);
			frmAltaAsignacionPaso2.add(lblElegirEmpleado);

			// Rellenar el Choice
			// Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			// Hacer un SELECT * FROM empleados
			sentencia = "SELECT * FROM empleados";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs = statement.executeQuery(sentencia);
				choEmpleados.removeAll();
				choEmpleados.add("Seleccionar un empleado");
				while(rs.next())
				{
					choEmpleados.add(rs.getInt("idEmpleado")
							+"-"+rs.getString("nombreEmpleado")
							+"-"+rs.getString("apellidosEmpleado")
							+"-"+rs.getString("cargoEmpleado"));
				}
			}
			catch (SQLException sqle)
			{
				System.out.println(sqle.getMessage());
			}
			frmAltaAsignacionPaso2.add(choEmpleados);
			frmAltaAsignacionPaso2.add(btnAnadir);
			// Hacer un SELECT * FROM empleados
			sentencia = "SELECT * FROM asignaciones WHERE idProyectoFK = "+idProyectoFK;
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs = statement.executeQuery(sentencia);
				txaListado.selectAll();
				txaListado.setText("");
				while(rs.next())
				{
					txaListado.append(rs.getInt("idAsignacion")
							+"-"+rs.getString("idEmpleadoFK")
							+"-"+rs.getString("idProyectoFK"));
				}
			}
			catch (SQLException sqle)
			{
				System.out.println(sqle.getMessage());
			}

			frmAltaAsignacionPaso2.add(txaListado);

			frmAltaAsignacionPaso2.setSize(300,140);
			frmAltaAsignacionPaso2.setResizable(false);
			frmAltaAsignacionPaso2.setLocationRelativeTo(null);
			frmAltaAsignacionPaso2.addWindowListener(this);
			frmAltaAsignacionPaso2.setVisible(true);
		}
		else
		{
			// Botón Añadir
			bd = new BaseDatos();
			connection = bd.conectar();
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				sentencia = "INSERT INTO asignaciones VALUES (null, "
						+ choEmpleados.getSelectedItem().split("-")[0]
						+ ", " +idProyectoFK + ")";
				statement.executeUpdate(sentencia);
				lblMensaje.setText("Asignación Correcta");
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Asignación");
			}
			finally
			{
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.setSize(150,100);
				dlgMensaje.setResizable(false);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.add(lblMensaje);
				dlgMensaje.setVisible(true);
				bd.desconectar(connection);
			}
		}
	}
}
