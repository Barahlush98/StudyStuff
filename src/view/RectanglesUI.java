package view;
import main.RectanglesApplication;
import model.OperatorEnum;
import model.Rectangle;
import model.RectanglesState;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.text.ParseException;
import java.util.StringTokenizer;

public class RectanglesUI {
    public static final String COMMAND_YES = "Y";
    public static final String COMMAND_NO = "N";
    public static final String MESSAGE_ABOUT = "Two rectangles";
    public static final String MESSAGE_CONTINUE = "Continue? (Y/N)>";
    public static final String MESSAGE_INITIAL_STATE = "Initial state:";
    public static final String MESSAGE_INPUT_ERROR = "Input error. More then one token found.";
    public static final String MESSAGE_INPUT_OPERAND = "Input an operand>";
    public static final String MESSAGE_INPUT_OPERATOR = "Input an operation (move1_x, " +
            "move1_y, move2_x, move1_y, str1_x str1_y, str2_x, str2_y)>";
    public static final String MESSAGE_RESULT = "Result: \n";
    public static final String MESSAGE_TRY_AGAIN = "Try again>";
    public static final String MESSAGE_XML_ERROR = "XML binding error! XML state file may be corrupted.";
    public static final String MESSAGE_IO_ERROR = "Unexpected Input/Output error!";
    public static final String ERROR_ARITHMETIC_PATTERN = "Arithmetic Error: %s";
    public static final String ERROR_TOKEN_PARSE_PATTERN = "Error! Unexpected token: %s";
    private RectanglesApplication application;
    private BufferedReader input;
    private PrintWriter output;
    private StringTokenizer tokenizer;
    private OperatorParser operatorParser;

    public RectanglesUI(RectanglesApplication application) {
        this.application = application;
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.output = new PrintWriter(System.out);
        this.tokenizer = new StringTokenizer("");
        this.operatorParser = new OperatorParser();
    }

