import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
public class TeamApplication extends Application {
	/**Author: Matt Busch
	 * Date: 10/19/17
	 * Purpose: Authenticate user and allow access to program
	 * */
	private String masterUsername = "csc200";
	private String masterPassword = "password";

	@Override
	public void start(Stage primaryStage) {
		/**Purpose: To authenticate user using designated username and password and then either have the user try again on wrong username or password or allow access to program, which will then prompt user for input and display results according to input
		 * */
		Dialog<Pair<String,String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Login to use Team Size Calculator");
		dialog.setGraphic(new ImageView(this.getClass().getResource("Login.png").toString()));
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		grid.add(new Label("Username:"),0,0);
		grid.add(username,1,0);
		grid.add(new Label("Password:"),0,1);
		grid.add(password,1,1);
		dialog.getDialogPane().setContent(grid);
		dialog.setResultConverter(dialogButton-> {
			if(dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});
			Optional<Pair<String, String>> result;
		do {
		result = dialog.showAndWait();
		username.clear();
		password.clear();
		} while(!result.get().getKey().equals(masterUsername) && !result.get().getValue().equals(masterPassword));
		TextInputDialog inputDialog = new TextInputDialog();
		inputDialog.setTitle("Team Size Calculator");
		inputDialog.setHeaderText("Enter number of players");
		Optional<String> result2 = inputDialog.showAndWait();
		int numPlayers = Integer.parseInt(result2.get());
		int teamSize;
		if (numPlayers > 11 && numPlayers < 55) {
			teamSize = numPlayers/11;
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");
			alert.setHeaderText("Result");
			alert.setContentText("Your number of players is " + numPlayers + "\nYour team size is " + teamSize);
			alert.showAndWait();
		}
		else {
			teamSize=1;
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");
			alert.setHeaderText("Result");
			alert.setContentText("Your number of players is " + numPlayers + "\nYour team size is " + teamSize);
			alert.showAndWait();
		}

	}

	public static void main(String [] args) {
		launch(args);

	}
}
