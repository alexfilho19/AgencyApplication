package sample;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Class used to record the details of the  jobs and Applicants
 * @author Alexandro Cipriano da Silva Filho
 */
   public class JobFileHandler {

        //Method for writing the file
        public static void writeJobList(Job_List jobList) {

                //Usage of the try-with-resources to close the file safely
                try (FileWriter jobFile = new FileWriter("Jobs.txt");
                     PrintWriter jobWriter = new PrintWriter(jobFile)) {

                        //To change the salary format
                        DecimalFormat decimalFormat = new DecimalFormat("###,##0.0#");

                        //Write each element of the list to the file
                        for (Job item : jobList.jList) {

                                jobWriter.println(item.getJobId());
                                jobWriter.println(item.getJobTitle());
                                jobWriter.println(item.getLocation());
                                jobWriter.println(item.getType());
                                jobWriter.println(item.getPrimarySkill());
                                jobWriter.println(decimalFormat.format(item.getSalary()));
                                jobWriter.println(item.getExperience());

                        }
                        //Handle the exception thrown by the FileWriter methods
                } catch (IOException e) {
                        System.out.println("There is a problem with the file!");
                }
        }

        //Method for reading the Job file
        public static void readJobList(Job_List jobList) {
                String ID, jobName, location, description, primarySkill, strSalary, strExperience;
                int experience;
                double salary;

                //Usage of the try-with-resources to close the file safely
                try (FileReader jobFile = new FileReader("Jobs.txt");
                     BufferedReader jobStream = new BufferedReader(jobFile)
                ) {
                        ID = jobStream.readLine(); //To read the first line of the file
                        while (ID != null) {

                                //Read the remaining of the first record, then all the rest of records until the end of the file
                                jobName = jobStream.readLine();
                                location = jobStream.readLine();
                                description = jobStream.readLine();
                                primarySkill = jobStream.readLine();
                                strSalary = jobStream.readLine();

                                //Convert the salary from String to Double
                                salary = Double.parseDouble(strSalary);
                                strExperience = jobStream.readLine();

                                //Convert Experience from String to Integer
                                experience = Integer.parseInt(strExperience);
                                Job myJob = new Job(ID, jobName, location, description, primarySkill, salary, experience);
                                jobList.addJob(myJob);
                                ID = jobStream.readLine();
                        }
                }
                //Handle the exception if the file is not found
                catch (FileNotFoundException e) {
                        System.out.println("Jobs.txt not found!");
                } catch (NumberFormatException e) {
                        System.out.print("");
                }
                //Handle the exception thrown by the FileReader methods
                catch (IOException e) {
                        System.out.println("Problem with the file\n");
                }
        }

}