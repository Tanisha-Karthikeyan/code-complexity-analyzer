package analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CodeAnalyzer {

    public static String analyze(File selectedFile) {

        try {

            int classCount = 0;
            int methodCount = 0;
            int loopCount = 0;
            int nestedLoopCount = 0;
            int currentLoopDepth = 0;

            int cyclomaticComplexity = 1;

            int logarithmicLoops = 0;

            int recursiveMethods = 0;

            ArrayList<String> methodNames =
                    new ArrayList<>();

            ArrayList<String> allLines =
                    new ArrayList<>();

            Scanner scanner =
                    new Scanner(selectedFile);

            while (scanner.hasNextLine()) {

                String line =
                        scanner.nextLine().trim();

                allLines.add(line);

                // Class Detection

                if (line.contains("class ")) {

                    classCount++;
                }

                // Method Detection

                if ((line.contains("public")
                        || line.contains("private")
                        || line.contains("protected"))
                        && line.contains("(")
                        && line.contains(")")
                        && line.contains("{")) {

                    methodCount++;

                    String[] words =
                            line.split("\\(");

                    String methodPart =
                            words[0];

                    String[] tokens =
                            methodPart.split(" ");

                    String methodName =
                            tokens[tokens.length - 1];

                    methodNames.add(methodName);
                }

                // Cyclomatic Complexity

                if (line.contains("if(")
                        || line.contains("if (")
                        || line.contains("else if")
                        || line.contains("for(")
                        || line.contains("for (")
                        || line.contains("while(")
                        || line.contains("while (")
                        || line.contains("case ")
                        || line.contains("catch(")
                        || line.contains("&&")
                        || line.contains("||")) {

                    cyclomaticComplexity++;
                }

                // Loop Detection

                if (line.contains("for(")
                        || line.contains("for (")
                        || line.contains("while(")
                        || line.contains("while (")) {

                    loopCount++;

                    currentLoopDepth++;

                    if (currentLoopDepth > 1) {

                        nestedLoopCount++;
                    }

                    // Logarithmic Loop Detection

                    if (line.contains("/=2")
                            || line.contains("/= 2")
                            || line.contains("*=2")
                            || line.contains("*= 2")
                            || line.contains(">>")) {

                        logarithmicLoops++;
                    }
                }

                if (line.equals("}")) {

                    if (currentLoopDepth > 0) {

                        currentLoopDepth--;
                    }
                }
            }

            scanner.close();

            // Recursion Detection

            for (String method : methodNames) {

                for (String line : allLines) {

                    if (line.contains(method + "(")
                            && !line.contains("public")
                            && !line.contains("private")
                            && !line.contains("protected")) {

                        recursiveMethods++;
                        break;
                    }
                }
            }

            // Complexity Estimation

            String complexity;

            if (nestedLoopCount >= 2) {

                complexity = "O(n³)";
            }

            else if (nestedLoopCount == 1
                    && logarithmicLoops > 0) {

                complexity = "O(n log n)";
            }

            else if (nestedLoopCount == 1) {

                complexity = "O(n²)";
            }

            else if (logarithmicLoops > 0) {

                complexity = "O(log n)";
            }

            else if (loopCount == 1) {

                complexity = "O(n)";
            }

            else {

                complexity = "O(1)";
            }

            // Quality Score

            int qualityScore = 100;

            qualityScore -= nestedLoopCount * 10;

            if (methodCount > 10) {

                qualityScore -= 10;
            }

            if (loopCount > 5) {

                qualityScore -= 5;
            }

            if (cyclomaticComplexity > 10) {

                qualityScore -= 10;
            }

            if (recursiveMethods > 0) {

                qualityScore -= 5;
            }

            if (qualityScore < 0) {

                qualityScore = 0;
            }

            return classCount + ","
                    + methodCount + ","
                    + loopCount + ","
                    + nestedLoopCount + ","
                    + complexity + ","
                    + qualityScore + ","
                    + cyclomaticComplexity + ","
                    + recursiveMethods;

        }

        catch (Exception e) {

            return "ERROR";
        }
    }
}