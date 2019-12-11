package calendarProgramFx;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Gui2 extends Application {

	public void init_text() { // TEXT 초기화 메소드
		idinput.setText("");
		pwinput.setText("");
		idinput2.setText("");
		pwinput2.setText("");
		pwinputcheck.setText("");
		birthdayinput.setText("");
	}

	private Group root = new Group();
	private Image backgroundImage = new Image("/MainBackground.jpg");
	private Image signinBImage = new Image("/SignInButton.jpg");
	private Image signupBImage = new Image("/SignUpButton.jpg");
	private Image fileimage;
	private BufferedImage sourceImage;

	private ImageView backimage = new ImageView(backgroundImage);
	private ImageView memoImage = new ImageView();

	private Button signinB = new Button("로그인");
	private Button signupB = new Button("회원가입");
	private Button loginB = new Button("Login");
	private Button backB = new Button("Back");
	private Button backB2 = new Button("Back");
	private Button joinB = new Button("Join");
	private Button idcheckB = new Button("중복확인");
	private Button browseB = new Button("사진 찾아보기...");
	private Button saveB3 = new Button("저장(변경)");
	private Button deleteB3 = new Button("삭제");
	private Button saveB1 = new Button("저장(변경)");
	private Button deleteB1 = new Button("삭제");
	private Button saveB2 = new Button("저장(변경)");
	private Button deleteB2 = new Button("삭제");

	private Text idtext = new Text("Id : ");
	private Text pwtext = new Text("Pw : ");
	private Text idtext2 = new Text("Id : ");
	private Text pwtext2 = new Text("Pw : ");
	private Text pwchecktext = new Text("Pwcheck : ");
	private Text birthdaytext = new Text("BirthDay : ");
	private Text pwmesage = new Text();
	private Text titleText = new Text("Welcome MyCalendar");

	private TextField idinput = new TextField();
	private TextField idinput2 = new TextField();
	private TextField birthdayinput = new TextField();
	private PasswordField pwinput = new PasswordField();
	private PasswordField pwinput2 = new PasswordField();
	private PasswordField pwinputcheck = new PasswordField();

	private TextField memotitle = new TextField();
	private TextField memodetail = new TextField();

	private HBox hbox1 = new HBox();
	private HBox hbox2 = new HBox();
	private HBox hbox3 = new HBox();
	private HBox hbox4 = new HBox(15);
	private HBox hbox5 = new HBox(15);
	private HBox hbox6 = new HBox(15);
	private VBox vbox1 = new VBox();
	private VBox vbox2 = new VBox();
	
	HashMap <String , VBox> vboxlist = new HashMap<String,VBox>();

	private Label memoL = new Label("Memo");
	private Label memodateL = new Label();
	private Label memotitleL = new Label("일정");
	private Label memodetailL = new Label("상세내용");
	private Label memoimageL = new Label("이미지");
	private Label titleL = new Label();

	private GridPane gridpane1 = new GridPane();
	private GridPane gridpane2 = new GridPane();
	private GridPane gridpane3 = new GridPane();

	private Pane calPane = new Pane();

	private Alert alert = new Alert(AlertType.INFORMATION);

	private ArrayList<Button> calday = new ArrayList<Button>(); // 날짜 버튼 넣을 리스트
	private ArrayList<Button> caldate = new ArrayList<Button>(); // 요일 버튼 넣을 리스트

	private String[] calHeader = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private String[] monthHeader = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	Calendar c = Calendar.getInstance();

	long time = System.currentTimeMillis();
	SimpleDateFormat simple = new SimpleDateFormat("MM");
	String s = simple.format(time);

	private JButton[][] calDate = new JButton[6][7];
	private int width = calHeader.length;
	private int inputDay = 1;
	private int startDay;
	private int lastDay;
	private int inputYear = c.getWeekYear();
	private int inputMonth = Integer.parseInt(s);
	private int inputDate = 1;
	private String monthBtext = monthHeader[inputMonth - 1];
	private Button monthB = new Button(monthBtext);
	private Button nextB = new Button("→");
	private Button beforeB = new Button("←");

	private Label now_month = new Label(monthHeader[inputMonth - 1] + " " + Integer.toString(inputYear));

	final FileChooser fileChooser = new FileChooser();
	private File selectedFile;
	private FileInputStream fis;
	private BufferedInputStream bis;

	int k = 0;
	int m = 0;
	private String getText = Integer.toString(Calendar.DATE);

	private String birthdaymonth = "";
	private String birthdayday = "";
	private String nowid = "";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primary) throws Exception {

		
		
		//hbox1.setPrefSize(400, 100); // 메인 화면 버튼 박스
		//hbox1.relocate(500, 400);
		hbox1.setSpacing(50);
		hbox1.setAlignment(Pos.CENTER);
		
		vbox2.setPrefSize(400, 500);
		vbox2.relocate(450, 100);
		vbox2.setStyle("-fx-background-color:purple");
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setSpacing(300);
		
		vbox2.getChildren().add(titleText);
		vbox2.getChildren().add(hbox1);

		hbox2.setPrefSize(400, 100); // 로그인 화면 버튼 박스
		hbox2.relocate(500, 500);
		hbox2.setSpacing(100);
		hbox2.setVisible(false);

		hbox3.setPrefSize(400, 100);
		hbox3.relocate(500, 500);
		hbox3.setSpacing(100);
		hbox3.setVisible(false);

		gridpane1.setPrefWidth(200); // 로그인 화면 id,pw 텍스트,텍스트필드
		gridpane1.setPrefHeight(70);
		gridpane1.relocate(550, 400);
		gridpane1.setStyle("-fx-background-color: khaki");
		gridpane1.add(idtext, 0, 0);
		gridpane1.add(idinput, 1, 0);
		gridpane1.add(pwtext, 0, 1);
		gridpane1.add(pwinput, 1, 1);
		gridpane1.setHgap(10);
		gridpane1.setVgap(10);
		gridpane1.setPadding(new Insets(10));
		gridpane1.setVisible(false);

		gridpane2.setPrefWidth(400); // 회원가입 화면 id,pw,pwcheck,birthday 텍스트 , 텍스트필드
		gridpane2.setPrefHeight(300);
		gridpane2.relocate(450, 150);
		gridpane2.setStyle("-fx-background-color: khaki");
		gridpane2.add(idtext2, 0, 0);
		gridpane2.add(idinput2, 1, 0);
		gridpane2.add(idcheckB, 2, 0);
		gridpane2.add(pwtext2, 0, 1);
		gridpane2.add(pwinput2, 1, 1);
		gridpane2.add(pwchecktext, 0, 2);
		gridpane2.add(pwinputcheck, 1, 2);
		gridpane2.add(pwmesage, 1, 3);
		gridpane2.add(birthdaytext, 0, 4);
		gridpane2.add(birthdayinput, 1, 4);
		gridpane2.setHgap(20);
		gridpane2.setVgap(30);
		gridpane2.setPadding(new Insets(40));
		gridpane2.setVisible(false);

		calPane.setPrefSize(1280, 720);
		calPane.setStyle("-fx-background-color : cornsilk");
		calPane.setVisible(false);

		gridpane3.setPrefSize(720, 550);
		gridpane3.setLayoutX(0);
		gridpane3.setLayoutY(170);
		gridpane3.setStyle("-fx-background-color : cornsilk");
		gridpane3.setStyle("-fx-grid-lines-visible: true;");

		signinB.setPrefWidth(150); // signin 버튼
		signinB.setStyle("-fx-background-color: cornsilk");
		signinB.setFont(new Font("blond", 20));
		signinB.setOnAction((ActionEvent) -> {
			vbox2.setVisible(false);
			hbox2.setVisible(true);
			gridpane1.setVisible(true);
		});

		signupB.setPrefWidth(150); // signup 버튼
		signupB.setStyle("-fx-background-color: cornsilk");
		signupB.setFont(new Font("blond", 20));
		signupB.setOnAction((ActionEvent) -> {
			vbox2.setVisible(false);
			hbox3.setVisible(true);
			gridpane2.setVisible(true);

		});

		loginB.setPrefWidth(100); // 로그인 버튼
		loginB.setStyle("-fx-background-color: cornsilk");
		loginB.setFont(new Font("blond", 20));
		loginB.setOnAction((ActionEvent) -> {
			if (Db_Login.login(idinput.getText(), pwinput.getText()).equals("1")) {
				alert.setTitle("Login Mesage");
				alert.setContentText("로그인 성공 접속중......");
				alert.setHeaderText(null);
				alert.show();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				birthdaymonth = Db_Login.out_birthday_month(idinput.getText(), "MONTH");
				birthdayday = Db_Login.out_birthday_month(idinput.getText(), "DAY");
				nowid = idinput.getText();
				// System.out.println(Db_Login.out_birthday_month(idinput.getText(),"MONTH"));
				// System.out.println(Db_Login.out_birthday_month(idinput.getText(),"DAY"));
				init_text();
				calbutton(inputYear, inputMonth, inputDate);
				calendaropen();

			} else if (Db_Login.login(idinput.getText(), pwinput.getText()).equals("2")) {
				alert.setTitle("Login Mesage");
				alert.setContentText("아이디가 틀리거나 존재하지 않습니다");
				alert.setHeaderText(null);
				alert.show();
			} else if (Db_Login.login(idinput.getText(), pwinput.getText()).equals("3")) {
				alert.setTitle("Login Mesage");
				alert.setContentText("패스워드가 틀립니다");
				alert.setHeaderText(null);
				alert.show();
			}
		});

		backB.setPrefWidth(100); // 뒤로가기 버튼
		backB.setStyle("-fx-background-color: cornsilk");
		backB.setFont(new Font("blond", 20));
		backB.setOnAction((ActionEvent) -> {
			hbox2.setVisible(false);
			gridpane1.setVisible(false);
			vbox2.setVisible(true);
			init_text();
		});

		backB2.setPrefWidth(100); // 뒤로가기 버튼
		backB2.setStyle("-fx-background-color: cornsilk");
		backB2.setFont(new Font("blond", 20));
		backB2.setOnAction((ActionEvent) -> {
			hbox3.setVisible(false);
			gridpane2.setVisible(false);
			vbox2.setVisible(true);
			init_text();
		});

		idcheckB.setPrefWidth(150); // 중복확인 버튼
		idcheckB.setOnAction((ActionEvent) -> {
			String command = Db_Login.idCheck(idinput2.getText());
			alert.setTitle("check Mesage");
			alert.setContentText(command);
			alert.setHeaderText(null);
			alert.show();
		});

		pwinput2.setOnKeyReleased((EventHandle) -> {
			if (pwinput2.getText().equals(pwinputcheck.getText())) {
				pwmesage.setVisible(true);
				pwmesage.setText("비밀번호 일치!!");
				pwmesage.setFill(Color.GREEN);
			} else if (!pwinput2.getText().equals(pwinputcheck.getText())) {
				pwmesage.setVisible(true);
				pwmesage.setText("비밀번호 불일치!!");
				pwmesage.setFill(Color.RED);
			}
		});

		pwinputcheck.setOnKeyReleased((EventHandle) -> {
			if (pwinput2.getText().equals(pwinputcheck.getText())) {
				pwmesage.setVisible(true);
				pwmesage.setText("비밀번호 일치!!");
				pwmesage.setFill(Color.GREEN);
			} else if (!pwinput2.getText().equals(pwinputcheck.getText())) {
				pwmesage.setVisible(true);
				pwmesage.setText("비밀번호 불일치!!");
				pwmesage.setFill(Color.RED);
			}
		});

		joinB.setPrefWidth(100); // 뒤로가기 버튼
		joinB.setStyle("-fx-background-color: cornsilk");
		joinB.setFont(new Font("blond", 20));
		joinB.setOnAction((ActionEvent) -> {
			String command = Db_Login.signup(idinput2.getText(), pwinput2.getText(), birthdayinput.getText());
			alert.setTitle("Join Mesage");
			alert.setContentText(command);
			alert.setHeaderText(null);
			alert.show();
			init_text();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			hbox3.setVisible(false);
			gridpane2.setVisible(false);
			vbox2.setVisible(true);
		});

		hbox1.getChildren().addAll(signinB, signupB);
		hbox2.getChildren().addAll(loginB, backB);
		hbox3.getChildren().addAll(joinB, backB2);
		hbox4.getChildren().addAll(memotitleL, saveB1, deleteB1);
		hbox5.getChildren().addAll(memodetailL, saveB2, deleteB2);
		hbox6.getChildren().addAll(browseB, saveB3, deleteB3);

		vbox1.getChildren().addAll(memoL, memodateL, hbox4, memotitle, hbox5, memodetail, memoimageL, hbox6);
		vbox1.setSpacing(25);
		vbox1.relocate(730, 0);

		memoL.setFont(new Font("Jeju Ghotic", 15));

		memodateL.setFont(new Font("Jeju Ghotic", 15));
		memodateL.setText(inputYear + monthBtext + getText);

		memotitleL.setFont(new Font("Jeju Ghotic", 15));

		memotitle.setPrefSize(280, 50);

		memodetailL.setFont(new Font("Jeju Ghotic", 15));

		memodetail.setPrefSize(280, 200);

		memoimageL.setFont(new Font("Jeju Ghotic", 15));

		browseB.setPrefSize(150, 30);
		browseB.setOnAction((ActionEvent) -> {
			configureFileChooser();

		});

		saveB1.setOnAction((ActionEvent) -> {
			Db_Login.insert_memo(nowid, memotitle.getText(), memodetail.getText(), inputYear, inputMonth,
					calday.get(m).getText());
			calbuttonremove(inputYear, inputMonth, inputDate);
			gridpane3.getChildren().removeAll();
			calbutton(inputYear, inputMonth, inputDate);
			/*
			titleL = new Label((memotitle.getText()));
			vbox2.setMargin(titleL, new Insets(0, 0, 0, 5));
			vbox2 = new VBox(titleL);
			vbox2.setPrefSize(50, 103);
			gridpane3.getChildren().remove(calday.get(m));
			gridpane3.getChildren().remove(memoImage);
			gridpane3.add(calday.get(m), gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
			gridpane3.add(memoImage, gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
			gridpane3.add(vbox2, gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
			*/
		});

		deleteB1.setOnAction((ActionEvent) -> {

			gridpane3.getChildren()
					.removeIf(vbox2 -> gridpane3.getRowIndex(calday.get(m)) == gridpane3.getRowIndex(vbox2)
							&& gridpane3.getColumnIndex(calday.get(m)) == gridpane3.getColumnIndex(vbox2));
			gridpane3.add(calday.get(m), gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
			gridpane3.add(memoImage, gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
		});

		saveB2.setOnAction((ActionEvent) -> {

		});

		saveB3.setPrefSize(150, 30);
		saveB3.setOnAction((ActionEvent) -> {

			// System.out.println(m);
			gridpane3.add(memoImage, gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));
			memoImage.setFitWidth(102);
			memoImage.setFitHeight(103);

			gridpane3.getChildren().remove(calday.get(m));
			gridpane3.add(calday.get(m), gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));

		});

		deleteB3.setPrefSize(150, 30);
		deleteB3.setOnAction((ActionEvent) -> {

			gridpane3.getChildren()
					.removeIf(memoImage -> gridpane3.getRowIndex(calday.get(m)) == gridpane3.getRowIndex(memoImage)
							&& gridpane3.getColumnIndex(calday.get(m)) == gridpane3.getColumnIndex(memoImage));
			gridpane3.add(calday.get(m), gridpane3.getColumnIndex(calday.get(m)), gridpane3.getRowIndex(calday.get(m)));

		});

		calPane.getChildren().add(gridpane3);
		calPane.getChildren().add(now_month);
		calPane.getChildren().add(beforeB);
		calPane.getChildren().add(monthB);
		calPane.getChildren().add(nextB);
		calPane.getChildren().add(vbox1);

		beforeB.setPrefSize(40, 25);
		beforeB.relocate(500, 100);
		beforeB.setOnAction((ActionEvent) -> {
			calbuttonremove(inputYear, inputMonth, inputDate);
			vboxlist.clear();
			if (inputMonth > 1 && inputMonth < 13) {
				inputMonth--;
			}
			System.out.println(inputMonth);
			monthB.setText(monthHeader[inputMonth - 1]);
			now_month.setText(monthHeader[inputMonth - 1] + " " + Integer.toString(inputYear));
			calbutton(inputYear, inputMonth, inputDate);
			memodateL.setText(inputYear + " " + monthHeader[inputMonth - 1] + " " + Calendar.DATE);

		});

		monthB.setPrefSize(100, 25);
		monthB.relocate(560, 100);

		nextB.setPrefSize(40, 25);
		nextB.relocate(680, 100);
		nextB.setOnAction((ActionEvent) -> {
			calbuttonremove(inputYear, inputMonth, inputDate);
			vboxlist.clear();
			if (inputMonth > 0 && inputMonth < 12) {
				inputMonth++;
			}
			monthB.setText(monthHeader[inputMonth - 1]);
			now_month.setText(monthHeader[inputMonth - 1] + " " + Integer.toString(inputYear));
			calbutton(inputYear, inputMonth, inputDate);
			memodateL.setText(inputYear + " " + monthHeader[inputMonth - 1] + " " + Calendar.DATE);

		});

		now_month.setPrefSize(200, 50);
		now_month.relocate(570, 130);
		now_month.setFont(new Font("Jeju Ghotic", 18));

		//backimage.setFitWidth(1280);
		//backimage.setFitHeight(720);


		titleText.setFont(new Font("blond",30));

		//root.getChildren().add(backimage);
		root.getChildren().add(vbox2);
		root.getChildren().add(hbox2);
		root.getChildren().add(hbox3);
		root.getChildren().add(gridpane1);
		root.getChildren().add(gridpane2);
		root.getChildren().add(calPane);

		primary.setTitle("MyCalendar");
		primary.setScene(new Scene(root, 1280, 720));
		primary.setResizable(true);
		primary.show();

	}

	public void calendaropen() { // 로그인 본인 달력화면 구현
		hbox2.setVisible(false);
		calPane.setVisible(true);

	}

	public void calbutton(int year, int month, int date) { // 달력 버튼 구현
		for (int i = 0; i < 7; i++) {
			caldate.add(new Button(calHeader[i]));
			caldate.get(i).setPrefSize(185, 35);
			caldate.get(i).setFont(new Font("Jeju Gothic", 13));
			caldate.get(i)
					.setStyle("-fx-background-color :  #ffffff; -fx-background-color: rgba( 255, 255, 255, 0.5 ) ");
			caldate.get(i).setAlignment(Pos.BOTTOM_RIGHT);
			gridpane3.add(caldate.get(i), i, 0);
		}
		// System.out.println(caldate);

		caldate.get(0).setTextFill(Color.RED);
		caldate.get(6).setTextFill(Color.BLUE);

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DATE, date);

		startDay = c.get(Calendar.DAY_OF_WEEK);
		lastDay = c.getActualMaximum(Calendar.DATE);
		inputDay = 1;

		int row = 0;
		for (int i = 1; inputDay <= lastDay; i++) {
			if (i < startDay) {
				calday.add(new Button(" "));
			} else {
				calday.add(new Button(Integer.toString(inputDay)));
				inputDay++;
			}
		}
		// System.out.println(calday);
		k = 0;
		int z = calday.size();
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (k < z) {
					gridpane3.add(calday.get(k), j, i);
					calday.get(k).setPrefSize(185, 103);
					calday.get(k).setFont(new Font("Jeju Gothic", 10));
					calday.get(k).setAlignment(Pos.TOP_RIGHT);
					calday.get(k).setStyle(
							"-fx-background-color :  #ffffff; -fx-background-color: rgba( 255, 255, 255, 0.5 ) ");
					if (j == 0) {
						calday.get(k).setTextFill(Color.RED);
					} else if (j == 6) {
						calday.get(k).setTextFill(Color.BLUE);
					}
					if (calday.get(k).getText().equals(birthdayday) && inputMonth == Integer.parseInt(birthdaymonth)) {

						calday.get(k).setTextFill(Color.AQUA);
						calday.get(k).setStyle("-fx-border-color: AQUA");
						titleL = new Label("BirthDay!!");
						vboxlist.put(calday.get(k).getText(), new VBox(titleL));
						vboxlist.get(calday.get(k).getText()).setMargin(titleL, new Insets(0,0,0,5));
						gridpane3.getChildren().remove(calday.get(k));
						gridpane3.add(calday.get(k), gridpane3.getColumnIndex(calday.get(k)),
								gridpane3.getRowIndex(calday.get(k)));
						gridpane3.add(vboxlist.get(calday.get(k).getText()), gridpane3.getColumnIndex(calday.get(k)),
								gridpane3.getRowIndex(calday.get(k)));
					}

					if (Db_Login.out_memo(nowid, inputYear, inputMonth, calday.get(k).getText()).equals("1")) {
						System.out.println("db꺼내기");
						titleL = new Label(Db_Login.getTitle());
						vboxlist.put(calday.get(k).getText(), new VBox(titleL));
						vboxlist.get(calday.get(k).getText()).setMargin(titleL, new Insets(0,0,0,5));
						gridpane3.getChildren().remove(calday.get(k));
						gridpane3.add(calday.get(k), gridpane3.getColumnIndex(calday.get(k)),
								gridpane3.getRowIndex(calday.get(k)));
						gridpane3.add(vboxlist.get(calday.get(k).getText()), gridpane3.getColumnIndex(calday.get(k)),
								gridpane3.getRowIndex(calday.get(k)));

						memodetail.setText(Db_Login.getDetail());
					}
					calday.get(k).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {

							calday.get(m).setStyle(
									"-fx-background-color :  #ffffff; -fx-background-color: rgba( 255, 255, 255, 0.5 ) ");
							getText = ((Button) arg0.getSource()).getText();
							m = calday.indexOf(arg0.getSource());
							// System.out.println(m);
							memodateL.setText(inputYear + " " + monthHeader[inputMonth - 1] + " " + getText);
							calday.get(m).setStyle("-fx-border-color: blue");
						}

					});

					k++;
				}
			}
		}
	}

	public void calbuttonremove(int year, int month, int date) { // 달력 버튼 삭제

		for (int i = 0; i < 7; i++) {
			gridpane3.getChildren().remove(caldate.get(i));
		}
		caldate.clear();
		k = 0;
		int z = calday.size();
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (k < z) {
					gridpane3.getChildren().remove(calday.get(k));
					gridpane3.getChildren().remove(vboxlist.get(Integer.toString(k)));
					k++;
				}
			}
		}
		calday.clear();
		// System.out.println(caldate);
		// System.out.println(calday);
	}

	public void configureFileChooser() {
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("C:/"));

		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		fc.getExtensionFilters().addAll(imgType);

		selectedFile = fc.showOpenDialog(null);

		try {

			fis = new FileInputStream(selectedFile);
			bis = new BufferedInputStream(fis);

			fileimage = new Image(bis);
			memoImage = new ImageView(fileimage);
			// memoImage.setImage(fileimage);
			memoImage.setX(1040);
			memoImage.setFitWidth(150);
			memoImage.setFitHeight(103);

			vbox1.getChildren().add(memoImage);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
