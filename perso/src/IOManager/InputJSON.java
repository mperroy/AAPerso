package IOManager;

import graphManagement.Goal;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.io.FileReader;

public class InputJSON {

	public static InputJSON singleton = null;

	private ArrayList<Point2D.Double> fieldLimits;
	private ArrayList<Goal> goals;
	private ArrayList<Point2D.Double> opponents;
	private double robotRadius;
	private double thetaStep;
	private double posStep;

	public static InputJSON getInstance(String filePath) {
		if (singleton == null)
			singleton = new InputJSON(filePath);
		return singleton;
	}

	public static InputJSON getInstance() throws RuntimeException {
		if (singleton != null)
			return singleton;

		throw new RuntimeException("InputJson not instantiated yet");
	}

	public static InputJSON renewInstance(String filePath) {
		singleton = null;
		return getInstance(filePath);
	}

	private InputJSON(String filePath) {
		JSONObject jObj = readJsonFromFile(filePath);
		if (jObj == null)
			System.exit(-1);

		fieldLimits = new ArrayList<Point2D.Double>();
		JSONArray fields = jObj.getJSONArray("field_limits");
		for (int i = 0; i < fields.length(); i++) {
			JSONArray field = fields.getJSONArray(i);
			fieldLimits.add(new Point2D.Double(field.getDouble(0), field.getDouble(1)));
		}

		goals = new ArrayList<Goal>();
		JSONArray listGoals = jObj.getJSONArray("goals");
		for (int i = 0 ; i < listGoals.length() ; i++) {			
			JSONObject currGoal = listGoals.getJSONObject(i);

			ArrayList<Point2D.Double> limits = new ArrayList<Point2D.Double>();
			JSONArray posts = currGoal.getJSONArray("posts");
			for (int j = 0 ; j < posts.length() ; j++) {
				JSONArray point = posts.getJSONArray(j);
				limits.add(new Point2D.Double(point.getDouble(0), point.getDouble(1)));
			}
			
			JSONArray direction = currGoal.getJSONArray("direction");
			goals.add(new Goal(limits, new Point2D.Double(direction.getDouble(0), direction.getDouble(1))));
		}
		
		opponents = new ArrayList<Point2D.Double>();
		JSONArray totalOpponents = jObj.getJSONArray("opponents");
		for (int i = 0 ; i < totalOpponents.length() ; i++) {
			JSONArray opponent = totalOpponents.getJSONArray(i);
			opponents.add(new Point2D.Double(opponent.getDouble(0), opponent.getDouble(1)));
		}
		
		robotRadius = jObj.getDouble("robot_radius");
		thetaStep = jObj.getDouble("theta_step");
		posStep = jObj.getDouble("pos_step");
	}

	private JSONObject readJsonFromFile(String filePath) {
		try {
			FileReader reader = new FileReader(filePath);
			StringBuilder buffer = new StringBuilder();
			int i;
			char a;
			while ((i = reader.read()) != -1) {
				a = (char) i;
				buffer.append(a);
			}

			reader.close();

			return new JSONObject(buffer.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public int opponentNumber() {
		return opponents.size();
	}

	public ArrayList<Point2D.Double> getFieldLimits() {
		return fieldLimits;
	}

	public void setFieldLimits(ArrayList<Point2D.Double> fieldLimits) {
		this.fieldLimits = fieldLimits;
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}

	public ArrayList<Point2D.Double> getOpponents() {
		return opponents;
	}

	public void setOpponents(ArrayList<Point2D.Double> opponents) {
		this.opponents = opponents;
	}

	public double getRobotRadius() {
		return robotRadius;
	}

	public void setRobotRadius(double robotRadius) {
		this.robotRadius = robotRadius;
	}

	public double getThetaStep() {
		return thetaStep;
	}

	public void setThetaStep(double thetaStep) {
		this.thetaStep = thetaStep;
	}

	public double getPosStep() {
		return posStep;
	}

	public void setPosStep(double posStep) {
		this.posStep = posStep;
	}

	public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("fields limits : ").append(fieldLimits.toString()).append("\n");
        sb.append("theta step : ").append(thetaStep).append("\n");
        sb.append("pos step : ").append(posStep).append("\n");
        sb.append("radius : ").append(robotRadius).append("\n");
        sb.append("opponents : ").append(opponents).append("\n");
        sb.append("goals : ").append(goals).append("\n");

        return sb.toString();
    }
}
