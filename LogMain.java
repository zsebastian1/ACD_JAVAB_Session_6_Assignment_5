package AssignmentSession6;

import java.util.*;
import java.io.*;
	
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	class LogWriter {
		File f;
		BufferedWriter bw;
		Date timestamp = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");

		LogWriter() {
			f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession6\\LogWriterFile");

			if (f.exists()) {
				System.out.println("log_" + dateFormat.format(timestamp) + ".txt");
				File f2 = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession6\\MoreLogs" + dateFormat.format(timestamp) + ".txt");
				f.renameTo(f2);

			}

			try {
				f.createNewFile();
				System.out.println("The file has been created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void writeToFile(String text) throws FileTooLargeException, ErrorWritingToFile {
			if (f.length() >= 5242880)
				throw new FileTooLargeException("This file is too large to write to");
			else
				try {
					bw = new BufferedWriter(new FileWriter(f, true));
					bw.write(dateFormat.format(timestamp) + " : NO TYPE : " + text);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		void writeToFile(String textType, String text) throws FileTooLargeException, ErrorWritingToFile {
			if (f.length() > 5242880)
				throw new FileTooLargeException("This file is also too large to write to");
			else
				try {
					bw = new BufferedWriter(new FileWriter(f, true));
					bw.write(dateFormat.format(timestamp) + " : " + textType + " : " + text);
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	class FileTooLargeException extends IOException {
	
		private static final long serialVersionUID = 1L;

		public FileTooLargeException(String msg) {
			super(msg);
		}

	}

	class ErrorWritingToFile extends Exception {
		
		private static final long serialVersionUID = 1L;

		public ErrorWritingToFile(String msg) {
			super(msg);
		}

	}

	public class LogMain {

		public static void main(String[] args) {
			LogWriter lw = new LogWriter();
			String n;
			for (int i = 0; i < 20; i++) {
				n = String.valueOf(Math.random());
				try {
					lw.writeToFile("Error", n + "\n");
				} catch (FileTooLargeException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (ErrorWritingToFile e) {
					System.out.println("Expect an error here");
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 200000; i++) {
				n = String.valueOf(Math.random());
				try {
					lw.writeToFile(n + "\n");
				} catch (FileTooLargeException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (ErrorWritingToFile e) {
					System.out.println("Expect an error here");
					e.printStackTrace();
				}
			}
			System.out.println("We're finished here");

		}

	}
