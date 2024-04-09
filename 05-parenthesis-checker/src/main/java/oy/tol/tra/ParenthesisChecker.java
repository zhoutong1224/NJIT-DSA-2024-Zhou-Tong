package oy.tol.tra;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      if (stack == null) {
         throw new ParenthesesException("The stack is null", ParenthesesException.STACK_FAILURE);
      }

      int count = 0;
      for (int i = 0; i < fromString.length(); i++) {
         char characterChecked = fromString.charAt(i);
         if (isOpenParenthesis(characterChecked)) {
            stack.push(characterChecked);
            count++;
         } else if (isClosingParenthesis(characterChecked)) {
            if (stack.isEmpty()) {
               throw new ParenthesesException("There are too many closing parentheses", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }
            char popped = stack.pop();
            count++;
            if (!areParenthesesMatching(popped, characterChecked)) {
               throw new ParenthesesException("Wrong kind of parenthesis were in the text", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
            }
         }
      }

      if (!stack.isEmpty()) {
         throw new ParenthesesException("Too many opening parentheses", ParenthesesException.STACK_FAILURE);
      }

      return count;
   }

   private static boolean isOpenParenthesis(char c) {
      return c == '(' || c == '[' || c == '{';
   }

   private static boolean isClosingParenthesis(char c) {
      return c == ')' || c == ']' || c == '}';
   }

   private static boolean areParenthesesMatching(char opening, char closing) {
      return (opening == '(' && closing == ')') ||
             (opening == '[' && closing == ']') ||
             (opening == '{' && closing == '}');
   }
}
