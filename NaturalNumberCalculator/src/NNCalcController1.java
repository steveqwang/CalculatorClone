import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Yu Wang
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        /*
         * updateSubtractAllowed
         */
        if (model.top().compareTo(model.bottom()) >= 0) {
            view.updateSubtractAllowed(true);
        } else {
            view.updateSubtractAllowed(false);
        }
        /*
         * updateRootAllowed
         */
        if (model.bottom().compareTo(TWO) >= 0
                && model.bottom().compareTo(INT_LIMIT) <= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        /*
         * updatePowerAllowed
         */
        if (model.bottom().compareTo(INT_LIMIT) <= 0) {
            view.updatePowerAllowed(true);
        } else {
            view.updatePowerAllowed(false);
        }
        /*
         * updateDivideAllowed
         */
        if (!model.bottom().isZero()) {
            view.updateDivideAllowed(true);
        } else {
            view.updateDivideAllowed(false);
        }
        /*
         * Update textArea
         */
        view.updateTopDisplay(model.top());
        view.updateBottomDisplay(model.bottom());
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    /**
     * Processes event to clear bottom operand.
     *
     * @updates this.model.bottom, this.view
     * @ensures <pre>
     * this.model.bottom = 0  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to swap operands.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.model.top = #this.model.bottom  and
     * this.model.bottom = #this.model.top  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to enter bottom operand to top.
     *
     * @updates this.model.top, this.view
     * @ensures <pre>
     * this.model.top = this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processEnterEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        top.copyFrom(bottom);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do an add operation.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top + #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processAddEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        top.add(bottom);
        bottom.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a subtract operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom <= this.model.top
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top - #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processSubtractEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        top.subtract(bottom);
        bottom.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a multiply operation.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.mode.top = 0  and
     * this.model.bottom = #this.model.top * #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processMultiplyEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        top.multiply(bottom);
        bottom.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a divide operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom > 0
     * @ensures <pre>
     * #this.model.top =
     *   this.model.bottom * #this.model.bottom + this.model.top  and
     * 0 <= this.model.top < #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processDivideEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber remainder = top.newInstance();
        remainder = top.divide(bottom);
        bottom.transferFrom(top);
        top.copyFrom(remainder);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a power operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom <= INT_LIMIT
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top ^ (#this.model.bottom)  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processPowerEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        int index = bottom.toInt();
        top.power(index);
        bottom.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a root operation.
     *
     * @updates this.model, this.view
     * @requires 2 <= this.model.bottom <= INT_LIMIT
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom =
     *   [the floor of the #this.model.bottom root of #this.model.top]  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processRootEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        int index = bottom.toInt();
        top.root(index);
        bottom.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to add a new (low-order) digit to the bottom operand.
     *
     * @param digit
     *            the low-order digit to be added
     *
     * @updates this.model.bottom, this.view
     * @requires 0 <= digit < 10
     * @ensures <pre>
     * this.model.bottom = #this.model.bottom * 10 + digit  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.multiplyBy10(digit);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
