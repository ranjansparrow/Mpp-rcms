package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbconfig.DBconfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.daomodel.MemberDAO;
import model.pojo.MemberPojo;

public class MemberListController implements Initializable {

	public ObservableList<MemberPojo> list = FXCollections.observableArrayList();
	DBconfig dbBconfig = new DBconfig();
	@FXML
	private TableView<MemberPojo> tablelist;
	@FXML
	private TableColumn<MemberPojo, Integer> serailNumber;
	@FXML
	private TableColumn<MemberPojo, String> fullName;
	@FXML
	private TableColumn<MemberPojo, String> address;
	@FXML
	private TableColumn<MemberPojo, Integer> memberType;
	@FXML
	private TableColumn<MemberPojo, Integer> packageType;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serailNumber.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("member_id"));
		fullName.setCellValueFactory(new PropertyValueFactory<MemberPojo, String>("fullname"));
		address.setCellValueFactory(new PropertyValueFactory<MemberPojo, String>("address"));
		memberType.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("member_type"));
		packageType.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("fee_package"));
		try {
			Connection conn = dbBconfig.getConnection();
			MemberDAO membdao = new MemberDAO();
			ResultSet rs = MemberDAO.getMember(conn);
			int i = 0;
			while (rs.next()) {
				MemberPojo pojo = new MemberPojo(++i, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				System.out.println(pojo);
				list.add(pojo);
				tablelist.setItems(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
