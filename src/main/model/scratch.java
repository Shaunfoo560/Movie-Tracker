package model;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class scratch {

    // View applicant table
    public ArrayList<String[]> applicantTable() {
        ArrayList<String[]> res = new ArrayList<>();
        try {
            String query = "SELECT * FROM Applicant";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String temp1 = String.valueOf(rs.getInt("ApplicantID"));
                String temp2 = rs.getString("firstName");
                String temp3 = String.valueOf(rs.getInt("lastName"));
                String temp4 = String.valueOf(rs.getInt("applicantEmail"));
                String temp5 = String.valueOf(rs.getInt("applicantSchool"));
                String temp6 = String.valueOf(rs.getFloat("applicantGPA"));
                String[] temp7 = new String[6];
                temp7[0] = temp1;
                temp7[1] = temp2;
                temp7[2] = temp3;
                temp7[3] = temp4;
                temp7[4] = temp5;
                temp7[5] = temp6;
                res.add(temp7);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return res;

    }

    // DANGEROUS FUNCTION FROM CAR GUY
    private void showTable(ArrayList<String[]> table, JScrollPane scrollPane) {
        String[] columnNames = new String[table.get(0).length];
        String[][] data = new String[table.size() - 1][table.get(0).length];
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(0).length; j++) {
                if (i == 0) {
                    columnNames[j] = table.get(0)[j];
                } else {
                    data[i - 1][j] = table.get(i)[j];
                }
            }
        }
        // Initializing the JTable
        JTable jTable;
        jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        scrollPane.setViewportView(jTable);
    }
}
