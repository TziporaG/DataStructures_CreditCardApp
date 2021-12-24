package creditCardFiles;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		Stack<Integer> stackTest = new Stack<>();
		
		Stack<Integer> stackCopy = new Stack<>();
		
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
	
		
		stackCopy.addAll(stackTest);
		
		System.out.println("test:" + stackTest.toString());
		System.out.println("copy:" + stackCopy.toString());
		
		System.out.print(stackTest.pop());
		
		System.out.print(stackCopy.pop());

	}

}