    public void run() {
        output.println(MESSAGE_ABOUT);
        output.print(MESSAGE_INITIAL_STATE);
        output.print('\n');
        try {
            double first_x0 = application.getStateManager().getRectangle1State().getX0();
            double first_y0 = application.getStateManager().getRectangle1State().getY0();
            double first_x2 = application.getStateManager().getRectangle1State().getX2();
            double first_y2 = application.getStateManager().getRectangle1State().getY2();

            double second_x0 = application.getStateManager().getRectangle2State().getX0();
            double second_y0 = application.getStateManager().getRectangle2State().getY0();
            double second_x2 = application.getStateManager().getRectangle2State().getX2();
            double second_y2 = application.getStateManager().getRectangle2State().getY2();

            double area = application.getStateManager().getIntersectionAreaState();

            output.println("   rect1 diag.: (" + first_x0 + ", " + first_y0 + ") -> (" +
                    first_x2 + ", " + first_y2 + ");"  );
            output.println("   rect2 diag.: (" + second_x0 + ", " + second_y0 + ") -> (" +
                    second_x2 + ", " + second_y2 + ");"  );
            output.println("   Current intersection area: " + area);
        } catch (JAXBException e) {
            output.println(MESSAGE_XML_ERROR);
            return;
        }
        boolean cont = true;
        while (cont) {
            OperatorEnum operator;
            double move_rect1_x = 0, move_rect1_y = 0,
                    move_rect2_x = 0, move_rect2_y = 0, stretch_rect1_x = 0, stretch_rect1_y = 0,
                    stretch_rect2_x = 0, stretch_rect2_y = 0, rect1_x0 = 0,rect1_y0 = 0,rect1_x2 = 0,
                    rect1_y2 = 0, rect2_x0 = 0, rect2_y0 = 0, rect2_x2 = 0, rect2_y2 = 0;
            try {
                rect1_x0 = application.getStateManager().getRectangle1State().getX0();
                rect1_y0 = application.getStateManager().getRectangle1State().getY0();
                rect1_x2 = application.getStateManager().getRectangle1State().getX2();
                rect1_y2 = application.getStateManager().getRectangle1State().getY2();
                rect2_x0 = application.getStateManager().getRectangle2State().getX0();
                rect2_y0 = application.getStateManager().getRectangle2State().getY0();
                rect2_x2 = application.getStateManager().getRectangle2State().getX2();
                rect2_y2 = application.getStateManager().getRectangle2State().getY2();
            }
            catch (JAXBException e) {
                output.println("JAXB exception!");
            }
            try {
                output.print(MESSAGE_INPUT_OPERATOR);
                output.flush();
                operator = readOperator();
                if (operator == OperatorEnum.OPERATOR_MOVE_1_X) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    move_rect1_x = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_MOVE_2_X) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    move_rect2_x = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_MOVE_1_Y) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    move_rect1_y = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_MOVE_2_Y) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    move_rect2_y = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_STRETCH_1_X) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    stretch_rect1_x = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_STRETCH_2_X) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    stretch_rect2_x = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_STRETCH_1_Y) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    stretch_rect1_y = readDouble();
                }
                else if (operator == OperatorEnum.OPERATOR_STRETCH_2_Y) {
                    output.print(MESSAGE_INPUT_OPERAND);
                    output.flush();
                    stretch_rect2_y = readDouble();
                }
                else {
                    output.flush();
                }
            } catch (IOException e) {
                output.println(MESSAGE_IO_ERROR);
                output.flush();
                return;
            }
            try {
                RectanglesState result = operator.process(rect1_x0, rect1_y0, rect1_x2, rect1_y2, rect2_x0,
                    rect2_y0, rect2_x2, rect2_y2, move_rect1_x, move_rect1_y, move_rect2_x, move_rect2_y,
                        stretch_rect1_x, stretch_rect1_y, stretch_rect2_x,stretch_rect2_y);
                output.print(MESSAGE_RESULT);
                double first_x0 = result.getRectangle1().getX0();
                double first_y0 = result.getRectangle1().getY0();
                double first_x2 = result.getRectangle1().getX2();
                double first_y2 = result.getRectangle1().getY2();
                double second_x0 = result.getRectangle2().getX0();
                double second_y0 = result.getRectangle2().getY0();
                double second_x2 = result.getRectangle2().getX2();
                double second_y2 = result.getRectangle2().getY2();
                double area = result.getIntersectionArea();
                output.println("   rect1 diag.: (" + first_x0 + ", " + first_y0 + ") -> (" +
                        first_x2 + ", " + first_y2 + ");"  );
                output.println("   rect2 diag.: (" + second_x0 + ", " + second_y0 + ") -> (" +
                        second_x2 + ", " + second_y2 + ");"  );
                output.println("   Current intersection area: " + area);
                output.flush();
                application.getStateManager().setState(result.getRectangle1(), result.getRectangle2());
            } catch (JAXBException e) {
                output.println(MESSAGE_XML_ERROR);
                return;
            } catch (ArithmeticException e) {
                output.println(String.format(ERROR_ARITHMETIC_PATTERN,
                        e.getMessage()));
                continue;
            }
            try {
                cont = readContinue();
            } catch (IOException e) {
                output.println(MESSAGE_IO_ERROR);
                return;
            }
        }
    }

    private String readString() throws IOException {
        while (true) {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(input.readLine());
            }
            String token = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens()) {
                return token;
            }
            tokenizer = new StringTokenizer("");
            output.println(MESSAGE_INPUT_ERROR);
            output.print(MESSAGE_TRY_AGAIN);
            output.flush();
        }
    }

    private double readDouble() throws IOException {
        while (true) {
            String token = readString();
            try {
                return Double.parseDouble(token);
            } catch (NumberFormatException e) {
                output.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
                output.print(MESSAGE_TRY_AGAIN);
                output.flush();
            }
        }
    }

    private OperatorEnum readOperator() throws IOException {
        OperatorEnum operator = null;
        while (operator == null) {
            String token = readString();
            try {
                operator = operatorParser.parseOperator(token);
            } catch (ParseException e) {
                output.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
                output.print(MESSAGE_TRY_AGAIN);
                output.flush();
            }
        }
        return operator;
    }

    private boolean readContinue() throws IOException {
        output.print(MESSAGE_CONTINUE);
        output.flush();
        while (true) {
            String token = readString();
            if (COMMAND_YES.equalsIgnoreCase(token)) {
                return true;
            }
            if (COMMAND_NO.equalsIgnoreCase(token)) {
                return false;
            }
            output.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
            output.print(MESSAGE_TRY_AGAIN);
            output.flush();
        }
    }
}
