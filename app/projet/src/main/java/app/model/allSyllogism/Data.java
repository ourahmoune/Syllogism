package app.model.allSyllogism;

import app.model.Figure;
import app.model.Proposition;
import app.model.Syllogism;
import app.model.Type;
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

/**
 * The Data class is responsible for loading syllogism data from an Excel file.
 * It stores the loaded syllogisms and their corresponding rules in a list.
 */
public class Data {
    final private String PathFile;
    final private List<PolySyllogismAndRules> AllSyllogismAndRules = new ArrayList<>();

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

    /**
     * Loads syllogism data from the specified Excel file.
     * Reads propositions, figures, and rules, and stores them in the AllSyllogismAndRules list.
     */
    public void load() {
        try {
            System.out.println("File path is: " + PathFile);
            FileInputStream MyFile = new FileInputStream(PathFile);
            XSSFWorkbook workbook = new XSSFWorkbook(MyFile);
            XSSFSheet MySheet = workbook.getSheetAt(0);


            int line = 1;
            Type type = null;
            boolean ResultOfRule = false;
            Figure figure = null;
            int cell;

            for (Row row : MySheet) {
                Map<Integer, Proposition> PropositionMap = new HashMap<>();
                PolySyllogismAndRules syllogismAndRules = new PolySyllogismAndRules();
                System.out.print(" " + line + " ");
                line++;
                cell = 0;

                for (Cell cellular : row) {
                    cell++;
                    switch (cellular.getCellType()) {
                        case STRING:
                            type = MySwitchCase(cellular.getStringCellValue());
                            break;
                        case BOOLEAN:
                            ResultOfRule = cellular.getBooleanCellValue();
                            break;
                        case NUMERIC:
                            figure = MySwitchCase((int) cellular.getNumericCellValue());
                            break;
                        default:
                            System.out.print("Unknown type\t");
                            break;
                    }

                    if (cell == 1) {
                        PropositionMap.put(1, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 2) {
                        PropositionMap.put(2, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 3) {
                        PropositionMap.put(3, new Proposition(type));
                        System.out.print(type + " ");
                    }
                    if (cell == 4) {
                        Syllogism syllogism = new Syllogism(figure, PropositionMap);
                        assert figure != null;
                        syllogism.setprposition(figure);
                        syllogismAndRules.setSyllogism(syllogism);
                        System.out.print(figure + " ");
                    }
                    if ((cell >= 5) && (cell <= 14)) {
                        syllogismAndRules.setRules(ResultOfRule);
                        System.out.print(ResultOfRule + " ");
                    }
                    if (cell == 15) {
                        syllogismAndRules.setRules(ResultOfRule);
                        System.out.print(ResultOfRule + " ");
                        AllSyllogismAndRules.add(syllogismAndRules);
                    }
                }
                System.out.println();
            }

            workbook.close();
            MyFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PolySyllogismAndRules> getAllSyllogismAndRules() {
        return AllSyllogismAndRules;
    }
}
