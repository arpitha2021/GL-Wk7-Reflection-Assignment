package com.greatlearning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionApiDemo {

	public static void main(String[] args) {
		int option = 0;
		Class[] classes = null;
		Method[] methods = null;
		System.out.println("Please enter the Predefined Class name");
		Scanner scan = new Scanner(System.in);
		String className = scan.next();
		StringBuffer buffer = new StringBuffer();
		try {
			Class classToInspect = Class.forName(className).getClass();
			if (classToInspect != null) {
				System.out.println("Its a Prdefined Class " + classToInspect.getName());
				System.out.println("Do you want to explore and inspect the Class then please enter true ");
			} else {
				System.out.println("Its not a Predefined Class or its incorrect fully qualified name of the class");
				System.out.println("Please enter the Fully Qualified Name of the Predifined Class in java");
				className = scan.next();
				classToInspect = Class.forName(className).getClass();
			}
			while (true) {
				switch (scan.next()) {
				case "true":
					System.out.println("Select the menu option");
					System.out.println(
							"1. Methods\n 2.Classes\n 3.SubClasses\n 4. ParentClasses\n 5.Constructors\n 6.Data Members");
					option = scan.nextInt();

					switch (option) {
					case 1:
						System.out.println("The List of Methods are :");
						methods = classToInspect.getName().getClass().getMethods();
						buffer.append("The List of Methods are :");
						buffer.append(System.getProperty("line.separator"));
						for (Method m : methods) {
							System.out.println("\t" + m);
							buffer.append(m);
							buffer.append(System.getProperty("line.separator"));
						}
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						break;
					case 2:
						System.out.println("The List of Classes are :");
						buffer.append("The List of Classes are :");
						buffer.append(System.getProperty("line.separator"));
						classes = classToInspect.getName().getClass().getDeclaredClasses();
						for (Class c : classes) {
							System.out.println("\t" + c);
							buffer.append(c.getName());
							buffer.append(System.getProperty("line.separator"));
						}
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						break;
					case 3:
						System.out.println("The List of Sub Classes are :");
						System.out.println("\t" + classToInspect.getName().getClass().getEnclosingClass());
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						break;
					case 4:
						System.out.println("The List of Parent Classes are :");
						buffer.append("The List of Parent Classes are :");
						buffer.append(System.getProperty("line.separator"));
						System.out.println("\t" + classToInspect.getName().getClass().getSuperclass());
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						buffer.append(classToInspect.getName().getClass().getSuperclass());
						buffer.append(System.getProperty("line.separator"));
						break;
					case 5:
						System.out.println("The List of Constructors are :");
						buffer.append("The List of Constructors are :");
						buffer.append(System.getProperty("line.separator"));
						Constructor[] constructors = classToInspect.getName().getClass().getConstructors();
						for (Constructor c : constructors) {
							System.out.println("\t" + c);
							buffer.append(c.getName());
							buffer.append(System.getProperty("line.separator"));
						}
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						break;
					case 6:
						System.out.println("The List of Data Members are :");
						buffer.append("The List of Data Members are :");
						buffer.append(System.getProperty("line.separator"));
						Field[] fields = classToInspect.getName().getClass().getDeclaredFields();
						for (Field c : fields) {
							System.out.println("\t" + c);
							buffer.append(c.getName());
							buffer.append(System.getProperty("line.separator"));
						}
						System.out.println(
								"Do you want to explore and inspect the Class then please enter true otherwise no");
						break;
					default:
						System.out.println("Invalid Option");
						break;
					}

					break;
				case "no":
					System.out.println("Please select below");
					System.out.println(
							"1: Store Information into File \n 2: To See all Previous files created\n 3: exit without saving");
					option = scan.nextInt();
					switch (option) {
					case 1:
						try {
							BufferedWriter bwr = new BufferedWriter(
									new FileWriter(new File(className + ".info")));

							// write contents of StringBuffer to a file
							bwr.write(buffer.toString());

							// flush the stream
							bwr.flush();

							// close the stream
							bwr.close();
							System.out.println(
									"Do you want to exit enter no");
							System.exit(0);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					case 2:
						System.out.println("All previous classes created are ");
						System.out.println(classToInspect.getName() + ".info");
						break;
					case 3:
						System.exit(0);
						break;

					default:
						break;
					}
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Incorrect Class Name " + e.getMessage());
			;
		}
	}

}
