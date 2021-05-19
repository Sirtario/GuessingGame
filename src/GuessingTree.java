import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Phillip
 *
 */
public class GuessingTree {
	private Knot Root;

	public Knot getRoot() {
		return Root;
	}

	public void setRoot(Knot root) {
		Root = root;
	}
	
	public void Solve(Knot knot, Knot previous)
	{
		//get type
		if(knot.getClass()!=Question.class) 
		{
			((Answer)knot).printAnswer();
			
			//get answer
			String answer = readInput();
			
			switch(answer.toLowerCase()) 
			{
			case("yes"):
				//Finished
				break;
			case("no"):
				//insert new question and answer
				insertQuestion((Question)previous,knot);
				break;
			default:
				throw new IllegalArgumentException("Input did not match any possible answer");
			}
			
			return;
		}
		//Print question
		((Question)knot).PrintQuestion();
		
		//get answer
		String answer = readInput();
		
		switch(answer.toLowerCase()) 
		{
			case "yes":
				Solve(knot.getLeft(),knot);
				break;
			case "no":
				Solve(knot.getRight(),knot);
				break;
			default:
				throw new IllegalArgumentException("Input did not match any possible answer");
		}
	}

	private String readInput() {
		String answer;
		
		try(BufferedReader reader = new BufferedReader(
		        new InputStreamReader(System.in));){
			
			answer = reader.readLine();
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		return answer;
	}

	private void insertQuestion(Question before,Knot current) {
		Question newQuestion = new Question();
		Answer newAnswer = new Answer();
		System.out.println("Type in new question:");
		String res = readInput();
		newQuestion.setQuestion(res);
		
		if(before.getLeft()==current) 
		{
			before.setLeft(newQuestion);
		}
		else if(before.getRight()==current) 
		{
			before.setRight(newQuestion);
		}
		else 
		{
			throw new IllegalArgumentException("current knot is not in the before question");
		}
		
		//assign the current knot to left or right
		System.out.println("Should the current knot be assigned to yes or no?");
		String assignment = readInput();
		
		switch(assignment.toLowerCase()) 
		{
		case("yes"):
			//assign left side
			newQuestion.setLeft(current);
			newQuestion.setRight(newAnswer);
			break;
		case("no"):
			//assign right side
			newQuestion.setRight(current);
			newQuestion.setLeft(newAnswer);
			break;
		default:
			break;
		}
		System.out.println("Set new answer:");
		newAnswer.setAnswer(readInput());
		
		
	}
}
