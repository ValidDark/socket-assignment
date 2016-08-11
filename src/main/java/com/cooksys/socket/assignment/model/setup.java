package com.cooksys.socket.assignment.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class setup
{
	
    public static JAXBContext createJAXBContext() throws JAXBException {
    	JAXBContext jaxb = JAXBContext.newInstance( "com.cooksys.socket.assignment.model" );
        return jaxb;
    }
	
	
	
public static void main(String[] args) throws JAXBException
{
	Config con = new Config();
	LocalConfig lCon = new LocalConfig();
	RemoteConfig rCon = new RemoteConfig();
	Student stu = new Student();
	
	stu.setFavoriteIDE("Code::Blocks");
	stu.setFavoriteLanguage("C++");
	stu.setFavoriteParadigm("Object Oriented");
	stu.setFirstName("David");
	stu.setLastName("Karl");
	
	lCon.setPort(12345);
	rCon.setHost("localhost");
	rCon.setPort(12345);
	con.setLocal(lCon);
	con.setRemote(rCon);
	con.setStudentFilePath("config/student.xml");
	con.setStudentsFilePath("students/");
	
	createJAXBContext().createMarshaller().marshal(con, new File("config/config.xml"));
	createJAXBContext().createMarshaller().marshal(stu, new File("config/student.xml"));
}
}
