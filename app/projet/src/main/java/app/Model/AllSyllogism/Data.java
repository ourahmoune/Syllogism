package app.Model.AllSyllogism;
import app.Model.Figure;
import app.Model.Proposition;
import app.Model.Syllogism;
import app.Model.Type;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    final private String PathFile;
    final private List<SyllogismAndRules> AllSyllogismAndRules = new ArrayList<>();
    public Data(String PF) {
        this.PathFile = PF;
    }
    private Type MySwitchCase(String str) {
        return switch (str) {
            case "A" -> Type.A;
            case "I" -> Type.I;
            case "O" -> Type.O;
            case "E" -> Type.E;
            default -> throw new UnsupportedOperationException(str + " : Type not supported");
        };
    }
    private Figure MySwitchCase(int figure) {
        return switch (figure) {
            case 1 -> Figure.UN;
            case 2 -> Figure.DEUX;
            case 3 -> Figure.TROIS;
            case 4 -> Figure.QUATRE;
            default -> throw new UnsupportedOperationException(figure + " : Figure not supported");
        };
    }
    public void load() {
        try {
            // Load the Excel file
            System.out.println("file path is: " + PathFile);
            FileInputStream MyFile = new FileInputStream(PathFile);
            // Create an instance of XSSFWorkbook to read the .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(MyFile);
            // Select the first sheet (index 0)
            XSSFSheet MySheet = workbook.getSheetAt(0);

            int line = 1;
            Type type = null;
            boolean ResultOfRule = false;
            Figure figure = null;
            int cell;

            // Iterate through all the rows in the sheet
            for (Row row : MySheet) {
                Map<Integer, Proposition> PropositionMap = new HashMap<>();
                SyllogismAndRules syllogismandrules = new SyllogismAndRules();
                System.out.print(" " + line + " ");
                line++;
                cell = 0;

                // Iterate through all the cells in each row (i.e., the columns)
                for (Cell cellular : row) {
                    cell++;
                    // Check the cell type and retrieve its value
                    switch (cellular.getCellType()) {
                        case STRING:
                            //System.out.print(cellular.getStringCellValue() + "\t");
                            type = MySwitchCase(cellular.getStringCellValue());
                            break;
                        case BOOLEAN:
                            //System.out.print(cellular.getBooleanCellValue() + "\t");
                            ResultOfRule = cellular.getBooleanCellValue();
                            break;
                        case NUMERIC:
                            // Check if it's a date or a number
                            //System.out.print(" " + (int) cellular.getNumericCellValue() + " ");
                            figure = MySwitchCase((int) cellular.getNumericCellValue());
                            break;
                        default:
                            System.out.print("Unknown type\t");
                            break;
                    }
                    if (cell == 1) {// First Proposition
                        PropositionMap.put(1, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 2) {// Second Proposition
                        PropositionMap.put(2, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 3) {// Third Proposition
                        PropositionMap.put(3, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 4) {// Creating Syllogism
                        syllogismandrules.setSyllogism(new Syllogism(figure, PropositionMap));
                        System.out.print(figure + " ");
                    }
                    if ((cell >= 5) && (cell <= 14)) {// Get the Result of rules
                        syllogismandrules.setRules(ResultOfRule);
                        System.out.print(ResultOfRule + " ");
                    }
                    if (cell == 15) { //get the last result and putin the syllogismandrules in the List
                        syllogismandrules.setRules(ResultOfRule);
                        System.out.print(ResultOfRule + " ");
                        AllSyllogismAndRules.add(syllogismandrules);
                    }
                }
                // Move to the next line after displaying all columns of the current row
                System.out.println();
            }

            // Close the file after reading
            workbook.close();
            MyFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SyllogismAndRules> getAllSyllogismAndRules() {
        return AllSyllogismAndRules;
    }
}
