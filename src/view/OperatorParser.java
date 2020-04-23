package view;
import model.OperatorEnum;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class OperatorParser {
    private Map<String, OperatorEnum> operatorMap = new HashMap<>();
    public OperatorParser() {
        operatorMap.put("move1_x", OperatorEnum.OPERATOR_MOVE_1_X);
        operatorMap.put("move2_x", OperatorEnum.OPERATOR_MOVE_2_X);
        operatorMap.put("move1_y", OperatorEnum.OPERATOR_MOVE_1_Y);
        operatorMap.put("move2_y", OperatorEnum.OPERATOR_MOVE_2_Y);

        operatorMap.put("str1_x", OperatorEnum.OPERATOR_STRETCH_1_X);
        operatorMap.put("str2_x", OperatorEnum.OPERATOR_STRETCH_2_X);
        operatorMap.put("str1_y", OperatorEnum.OPERATOR_STRETCH_1_Y);
        operatorMap.put("str2_y", OperatorEnum.OPERATOR_STRETCH_2_Y);
    }

    public OperatorEnum parseOperator(String token) throws ParseException {
        OperatorEnum operator = operatorMap.get(token);
        if (operator == null) {
            throw new ParseException(String.format(RectanglesUI.ERROR_TOKEN_PARSE_PATTERN, token), 0);
        }
        return operator;
    }
}
