package euclideandistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EuclideanDistance {

    public static void main(String[] args) {
        
        //Matrix is 91 because first row is not readable
        double[][] cellData = new double[91][7];
        double[][] result = new double[91][91];

        //object of the class
        EuclideanDistance rc = new EuclideanDistance();

        //Reading the value of n row and n column     
        for (int i = 1; i <= 90; i++) {
            for (int j = 0; j <= 6; j++) {
                cellData[i][j] = rc.ReadCellData(i, j);
            }
        }

        //Temporary variables to calculate Euclidean Distance
        double[] tempArray1 = new double[7];
        double[] tempArray2 = new double[7];

        //Store temp array 1
        for (int i = 1; i <= 90; i++) {
            for (int j = 1; j <= 90; j++) {
                for (int k = 0; k <= 6; k++) {
                    tempArray1[k] = cellData[i][k];
                    tempArray2[k] = cellData[j][k];
                }
                result[i][j] = calculateDistance(tempArray1, tempArray2);
            }
        }

        //Printing result
        printResult(result);

    }

    //Euclidean Distance Calcualation Method
    public static double calculateDistance(double[] array1, double[] array2) {
        double Sum = 0.0;
        for (int i = 0; i < array1.length; i++) {
            Sum = Sum + Math.pow((array1[i] - array2[i]), 2.0);
        }
        return Math.sqrt(Sum);
    }
    
    //Reads Data From Individual Cell
    public double ReadCellData(int vRow, int vCol) {
        //Initialize return variable
        double value = .0;

        //Initialize workbook object
        Workbook wb = null;

        try {

            //Read data from the excel file
            FileInputStream input = new FileInputStream("src\\Cryotherapy.xlsx");

            //Assign the input file to workbook
            wb = new XSSFWorkbook(input);
            //Catch exception
        } catch (FileNotFoundException e) {
        } catch (IOException io) {
        }

        //Gets sheet of the excel file
        Sheet sheet = wb.getSheetAt(0);
        //Get row from the execl file
        Row row = sheet.getRow(vRow);
        //Get cell from the previous row
        Cell cell = row.getCell(vCol);

        //Reads string from cell and parses to double and stores in value
        value = cell.getNumericCellValue();

        return value;
    }

    //Print Result
    public static void printResult(double[][] array) {
        for (int i = 1; i <= 90; i++) {
            for (int j = 1; j <= 90; j++) {
                System.out.printf("%3.0f | ", array[i][j]);
//                System.out.printf("%.2f \t", array[i][j]);
            }
            System.out.println();
        }
    }
    
//Class ends here
}