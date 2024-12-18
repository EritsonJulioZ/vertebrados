package com.example.vertebrados;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class VertebradosController implements Initializable {

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Vertebrados, String> colClasificacion;

    @FXML
    private TableColumn<Vertebrados, Integer> colIDAnimal;

    @FXML
    private TableColumn<Vertebrados, String> colNombreCientifico;

    @FXML
    private TableColumn<Vertebrados, String> colNombreComun;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private TableView<Vertebrados> tblVertebrados;
    int id =0;

    @FXML
    private ComboBox<String> txtClassification;

    @FXML
    private TextField txtCommonName;

    @FXML
    private TextField txtIdentificationNumber;

    @FXML
    private TextField txtScientistName;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showVertebrados();
        loadComboBox();
    }

    public ObservableList<Vertebrados> getVertebradosList() {
        ObservableList<Vertebrados> vertebradosList = FXCollections.observableArrayList();

        String sql = "select * from animales";

        con = DatabaseConnection.getConnection();

        try{
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Vertebrados vert = new Vertebrados();

                vert.setId_animal(rs.getInt("id_animal"));
                vert.setNombre_cientifico(rs.getString("nombre_cientifico"));
                vert.setNombre_comun(rs.getString("nombre_comun"));
                vert.setClasificacion(rs.getString("clasificacion"));

                vertebradosList.add(vert);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vertebradosList;
    }

    public void showVertebrados(){
        ObservableList<Vertebrados> list = getVertebradosList();
        tblVertebrados.setItems(list);
        colIDAnimal.setCellValueFactory(new PropertyValueFactory<Vertebrados, Integer>("id_animal"));
        colNombreCientifico.setCellValueFactory(new PropertyValueFactory<Vertebrados, String>("nombre_cientifico"));
        colNombreComun.setCellValueFactory(new PropertyValueFactory<Vertebrados, String>("nombre_comun"));
        colClasificacion.setCellValueFactory(new PropertyValueFactory<Vertebrados, String>("clasificacion"));
    }

    public void loadComboBox() {
        txtClassification.getItems().addAll("Selecciona una clasificacion", "Mamifero", "Ave", "Reptil", "Anfibio");
        txtClassification.getSelectionModel().selectFirst();
    }

    @FXML
    void clearForm() {
        txtScientistName.clear();
        txtCommonName.clear();
        txtIdentificationNumber.clear();
        txtClassification.getSelectionModel().selectFirst();
        btnSave.setDisable(false);
    }

    @FXML
    void deleteAnimal(ActionEvent event) {
        String delete = "delete from animales where id_animal = ?";
        con = DatabaseConnection.getConnection();

        try{
            st = con.prepareStatement(delete);
            st.setInt(1, id);
            st.executeUpdate();
            clearForm();
            showVertebrados();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveAnimal(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registrando nuevo vertebrado");
        String message = "¿Desea registrar el animal " + txtScientistName.getText() + "?";
        alert.setContentText(message);

        Optional<ButtonType> response = alert.showAndWait();

        String query = "insert into animales (id_animal, nombre_cientifico, nombre_comun, clasificacion) values (null,?,?,?)";

        try{
            con = DatabaseConnection.getConnection();
            st = con.prepareStatement(query);

            st.setString(1, txtScientistName.getText());
            st.setString(2, txtCommonName.getText());
            st.setString(3, txtClassification.getValue());

            if( response.isPresent() &&  (response.get() == ButtonType.OK) && !txtScientistName.getText().isEmpty() && !txtCommonName.getText().isEmpty() && !txtClassification.getValue().equals("Selecciona una clasificacion")){
                st.executeUpdate();
                clearForm();
                showVertebrados();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void getData(MouseEvent event) {
        Vertebrados vert = tblVertebrados.getSelectionModel().getSelectedItem();
        id = vert.getId_animal();
        txtIdentificationNumber.setText(String.valueOf(vert.getId_animal()));
        txtScientistName.setText(vert.getNombre_cientifico());
        txtCommonName.setText(vert.getNombre_comun());
        txtClassification.setValue(vert.getClasificacion());
        btnSave.setDisable(true);
    }

    @FXML
    void updateAnimal(ActionEvent event) {
        String update = "update animales set nombre_cientifico = ?, nombre_comun = ?, clasificacion = ? where id_animal = ?";
        con = DatabaseConnection.getConnection();

        try{
            st = con.prepareStatement(update);
            st.setString(1, txtScientistName.getText());
            st.setString(2, txtCommonName.getText());
            st.setString(3, txtClassification.getValue());
            st.setInt(4, id);

            st.executeUpdate();
            clearForm();
            showVertebrados();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
