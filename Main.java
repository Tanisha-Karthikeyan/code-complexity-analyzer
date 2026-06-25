package analyzer;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Main {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();

            System.out.println("Selected File: " + selectedFile.getName());

            try {

            	int classCount = 0;

            	int methodCount = 0;
            	
            	int loopCount = 0;
            	
            	int nestedLoopCount = 0;
            	int currentLoopDepth = 0;
            	
            	Scanner scanner = new Scanner(selectedFile);

            	while(scanner.hasNextLine()) {

            	    String line = scanner.nextLine();
            	    line = line.trim();

            	    if(line.contains("class ")) {

            	        classCount++;

            	    }
            	    if((line.contains("public") ||
            	    	    line.contains("private") ||
            	    	    line.contains("protected"))
            	    	    &&
            	    	    line.contains("(")
            	    	    &&
            	    	    line.contains(")")
            	    	    &&
            	    	    line.contains("{")) {

            	    	    methodCount++;

            	    	}

            	    if(line.contains("for(") ||
            	    		   line.contains("for (") ||
            	    		   line.contains("while(") ||
            	    		   line.contains("while (")) {

            	    		    loopCount++;

            	    		    currentLoopDepth++;

            	    		    if(currentLoopDepth > 1) {

            	    		        nestedLoopCount++;

            	    		    }
            	    		}
            	    if(line.equals("}")) {

            	        if(currentLoopDepth > 0) {

            	            currentLoopDepth--;

            	        }
            	    }
            	}

            	scanner.close();

            	System.out.println("Classes Found: " + classCount);

            	System.out.println("Methods Found: " + methodCount);
            	
            	System.out.println("Loops Found: " + loopCount);
            	
            	System.out.println("Nested Loops Found: " + nestedLoopCount);
            	String complexity;

            	if(nestedLoopCount >= 2) {

            	    complexity = "O(n³)";

            	}
            	else if(nestedLoopCount == 1) {

            	    complexity = "O(n²)";

            	}
            	else if(loopCount == 1) {

            	    complexity = "O(n)";

            	}
            	else {

            	    complexity = "O(1)";

            	}

            	System.out.println("Estimated Complexity: " + complexity);
            	int qualityScore = 100;

            	qualityScore -= nestedLoopCount * 10;

            	if(methodCount > 10) {

            	    qualityScore -= 10;

            	}

            	if(loopCount > 5) {

            	    qualityScore -= 5;

            	}

            	if(qualityScore < 0) {

            	    qualityScore = 0;

            	}

            	System.out.println("Code Quality Score: " + qualityScore + "/100");
            	System.out.println("\nSuggestions:");

            	if(nestedLoopCount > 0) {

            	    System.out.println("- Reduce nested loops to improve performance.");

            	}

            	if(loopCount > 5) {

            	    System.out.println("- Consider simplifying repetitive loops.");

            	}

            	if(methodCount > 10) {

            	    System.out.println("- Split large classes into smaller modules.");

            	}

            	if(nestedLoopCount == 0 &&
            	   loopCount <= 5 &&
            	   methodCount <= 10) {

            	    System.out.println("- Code structure looks good.");

            	}
            	
            } catch(Exception e) {

                System.out.println("Error reading file");

            }

        } else {

            System.out.println("No file selected.");

        }
    }
}