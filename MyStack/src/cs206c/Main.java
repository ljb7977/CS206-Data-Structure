package cs206c;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		// Read string from STDIN
		Scanner scanner = new Scanner(System.in);
		String expression = scanner.nextLine();

		// Parse string
		List<Element> infixExpression = readExpression(expression);

		// Convert infix to postfix
		List<Element> postfixExpression = infixToPostfix(infixExpression);

		double result = evaluate(postfixExpression);

		// Print the answer
		System.out.println(result);
	}
	
	public static double evaluate(List<Element> postfixExpression) {
		MyStack operandStack = new MyStack();
		int len = postfixExpression.size();
		for(int i=0; i<len; i++){
			Element e = postfixExpression.get(i);
			if(e.getType() == Element.OPERAND){
				operandStack.push(e);
			} else {
				Element b = operandStack.pop();
				Element a = operandStack.pop();
				Element ans = null;
				if(e.getOperator() == '+')
					ans = new Element(a.getOperand()+b.getOperand());
				else if (e.getOperator() == '-')
					ans = new Element(a.getOperand()-b.getOperand());
				else if (e.getOperator() == '*')
					ans = new Element(a.getOperand()*b.getOperand());
				else if (e.getOperator() == '/')
					ans = new Element(a.getOperand()/b.getOperand());
				operandStack.push(ans);
			}
		}
		return operandStack.top().getOperand();
	}
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Element> readExpression(String stringExpression) {
		// Problem 2
		ArrayList<Element> infixExpression = new ArrayList<Element>();
		String[] stringsplit;
		stringsplit = stringExpression.split(" ");
		int len = stringsplit.length;
		for(int i=0; i<len; i++){
			if(stringsplit[i].charAt(0) >= '0' && stringsplit[i].charAt(0) <= '9'){
				try{
				int t = Integer.parseInt(stringsplit[i]);
				Element e = new Element(t);
				infixExpression.add(e);
				} catch (NumberFormatException nEx) {
					return null;
				}
			}
			else if (stringsplit[i].charAt(0) == '*' ||
					stringsplit[i].charAt(0) == '+' ||
					stringsplit[i].charAt(0) == '/' ||
					stringsplit[i].charAt(0) == '-' ||
					stringsplit[i].charAt(0) == ')' ||
					stringsplit[i].charAt(0) == '('
					){
				if(stringsplit[i].length() != 1) return null;
				Element e = new Element(stringsplit[i].charAt(0));
				infixExpression.add(e);
			}
		}
		return infixExpression;
	}

	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Element> infixToPostfix(List<Element> infixExpression) {
		// Problem 3
		
		ArrayList<Element> postfixExpression = new ArrayList<Element>();
		MyStack operatorStack = new MyStack();
		int infixlen = infixExpression.size();
		for(int i=0; i<infixlen; i++){
			Element e = infixExpression.get(i);
			if(e.getType() == Element.OPERAND){
				postfixExpression.add(e);
			} else {
				if(e.getOperator() == '('){
					operatorStack.push(e);
				} else if (e.getOperator() == ')'){
					while(operatorStack.top().getOperator() != '('){
						postfixExpression.add(operatorStack.pop());
					}
					operatorStack.pop();
				} else {
					while(!operatorStack.isEmpty() && !compareOperator(e.getOperator(), operatorStack.top().getOperator()))
						postfixExpression.add(operatorStack.pop());
					operatorStack.push(e);
				}
			}
		}
		while(!operatorStack.isEmpty()){
			postfixExpression.add(operatorStack.pop());
		}
		return postfixExpression;
	}
	public static boolean compareOperator(char sub, char obj){
		int priority[] = new int[1000];
		priority['*'] = 1;
		priority['/'] = 1;
		priority['-'] = 2;
		priority['+'] = 2;
		priority['('] = 3;
		if(priority[sub] < priority[obj]) return true;
		return false;
	}
}
