package org.helmo.gbeditor.views.subviews;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.helmo.gbeditor.presenters.interfaces.presenters.MainPresenterInterface;
import org.helmo.gbeditor.presenters.interfaces.presenters.subpresenters.CreateBookPInterface;
import org.helmo.gbeditor.presenters.interfaces.views.subviews.SubViewInterface;

/**
 * Vue création livre
 */
public class CreateBookView extends GridPane implements SubViewInterface {

    private MainPresenterInterface presenter;

    private CreateBookPInterface createBookPresenter;

    private BorderPane titleBox = new BorderPane(); {
        Label title = new Label("Créer un livre");

        titleBox.setCenter(title);

        titleBox.getStyleClass().add("title");
    }

    private TextField bookTitle = new TextField(); {
        bookTitle.setPrefWidth(250);

        int maxLength = 150;
        bookTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                bookTitle.setText(oldValue);
            }
        });
    }
    private BorderPane bookTitleBox = new BorderPane(); {
        Label text = new Label("Titre ");

        bookTitleBox.setLeft(text);
        bookTitleBox.setRight(bookTitle);

        bookTitleBox.getStyleClass().add("");
    }

    private Label isbnVerif = new Label(" ");
    private TextField isbn = new TextField(); {
        isbn.setPrefWidth(90);
        //isbn.setEditable(false);
        //isbn.setPromptText("*-******-**-*");

        isbn.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 11 || newValue.length() < 9 || !newValue.matches("\\d{1}-\\d{6}-\\d*")) {
                isbn.setText(oldValue);
            } else {
                isbnVerif.setText(presenter.getIsbnVerif(newValue));
            }
        });

    }

    private BorderPane isbnBox = new BorderPane(); {
        Label text = new Label("ISBN ");

        HBox field = new HBox(); {
            field.getChildren().addAll(isbn, isbnVerif);
        }

        isbnBox.setLeft(text);
        isbnBox.setRight(field);

        isbnBox.getStyleClass().add("");
    }

    private Label wordCount = new Label("Caractères : 0");
    private TextArea summary = new TextArea(); {
        summary.setPrefRowCount(8);
        summary.setPrefColumnCount(100);
        summary.setWrapText(true);
        summary.setPrefWidth(500);

        int maxLength = 500;
        summary.textProperty().addListener((observable, oldValue, newValue) -> {
            int wc = newValue.length();
            if (newValue.length() > maxLength) {
                summary.setText(oldValue);
                --wc;
            }
            wordCount.setText("Caractères : " + wc);
        });
    }
    private BorderPane summaryBox = new BorderPane(); {
        Label text = new Label("Résumé ");

        summaryBox.setLeft(text);
        summaryBox.setRight(summary);
        summaryBox.setBottom(wordCount);

        summaryBox.getStyleClass().add("");
    }

    private TextField author = new TextField(); {
        author.setEditable(false);
    }
    private BorderPane authorBox = new BorderPane(); {
        Label text = new Label("Auteur ");

        authorBox.setLeft(text);
        authorBox.setRight(author);

        authorBox.getStyleClass().add("");
    }

    private BorderPane createBtnBox = new BorderPane(); {
        Button createButton = new Button("Créer le livre"); {
            createButton.setOnAction(action -> createBookPresenter.createBook(bookTitle.getText(), summary.getText(), author.getText(), isbn.getText() + isbnVerif.getText()));

            createButton.getStyleClass().add("");
        }

        createBtnBox.setCenter(createButton);

        createBtnBox.getStyleClass().add("auth-box");
    }

    /**
     * Constructeur de la sous vue
     * @param presenter (MainPresenterInterface)
     */
    public CreateBookView(MainPresenterInterface presenter) {
        this.presenter = presenter;

        this.add(titleBox, 0, 0);
        this.add(bookTitleBox, 0, 1);
        this.add(isbnBox, 0, 2);
        this.add(summaryBox, 0, 3);
        this.add(authorBox, 0, 4);

        this.add(createBtnBox, 0, 5);

        this.setAlignment(Pos.CENTER);

        this.setVisible(false);
    }

    /**
     * Assigne le presentateur spécifique
     */
    public void setPresenter() {
        createBookPresenter = (CreateBookPInterface) presenter.getSubPresenters(1);
    }

    /**
     * initialise les champs de la vue
     */
    @Override
    public void refresh() {
        bookTitle.clear();
        summary.clear();

        author.setText(presenter.getAuthorInfos());

        String isbnNumber = createBookPresenter.generateIsbn();
        isbn.setText(isbnNumber.substring(0, isbnNumber.length()-2));
        isbnVerif.setText(isbnNumber.substring(isbnNumber.length()-2));
    }

    /**
     * Modifie la visibilité de la vue
     * @param b (boolean)
     */
    @Override
    public void setVisibility(boolean b) {
        this.setVisible(b);
    }

}
