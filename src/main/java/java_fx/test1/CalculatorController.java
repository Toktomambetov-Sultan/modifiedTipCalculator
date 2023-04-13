package java_fx.test1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class CalculatorController {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    @FXML
    private TextField AmountInput;

    @FXML
    private Button Calculatebutton;

    @FXML
    private TextField TipInput;

    @FXML
    private TextField TotalInput;

    @FXML
    private Slider slider;

    @FXML
    private Label tip;
    public void initialize(){
       currency.setRoundingMode(RoundingMode.HALF_UP);

       slider.valueProperty().addListener(
               new ChangeListener<Number>() {
                   @Override
                   public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                       tipPercentage = BigDecimal.valueOf(newValue.intValue()/100.0);
                       tip.setText(percent.format(tipPercentage));
                   }
               }
       );
    }
    public void calculateButtonPressed (ActionEvent event){
        try {
            BigDecimal amount = new BigDecimal(AmountInput.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            TipInput.setText(currency.format(tip));
            TotalInput.setText(currency.format(total));
        }catch (NumberFormatException ex){
            AmountInput.setText("Enter amount");
            AmountInput.selectAll();
            AmountInput.requestFocus();
        }
    }

}
