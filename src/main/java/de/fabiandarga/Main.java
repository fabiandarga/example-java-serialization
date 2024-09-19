package de.fabiandarga;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/school";
        String user = "school";
        String password = "123456";

        // create some object
        Student fabian = new Student( 1, "Fabian", "Darga", 100);

        // serialize it
        try {
            DatabaseUtils databaseUtils = new DatabaseUtils(url, user, password);
/*
            byte[] studentAsBytes = SerializationUtils.serialize(fabian);

            // save it database
            databaseUtils.saveStudentData(fabian.getId(), studentAsBytes);

            System.out.println("Student saved");
*/

            // read from db
            byte[] studentDataFromDB = databaseUtils.getStudentDataById(1);

            // deserialize
            Student studentFromDB = SerializationUtils.deserialize(studentDataFromDB);

            // output
            System.out.println("Student object recreated: " + studentFromDB.getFirstName() + " " + studentFromDB.getLastName());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}