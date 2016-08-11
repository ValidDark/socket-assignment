package com.cooksys.socket.assignment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Client
{
	
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException
	{
		Student stu = (Student) jaxb.createUnmarshaller().unmarshal(new File(studentFilePath));
		return stu;
	}
	

	public static void main(String[] args) throws JAXBException
	{
		Config clientConfig = Utils.loadConfig("config/config.xml", Utils.createJAXBContext());
		
		try (
				Socket s = new Socket(clientConfig.getRemote().getHost(), clientConfig.getRemote().getPort());
				InputStream in = s.getInputStream();
			)
		{
			Student stu = (Student) Utils.createJAXBContext().createUnmarshaller().unmarshal(in);
			
			File dir = new File(clientConfig.getStudentsFilePath());
			if(!dir.exists())
				dir.mkdir();
			
			Utils.createJAXBContext().createMarshaller().marshal(stu, new File (clientConfig.getStudentsFilePath() + stu.getFirstName()+".xml"));
			System.out.println(stu.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private static void MakeDir()
	{
		// TODO Auto-generated method stub
		
	}
}
