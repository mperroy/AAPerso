package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.DominatingSetSolverInterface;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.DominatingSetExtrator.RecursiveDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
    	double time = System.currentTimeMillis();
    	// Exact or Recursive Method
    	int method = 0;
    	if(args.length > 0 && (args[0].equals("0") || args[0].equals("1")))
    		method = Integer.parseInt(args[0]);
    	//mode : (0 = brute-force algorithm/ 1 = greedy algorithm)
    	int problemType = 0;
    	if(args.length > 1 && (args[1].equals("0") || args[1].equals("1")))
    		problemType = Integer.parseInt(args[1]);
    	//try to find vertex cover with k number of vertex
        int k = 3;
    	if(args.length > 2 && Integer.parseInt(args[2]) > 0)
    		k = Integer.parseInt(args[2]);
    	
    	//Reading data from problem jsonfile
        InputJSON input = InputJSON.getInstance("../data_json/problem/basic_problem_2.json");

        //Create graph with the json data and mode (0 = brute-force algorithm/ 1 = greedy algorithm)
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input, problemType);

        //Initialize dominant solver
        DominatingSetSolverInterface dss;
        if(method == 0)
        	dss = new ExactDominantSetSolver(g);
        else
        	dss = new RecursiveDominantSetSolver(g);
        
        //check if graph contains dominatingSet
        boolean result = dss.hasDominatingSet(k);
        if(!result)
            System.out.println("No solution find for this position step");
        else {
            System.out.println("A solution was found in : " + (System.currentTimeMillis() - time) + "ms");
        }
        
    	//Write in out.json file position of defenders of dominantSet
        if(result)
        	OutputJSON.writeToJSON(dss);

    }
}
