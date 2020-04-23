package model;

public enum OperatorEnum {
    OPERATOR_MOVE_1_X, OPERATOR_MOVE_2_X,
    OPERATOR_MOVE_1_Y, OPERATOR_MOVE_2_Y,
    OPERATOR_STRETCH_1_X, OPERATOR_STRETCH_1_Y,
    OPERATOR_STRETCH_2_X, OPERATOR_STRETCH_2_Y,
    OPERATOR_UNKNOWN;
    public static final String ERROR_UNKNOWN_OPERATOR = "Error! Unexpected operator!";

    public RectanglesState process(double rect1_x0, double rect1_y0, double rect1_x2, double rect1_y2,
                                   double rect2_x0, double rect2_y0, double rect2_x2, double rect2_y2,
                                   double move_rect1_x, double move_rect1_y, double move_rect2_x, double move_rect2_y,
                                   double stretch_rect1_x, double stretch_rect1_y,
                                   double stretch_rect2_x, double stretch_rect2_y) {
        Rectangle rect1 = new Rectangle(rect1_x0, rect1_y0, rect1_x2, rect1_y2);
        Rectangle rect2 = new Rectangle(rect2_x0, rect2_y0, rect2_x2, rect2_y2);
        RectanglesState state = new RectanglesState(rect1, rect2);
        switch (this) {
            case OPERATOR_MOVE_1_X: {
                rect1.MoveX(move_rect1_x);
                state.setRectangle1(rect1);
                return state;
            }
            case OPERATOR_MOVE_2_X: {
                rect2.MoveX(move_rect2_x);
                state.setRectangle2(rect2);
                return state;
            }
            case OPERATOR_MOVE_1_Y: {
                rect1.MoveY(move_rect1_y);
                state.setRectangle1(rect1);
                return state;
            }
            case OPERATOR_MOVE_2_Y: {
                rect2.MoveY(move_rect2_y);
                state.setRectangle2(rect2);
                return state;
            }

            case OPERATOR_STRETCH_1_X: {
                rect1.StretchingX(stretch_rect1_x);
                state.setRectangle1(rect1);
                return state;
            }
            case OPERATOR_STRETCH_2_X: {
                rect2.StretchingX(stretch_rect2_x);
                state.setRectangle2(rect2);
                return state;
            }
            case OPERATOR_STRETCH_1_Y: {
                rect1.StretchingY(stretch_rect1_y);
                state.setRectangle1(rect1);
                return state;
            }
            case OPERATOR_STRETCH_2_Y: {
                rect2.StretchingY(stretch_rect2_y);
                state.setRectangle2(rect2);
                return state;
            }
        }
        throw new RuntimeException(ERROR_UNKNOWN_OPERATOR);
    }
}
