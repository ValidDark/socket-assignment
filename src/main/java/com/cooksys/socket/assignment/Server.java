package com.cooksys.socket.assignment;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Server extends Utils
{

	/**
	 * Reads a {@link Student} object from the given file path
	 *
	 * @param studentFilePath
	 *            the
	 * @param jaxb
	 * @return
	 * @throws JAXBException
	 */
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException
	{
		Student stu = (Student) jaxb.createUnmarshaller().unmarshal(new File(studentFilePath));
		return stu;
	}

	public static void main(String[] args) throws JAXBException
	{
		Config serverConfig = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());

		try(ServerSocket ss = new ServerSocket(serverConfig.getLocal().getPort());)
		{
			while(true)
			{

			try (
				Socket s = ss.accept();
				OutputStream out = s.getOutputStream();
				)
			{
				
				Utils.createJAXBContext().createMarshaller().marshal(loadStudent(serverConfig.getStudentFilePath(), Utils.createJAXBContext()),
						out);

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			}
			
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // get port / setup ServerSocket

		

	}
}
